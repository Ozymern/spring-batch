package com.ozymern.springbatch.listener;

import com.ozymern.springbatch.processor.UserProcessor;
import com.ozymern.springbatch.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserListener  extends JobExecutionListenerSupport {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserListener.class);


    @Autowired
    private UserRepository userRepository;
    @Override
    public void afterJob(JobExecution jobExecution) {
       if(jobExecution.getStatus()== BatchStatus.COMPLETED){
           LOGGER.info("FINALIZO JOB");
           userRepository.findAll().stream().forEach(x->{
               LOGGER.info(x.getName());
               LOGGER.info(x.getEmail());
               LOGGER.info(x.getLastName());
               LOGGER.info(String.valueOf(x.getAge()));
               LOGGER.info(x.getSex());
           });

       }
    }
}
