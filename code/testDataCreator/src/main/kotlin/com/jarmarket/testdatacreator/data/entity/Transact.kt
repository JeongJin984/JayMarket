package com.jarmarket.testdatacreator.data.entity

import org.jetbrains.exposed.sql.*
import org.joda.time.DateTime

class Transact : Table () {
    val eventTime: Column<DateTime> = date("event_time")
    val eventType: Column<String> = varchar("event_type", 20)
    val productId: Column<String> = varchar("product_id", 20)
    val categoryId: Column<String> = varchar("product_id", 30)
    val categoryCode: Column<String> = varchar("product_code", 100)
    val brand: Column<String> = varchar("brand", 100)
    val price: Column<Double> = double("price")
    val userId: Column<String> = varchar("user_id", 100, )
    val userSession: Column<String> = varchar("user_session", 255)
}