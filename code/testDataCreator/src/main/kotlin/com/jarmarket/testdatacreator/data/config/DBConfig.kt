package com.jarmarket.testdatacreator.data.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.autoconfigure.batch.BatchDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import javax.sql.DataSource

@Configuration
class DBConfig {
    @Bean
    fun localReadDatasource() : DataSource {
        val config = HikariConfig("/datasource/datasource.local.read.properties")
        return HikariDataSource()
    }

    @Bean
    @Primary
    @BatchDataSource
    fun localWriteDatasource() : DataSource {
        val config = HikariConfig("/datasource/datasource.local.write.properties")
        config.schema = "/schema/schema.sql"
        return HikariDataSource()
    }

}