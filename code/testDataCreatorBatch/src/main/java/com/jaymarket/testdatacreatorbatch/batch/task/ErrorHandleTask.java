package com.jaymarket.testdatacreatorbatch.batch.task;

import com.jaymarket.testdatacreatorbatch.common.dto.SlackMessageBody;
import com.jaymarket.testdatacreatorbatch.common.util.SlackUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class ErrorHandleTask implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        SlackUtil.sendMessage(
                "https://hooks.slack.com/services/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",
                new SlackMessageBody("#data_process_message", "Data-Creation-Batch", ":ghost:", "테스트 입니당.")
        )
                .thenAccept(log::warn)
                .get();
        return null;
    }
}
