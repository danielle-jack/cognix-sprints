export default class Customer {
  constructor({ id = '', name = '', username = '' } = {}) {
    this.id = id
    this.name = name || username
    this.username = username || name
  }

  static from(obj) {
    return new Customer({ id: obj.id || obj._id || '', name: obj.name || obj.username || '', username: obj.username || obj.name || '' })
  }
}