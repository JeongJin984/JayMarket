package com.jaymarket.testdatacreatorbatch.batch.processor;

import com.jaymarket.testdatacreatorbatch.batch.domain.JayTransact;
import com.jaymarket.testdatacreatorbatch.batch.domain.JayTransactVO;
import org.springframework.batch.item.ItemProcessor;

public class JayTransactItemProcessor implements ItemProcessor<JayTransactVO, JayTransact> {
    @Override
    public JayTransact process(JayTransactVO jayTransactVO) throws Exception {
        return null;
    }
}
