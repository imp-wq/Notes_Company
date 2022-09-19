// v5版本增加onInvalidate函数，用于在每次副作用执行之前，对旧的副作用进行清理。

// 实现immediate。第一次执行时oldVal为undefined。
// 实现flush，为'post'时封装成promise放入微任务队列。
import { effect } from './myReactive v6.mjs'

function watch(source, cb, options = {}) {
    let getter
    if (typeof source === 'function') {
        getter = source
    } else {
        getter = () => traverse(source)
    }

    let oldValue, newValue

    // 用于保存清理副作用的过期回调。
    let cleanup
    // 用于注册过期回调的函数。
    function onInvalidate(fn) {
        cleanup = fn
    }

    // 将原本scheduler中的调度函数封装成独立的job函数。
    const job = () => {
        newValue = effectFn()
        cleanup?.()
        cb(newValue, oldValue, onInvalidate)
        oldValue = newValue
    }

    const effectFn = effect(
        () => getter(),
        {
            lazy: true,
            scheduler: () => {
                if (options.flush === 'post') {
                    // 放到微任务队列。
                    const p = Promise.resolve()
                    p.then(job)
                } else {
                    job()
                }
            }
        })

    if (options.immediate) {
        // 如果immediate为true，立即执行job。
        job()
    } else {
        oldValue = effectFn()

    }
}

function traverse(value, seen = new Set()) {
    if (typeof value !== 'object' || value === null || seen.has(value)) return
    seen.add(value)

    for (const k in value) {
        traverse(value[k], seen)
    }

    return value
}

export default watch