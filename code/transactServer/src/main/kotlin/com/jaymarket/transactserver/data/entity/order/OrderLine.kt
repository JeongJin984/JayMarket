package com.jaymarket.transactserver.data.entity.order

import com.jaymarket.transactserver.data.entity.money.Money

class OrderLine(
    product: Product,
    price: Money,
    quantity: Int,
    amounts: Money
) {
    var product: Product = product
        private set
    var price: Money = price
        private set
    var quantity: Int = quantity
        private set
    var amounts: Money = amounts
        private set

    private fun calculateAmounts() : Money {
        return price.multiply(amounts)
    }
}