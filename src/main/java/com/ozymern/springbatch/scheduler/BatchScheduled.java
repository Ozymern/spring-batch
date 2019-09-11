package com.ozymern.springbatch.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@EnableScheduling
@Service
public class BatchScheduled {

    private static final Logger LOGGER = LoggerFactory.getLogger(BatchScheduled.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("jobUserTextFile")
    private Job jobUserTextFile;

    private void executeJob(Job job, String actorName) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
    	
    	LOGGER.info("Inicia proceso de generacion de archivo " + new Date());
        JobParameters param = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis())).toJobParameters();

        LOGGER.info("Job getName: " + job.getName());
        
        jobLauncher.run(job, param);
        LOGGER.info("Finaliza proceso de generacion de archivo " + new Date());
    }

    @Scheduled(cron = "${batch.scheduler.cron}")
    public void runuSER() throws Exception {
    	
        executeJob(jobUserTextFile, "[USER]");
    }
}
