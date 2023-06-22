package com.jaymarket.transactserver.data.entity.shipping

class ShippingInfo(
    receiver: Receiver,
    shippingAddress1: Address,
) {
    var receiver: Receiver = receiver
        private set
}