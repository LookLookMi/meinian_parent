package com.dsj.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dsj.constant.MessageConstant;
import com.dsj.dao.MemberDao;
import com.dsj.dao.OrderDao;
import com.dsj.dao.OrderSettingDao;
import com.dsj.entity.Result;
import com.dsj.pojo.Member;
import com.dsj.pojo.Order;
import com.dsj.pojo.OrderSetting;
import com.dsj.service.OrderService;
import com.dsj.util.DateUtils;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Author    DSJ
 * date:   2022/4/3 20:19
 **/
@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;
   @Autowired
    MemberDao memberDao;
   @Autowired
    OrderSettingDao orderSettingDao;

    @Override
    public Result saveOrder(Map map) throws Exception {
        int setmealId = Integer.parseInt((String)map.get("setmealId"));
        String orderDate= (String) map.get("orderDate");
        Date date=DateUtils.parseString2Date(orderDate);
        OrderSetting orderSetting= orderSettingDao.getOrderSettingByOrderData(date);
        if(orderSetting==null){
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }else {
            int number = orderSetting.getNumber();
            int reservations = orderSetting.getReservations();
            if (number <= reservations) {
                return new Result(false, MessageConstant.ORDER_FULL);
            }
        }
        String telephone = (String) map.get("telephone");
       Member member= memberDao.getMemberByTelephone(telephone);
        if(member==null){
            member=new Member();
            member.setName((String) map.get("name"));
            member.setSex((String) map.get("sex"));
            member.setIdCard((String) map.get("idCard"));
            member.setPhoneNumber(telephone);
            member.setRegTime(new Date());
            memberDao.add(member);
        }else{
            Order orderParam = new Order();
            orderParam.setOrderDate(date);
            orderParam.setSetmealId(setmealId);
            orderParam.setMemberId(member.getId());
            List<Order> orderList= orderDao.findOrderCountByCondition(orderParam);
            if(orderList!=null&&orderList.size()>0){
                return new Result(false,MessageConstant.HAS_ORDERED);
            }
        }
        Order order=new Order();
        order.setMemberId(member.getId());
        order.setOrderDate(date);
        order.setOrderType("微信预约");
        order.setOrderStatus("未出游");
        order.setSetmealId(setmealId);
        orderDao.add(order);
      orderSetting.setReservations(orderSetting.getReservations()+1);
      orderSettingDao.editReservationsByOrderDate(orderSetting);
        return new Result(true,MessageConstant.ORDER_SUCCESS,order);
    }

    @Override
    public Map<String, Object> findById(Integer orderId) throws Exception {
        Map<String, Object> map = orderDao.findById(orderId);
        Date date = (Date) map.get("orderDate");
         map.put("orderDate", DateUtils.parseDate2String(date));
        return map;
    }
}
