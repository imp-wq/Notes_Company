// 增加lazy，使副作用函数不会立刻执行。
// effect增加返回值，使其能被看作getter函数。
let activeEffect
const effectStack = []

const bucket = new WeakMap()

function effect(fn, options = {}) {
    const effectFn = () => {
        cleanup(effectFn)
        effectStack.push(effectFn)
        activeEffect = effectFn
        // 将副作用函数执行结果保存并返回。
        const res = fn()
        effectStack.pop()
        activeEffect = effectStack[effectStack.length - 1]
        return res
    }
    effectFn.options = options
    effectFn.deps = []
    // 根据lazy决定，副作用函数是否在立刻执行。
    if (!options.lazy) {
        effectFn()
    }

    // effectFn为包装后的副作用函数。
    return effectFn
}


function track(target, key) {
    if (!activeEffect) return
    let depsMap = bucket.get(target)
    if (!depsMap) {
        bucket.set(target, (depsMap = new Map()))
    }
    let deps = depsMap.get(key)
    if (!deps) {
        depsMap.set(key, (deps = new Set()))
    }
    deps.add(activeEffect)

    activeEffect.deps.push(deps)
}

function trigger(target, key) {

    const depsMap = bucket.get(target)
    if (!depsMap) return

    const effects = depsMap.get(key)

    const effectsToRun = new Set()
    effects && effects.forEach(effectFn => {
        if (effectFn !== activeEffect) {
            effectsToRun.add(effectFn)
        }
    })
    effectsToRun.forEach(effectFn => {
        // 如果当前副作用函数存在调度器，则调用，并将自身所作为参数传入。
        if (effectFn.options.scheduler) {
            effectFn.options.scheduler(effectFn)
        } else {
            // 如果没有调度器，则直接执行。
            effectFn()
        }
    })

}

function cleanup(effectFn) {


    effectFn.deps.forEach(effects => {
        effects.delete(effectFn)
    })
    effectFn.deps = []
}


function getProxy(obj) {
    return new Proxy(obj, {
        get(target, key) {
            track(target, key)
            return target[key]
        },
        set(target, key, newVal) {
            target[key] = newVal
            trigger(target, key)
            return true
        }
    })
}

export { getProxy, effect, track, trigger }