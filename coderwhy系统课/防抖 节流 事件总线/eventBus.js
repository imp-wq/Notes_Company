class EventBus {
  #eventMap

  constructor() {
    this.#eventMap = {}
  }
  on(eventName, eventFn) {
    let eventFns = this.#eventMap[eventName]

    // 第一次监听该事件，在eventMap中创建eventFns数组。
    if (!eventFns) {
      eventFns = []
      this.#eventMap[eventName] = eventFns
    }
    eventFns.push(eventFn)
  }

  emit(eventName, ...args) {
    const eventFns = this.#eventMap[eventName]
    // 该事件从未被监听过，直接return。
    if (!eventFns) return
    eventFns.forEach(fn => fn(...args))
  }

  // 取消监听。
  off(eventName, eventFn) {
    const eventFns = this.#eventMap[eventName]
    if (!eventFns) {
      return
    }
    for (let i = 0; i < eventFns.length; i++) {
      const fn = eventFns[i]
      if (fn === eventFn) {
        eventFns.splice(i, 1)
        break
      }
    }

    // 如果事件列表已清空，则删除该事件字段。
    if (eventFns.length === 0) {
      delete this.#eventMap[eventName]
    }
  }
}

export default EventBus