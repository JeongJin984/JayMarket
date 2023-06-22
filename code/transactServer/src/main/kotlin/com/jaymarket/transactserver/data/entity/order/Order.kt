package com.jaymarket.transactserver.data.entity.order

import com.jaymarket.transactserver.data.entity.money.Money
import com.jaymarket.transactserver.data.entity.shipping.ShippingInfo
import java.lang.IllegalArgumentException

class Order(
    orderNumber: String,
    state: OrderState,
    shippingInfo: ShippingInfo,
    orderLine: List<OrderLine>,
    totalAmounts: Money
) {
    val orderNumber: String = orderNumber
    var state : OrderState = state
        private set
    var shippingInfo: ShippingInfo = shippingInfo
        private set

    var orderLines: List<OrderLine> = orderLine
        private set

    var totalAmounts: Money = totalAmounts
        private set
    fun changeShippingInfo(shippingInfo: ShippingInfo) {
        if(!isShippingChangeable()) {
            throw IllegalStateException("can't change shipping in $state")
        }
        this.shippingInfo = shippingInfo
    }

    fun cancel() {
        verifyNotYetShipped()
        this.state = OrderState.CANCELED
    }

    @Override
    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(other == null) return false
        if(other !is Order) return false
        return orderNumber == other.orderNumber
    }

    @Override
    override fun hashCode(): Int {
        val prime = 31
        val result = 1
        return prime * result + orderNumber.hashCode();
    }

    private fun verifyNotYetShipped() {
        if(state != OrderState.PAYMENT_WAITING && state != OrderState.PREPARING)
            throw IllegalStateException("already shipped")
    }

    private fun isShippingChangeable() : Boolean {
        return state == OrderState.PAYMENT_WAITING ||
                state == OrderState.PREPARING
    }

    private fun setOrderLines(orderLines: List<OrderLine>) {
        verifyAtLeastOneOrderLines(orderLines);
        this.orderLines = orderLines
    }

    private fun verifyAtLeastOneOrderLines(orderLines: List<OrderLine>) {
        if(orderLines.isEmpty()) {
            throw IllegalArgumentException("no OrderLine")
        }
    }

    private fun calculateTotalAmounts() {
        val sum = this.orderLines.sumOf { it.amounts.price }
        this.totalAmounts = Money(sum)
    }

    public enum class OrderState {
        PAYMENT_WAITING, PREPARING, SHIPPED, DELIVERING, DELIVERY_COMPLETED, CANCELED
    }
}