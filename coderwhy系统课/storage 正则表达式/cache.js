class Cache {
  #storage

  constructor(isLoacal = true) {
    if (isLoacal) {
      this.#storage = localStorage
    }
    else {
      this.#storage = sessionStorage
    }
  }

  setCache(key, value) {
    this.#storage.setItem(key, JSON.stringify(value))
  }

  getCache(key) {
    const value = this.#storage.getItem(key)
    if (value) {
      return JSON.parse(value)
    }

    throw new Error('value is empty!')

  }

  removeCache(key) {
    this.#storage.removeItem(key)
  }

  clear() {
    this.#storage.clear()
  }
}

export default Cache