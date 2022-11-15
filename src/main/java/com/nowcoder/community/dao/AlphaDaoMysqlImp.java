package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

/**
 * @author VvV
 * @date 2022/7/2
 */
@Repository
public class AlphaDaoMysqlImp implements AlphaDao{


    @Override
    public String getData() {
        return "Mysql获取数据！";
    }
}
