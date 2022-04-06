package com.dsj.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dsj.constant.MessageConstant;
import com.dsj.entity.Result;
import com.dsj.pojo.OrderSetting;
import com.dsj.service.OrderSettingService;
import com.dsj.util.POIUtils;
import com.sun.org.apache.xpath.internal.operations.Mult;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Author    DSJ
 * date:   2022/4/3 10:43
 **/
@RestController
@RequestMapping("/orderSetting")
public class OrderSettingController {
    @Reference
    private OrderSettingService orderSettingService;

    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String date){
       List<Map> list= orderSettingService.getOrderSettingByMonth(date);
       return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,list);
    }
    @RequestMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting){
        try {
            orderSettingService.editNumberByDate(orderSetting);
            return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ORDERSETTING_FAIL);
        }
    }
    @RequestMapping("/upload")
     public Result upload(MultipartFile excelFile){
         try {
             List<String[]> list = POIUtils.readExcel(excelFile);
             ArrayList<OrderSetting> listData = new ArrayList<>();
             for (String[] strings : list) {
                String dateStr= strings[0];
                String numberStr= strings[1];
                 OrderSetting orderSetting = new OrderSetting();
                 orderSetting.setOrderDate(new Date(dateStr));
                 orderSetting.setNumber(Integer.parseInt(numberStr));
                 listData.add(orderSetting);
             }
             orderSettingService.addBatch(listData);
             return new Result(true, MessageConstant.UPLOAD_SUCCESS);
         } catch (IOException e) {
             e.printStackTrace();
             return new Result(false,MessageConstant.UPLOAD_FAIL);
         }
     }
}
