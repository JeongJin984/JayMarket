package com.jaymarket.testdatacreatorbatch.batch;

import com.jaymarket.testdatacreatorbatch.batch.domain.JayTransact;
import com.jaymarket.testdatacreatorbatch.batch.domain.JayTransactVO;
import com.jaymarket.testdatacreatorbatch.batch.domain.TransactVO;
import com.jaymarket.testdatacreatorbatch.batch.processor.FileItemProcessor;
import com.jaymarket.testdatacreatorbatch.batch.processor.JayTransactItemProcessor;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.RecordFieldSetMapper;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    @Bean
    public Job testDataCreationJob() {
        return jobBuilderFactory.get("dataCreationJob")
                .incrementer(new RunIdIncrementer())
                .start(createSimpleData())
                .next(csvProcess())
                .build();
    }

    @Bean
    public Step createSimpleData() {
        return stepBuilderFactory.get("createSimpleData")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("test!!!");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step csvProcess() {
        return stepBuilderFactory.get("csv_process")
                .chunk(100)
                .reader(fileItemReader(null))
                .processor(fileProcessor())
                .writer(transactItemWriter())
                .build();

    }

    @Bean
    @StepScope
    public FlatFileItemReader<TransactVO> fileItemReader(
            @Value("#{jobParameters['csvname']}") String csvname
    ) {
        return new FlatFileItemReaderBuilder<TransactVO>().name("file_reader")
                .resource(new FileSystemResource(csvname))
                .fieldSetMapper(new RecordFieldSetMapper<>(TransactVO.class))
                .targetType(TransactVO.class)
                .linesToSkip(1)
                .delimited().delimiter(",")
                .names("Series_reference", "Period","Data_value", "Suppressed", "STATUS", "UNITS", "Magnitude", "Subject", "Group", "Series_title_1", "Series_title_2", "Series_title_3", "Series_title_4", "Series_title_5")
                .build();
    }

    @Bean
    public CompositeItemProcessor<? super Object, ?> fileProcessor() {
        List<ItemProcessor<?, ?>> itemProcessor = new ArrayList<>();
        itemProcessor.add(new FileItemProcessor());
        itemProcessor.add(new JayTransactItemProcessor());
        return new CompositeItemProcessorBuilder<>()
                .delegates(itemProcessor)
                .build();
    }

    @Bean
    public ItemProcessor<JayTransactVO, JayTransact> jayTransactProcessor() {
        return new JayTransactItemProcessor();
    }

    @Bean
    public ItemWriter transactItemWriter() {
        return new JdbcBatchItemWriterBuilder<JayTransact>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .dataSource(dataSource)
                .sql("insert into devdb.transact (transid) values (:transid) ")
                .build();
    }

}
