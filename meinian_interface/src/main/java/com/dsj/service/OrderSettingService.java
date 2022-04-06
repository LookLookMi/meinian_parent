package com.dsj.service;

import com.dsj.pojo.OrderSetting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface OrderSettingService {
    void addBatch(ArrayList<OrderSetting> listData);

    List<Map> getOrderSettingByMonth(String date);

    void editNumberByDate(OrderSetting orderSetting);
}
