package com.dsj.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dsj.constant.MessageConstant;
import com.dsj.entity.Result;
import com.dsj.pojo.Setmeal;
import com.dsj.service.SetmealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author    DSJ
 * date:   2022/4/3 16:22
 **/
@RestController
@RequestMapping("/setmeal")
public class SetmealMobileController {
    @Reference
    private SetmealService setmealService;

    @RequestMapping("/getSetmealById")
    public Result getSetmealById(Integer id){
       Setmeal setmeal= setmealService.getSetmealById(id);
       return new Result(true,MessageConstant.ORDERSETTING_SUCCESS,setmeal);
    }
    @RequestMapping("/getSetmeal")
    public Result getSetmeal(){
     List list= setmealService.getSetmeal();
      return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,list);
    }
    @RequestMapping("/findById")
    public Result findById(Integer id){
       Setmeal setmeal= setmealService.findById(id);
       return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
    }
}
