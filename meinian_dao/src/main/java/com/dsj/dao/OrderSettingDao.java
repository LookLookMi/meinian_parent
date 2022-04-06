package com.dsj.dao;

import com.dsj.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderSettingDao {
    void add(OrderSetting listDatum);

    void edit(OrderSetting listDatum);

    int findOrderSettingByOrderData(Date orderDate);

    List<OrderSetting> getOrderSettingByMonth(Map param);

    OrderSetting getOrderSettingByOrderData(Date date);

    void editReservationsByOrderDate(OrderSetting orderSetting);


    //void editNumberByOrderDate(OrderSetting orderSetting);


}
