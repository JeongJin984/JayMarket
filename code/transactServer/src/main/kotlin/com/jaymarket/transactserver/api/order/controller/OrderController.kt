package com.jaymarket.transactserver.api.order.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/order")
class OrderController {
    @GetMapping("/list")
    fun getOrderList() {

    }

}