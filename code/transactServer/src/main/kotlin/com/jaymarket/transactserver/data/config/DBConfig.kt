package com.jaymarket.transactserver.data.config

import io.r2dbc.pool.ConnectionPool
import io.r2dbc.pool.ConnectionPoolConfiguration
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate

@Configuration
class DBConfig {

    @Bean
    fun localWriteConnection() : R2dbcEntityTemplate {
        return R2dbcEntityTemplate(
            ConnectionFactories.get("r2dbcs:mysql://root:pass@localhost:3306/jaymarket")
        )
    }
}