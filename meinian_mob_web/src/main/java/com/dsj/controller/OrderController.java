package com.dsj.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dsj.constant.MessageConstant;
import com.dsj.constant.RedisMessageConstant;
import com.dsj.entity.Result;
import com.dsj.pojo.Order;
import com.dsj.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * Author    DSJ
 * date:   2022/4/3 20:18
 **/
@RestController
@RequestMapping("/order")
public class OrderController {
    @Reference
    private OrderService orderService;
    @Autowired
    JedisPool jedisPool;
    @RequestMapping("/findById")
    public Result findById(Integer orderId) throws Exception {
        Map<String,Object> map=orderService.findById(orderId);
        return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,map);
    }
    @RequestMapping("/saveOrder")
    public Result saveOrder(@RequestBody Map map){
        try {
            System.out.println(map);
            String telephone = (String) map.get("telephone");
            String validateCode = (String) map.get("validateCode");
            String redisCode = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_ORDER);
            if(redisCode==null || !redisCode.equals(validateCode)){
                 return new Result(false,MessageConstant.VALIDATECODE_ERROR);
            }
            Result result=orderService.saveOrder(map);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ORDER_FAIL);

        }
    }
}
