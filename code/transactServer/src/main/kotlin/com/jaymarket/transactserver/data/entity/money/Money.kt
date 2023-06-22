package com.jaymarket.transactserver.data.entity.money

class Money(
    price: Int
) {
    val price: Int = price

    fun add(money : Money) : Money {
        return Money(money.price + this.price)
    }

    fun multiply(money : Money) : Money {
        return Money(money.price * this.price)
    }
}