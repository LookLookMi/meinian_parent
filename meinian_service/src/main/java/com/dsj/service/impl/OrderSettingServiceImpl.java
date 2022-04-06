package com.dsj.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dsj.dao.OrderSettingDao;
import com.dsj.pojo.OrderSetting;
import com.dsj.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Author    DSJ
 * date:   2022/4/3 10:44
 **/
@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
  @Autowired
  private OrderSettingDao orderSettingDao;
    @Override
    public void addBatch(ArrayList<OrderSetting> listData) {
        for (OrderSetting listDatum : listData) {
            int count=orderSettingDao.findOrderSettingByOrderData(listDatum.getOrderDate());
            if(count>0){
                orderSettingDao.edit(listDatum);
            }else {
                orderSettingDao.add(listDatum);
            }
        }
    }

    @Override
    public List<Map> getOrderSettingByMonth(String date) {
        String startDate=date+"-1";
        String endDate=date+"-31";
        Map param=new HashMap();
        param.put("startDate",startDate);
        param.put("endDate",endDate);
       List<OrderSetting> list= orderSettingDao.getOrderSettingByMonth(param);
       List<Map> listData=new ArrayList<>();
       // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (OrderSetting orderSetting : list) {
            Map map=new HashMap();
            map.put("date",orderSetting.getOrderDate().getDate());
            map.put("number",orderSetting.getNumber());
            map.put("reservations",orderSetting.getReservations());
            listData.add(map);
        }
        return listData;
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        int count = orderSettingDao.findOrderSettingByOrderData(orderSetting.getOrderDate());
        if(count>0){
            orderSettingDao.edit(orderSetting);
        }else{
            orderSettingDao.add(orderSetting);
        }
    }


}
