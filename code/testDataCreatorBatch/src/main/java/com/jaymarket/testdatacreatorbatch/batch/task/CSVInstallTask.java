package com.jaymarket.testdatacreatorbatch.batch.task;

import com.jaymarket.testdatacreatorbatch.common.util.DateUtil;
import com.jaymarket.testdatacreatorbatch.common.util.HttpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CSVInstallTask implements Tasklet {
    private final HttpUtil httpUtil;
    private final DateUtil dateUtil;
    @Value("${spring.profiles.active}")
    private String profile;

    @Value("${test.naverpay.transact.filename}")
    private String csvFilename;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        String filename = "";

        if(profile.equals("prod")) {
            filename = httpUtil.download(dateUtil.currentDate());
        } else if(profile.equals("dev")) {
            filename = csvFilename;
        }

        stepContribution.getStepExecution().getJobExecution().getExecutionContext().put("target.csvname", filename);
        return RepeatStatus.FINISHED;
    }
}
