package com.jarmarket.testdatacreator

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableBatchProcessing
class TestDataCreatorApplication

fun main(args: Array<String>) {
    runApplication<TestDataCreatorApplication>(*args)
}
