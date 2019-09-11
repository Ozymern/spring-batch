package com.ozymern.springbatch.config;


import com.ozymern.springbatch.entities.User;
import com.ozymern.springbatch.listener.UserListener;
import com.ozymern.springbatch.processor.UserProcessor;
import com.ozymern.springbatch.reader.UserReader;
import com.ozymern.springbatch.writer.UserWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableBatchProcessing
public class BatchConfig {



    @Autowired
    private UserListener userListener;

    @Autowired
    private StepBuilderFactory steps;

    @Bean
    public Step stepUserTextFile() {
        return steps.get("stepUserRecordToTextFile")
        		.<List<User>, List<User>>chunk(1)
        		.reader(readerUser())
        		.processor(processorUser())

        		.writer(writerUserFile()).build();
    }
    
    @Bean
    @StepScope
    public ItemReader<List<User>> readerUser() {
        return new UserReader();
    }

    @Bean
    @StepScope
    public ItemProcessor<List<User>, List<User>> processorUser() {
        return new UserProcessor();
    }

    @Bean
    @StepScope
    public ItemWriter<List<User>> writerUserFile() {
        return new UserWriter();
    }
    
    @Bean
    public Job jobUserTextFile(JobBuilderFactory jobs) {
        return jobs.get("jobRecordToTextFile").start(stepUserTextFile()).listener(userListener).build();
    }

}
