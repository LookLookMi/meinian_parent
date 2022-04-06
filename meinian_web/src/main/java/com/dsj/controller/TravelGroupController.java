package com.dsj.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dsj.constant.MessageConstant;
import com.dsj.entity.PageResult;
import com.dsj.entity.QueryPageBean;
import com.dsj.entity.Result;
import com.dsj.pojo.TravelGroup;
import com.dsj.service.TravelGroupService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author    DSJ
 * date:   2022/4/2 10:35
 **/
@RestController
@RequestMapping("/travelGroup")
public class TravelGroupController {
    @Reference
    private TravelGroupService travelGroupService;


    @RequestMapping("/findAll")
    public Result findAll(){
        List<TravelGroup> list=travelGroupService.findAll();
        return new Result(true, MessageConstant.QUERY_TRAVELGROUP_SUCCESS,list);
    }
    @RequestMapping("/edit")
    public Result edit(Integer[] travelItemIds, @RequestBody TravelGroup travelGroup){
        try {
            travelGroupService.edit(travelItemIds,travelGroup);
            return new Result(true, MessageConstant.EDIT_TRAVELGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_TRAVELGROUP_FAIL);
        }
    }
    @RequestMapping("/getTravelItemIdsByTravelGroupId")
    public Result getTravelItemIdsByTravelGroupId(Integer travelGroupId){
        try {
            List<Integer> TravelItemIds= travelGroupService.getTravelItemIdsByTravelGroupId(travelGroupId);
            return new Result(true,"根据跟团游查询自由行成功",TravelItemIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"根据跟团游查询自由行失败");

        }
    }
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
       PageResult pageResult= travelGroupService.findPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize(),queryPageBean.getQueryString());
        return pageResult;


    }
    @RequestMapping("/getById")
    public Result getById(Integer id){
        try {
            TravelGroup travelGroup=travelGroupService.getById(id);
            return new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS,travelGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_TRAVELGROUP_FAIL);
        }
    }
    @RequestMapping("/add")
    public Result add(Integer[] travelItemIds, @RequestBody TravelGroup travelGroup){
        try {
            travelGroupService.add(travelItemIds,travelGroup);
            return new Result(true, MessageConstant.ADD_TRAVELGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_TRAVELGROUP_FAIL);
        }
    }
}
