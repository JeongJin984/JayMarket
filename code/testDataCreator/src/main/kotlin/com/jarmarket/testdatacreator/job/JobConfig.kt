package com.jarmarket.testdatacreator.job

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JobConfig (
    val jobBuilderFactory: JobBuilderFactory,
    val stepBuilderFactory: StepBuilderFactory
) {

    @Bean
    fun testJob() : Job {
        return jobBuilderFactory.get("testJob")
            .start(testStep())
            .build()
    }

    @Bean
    fun testStep() : Step {
        return stepBuilderFactory.get("testStep")
            .tasklet { _, _ ->
                println("asdfasdf")
                RepeatStatus.FINISHED
             }
            .build()
    }
}