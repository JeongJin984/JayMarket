package com.jaymarket.testdatacreatorbatch.batch.listener;

import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;

public class TransactChunkListener {
    @BeforeChunk
    public void beforeChunk(ChunkContext chunkContext) {

    }
}
