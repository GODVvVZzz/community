package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author VvV
 * @date 2022/7/2
 */
@Service
public class AlphaServiceImpl implements AlphaService{

    private static final Logger logger = LoggerFactory.getLogger(AlphaServiceImpl.class);

    @Autowired
    private AlphaDao alphaDao;

    @Override
    public String processData() {
        String daoData = alphaDao.getData();
        String newData = daoData + " 处理";
        return newData;
    }

    @Override
    @Async
    public void execute1() {
        logger.debug("execute1");
    }

    // 该方法程序启动自动执行
/*    @Override
    @Scheduled(initialDelay = 10000, fixedRate = 1000)
    public void execute2() {
        logger.debug("execute2");
    }*/


}

