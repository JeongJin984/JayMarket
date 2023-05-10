package com.jaymarket.testdatacreatorbatch.batch.processor;

import com.jaymarket.testdatacreatorbatch.batch.domain.JayTransactVO;
import com.jaymarket.testdatacreatorbatch.batch.domain.TransactVO;
import org.springframework.batch.item.ItemProcessor;

public class FileItemProcessor implements ItemProcessor<TransactVO, JayTransactVO> {
    @Override
    public JayTransactVO process(TransactVO transactVO) throws Exception {
        return null;
    }
}
