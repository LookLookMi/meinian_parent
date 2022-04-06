package com.dsj.service;

import com.dsj.entity.Result;

import java.util.Map;

/**
 * Author    DSJ
 * date:   2022/4/3 20:18
 **/
public interface OrderService {
    Result saveOrder(Map map) throws Exception;

    Map<String, Object> findById(Integer orderId) throws Exception;
}
