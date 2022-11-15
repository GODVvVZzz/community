package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.stereotype.Service;

/**
 * @author VvV
 * @date 2022/7/2
 */
public interface AlphaService {

    /**
     * 处理从dao层获取的数据
     * @return
     */
    String processData();

    void execute1();

    // void execute2();
}
