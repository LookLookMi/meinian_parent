package com.dsj.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dsj.constant.MessageConstant;
import com.dsj.entity.PageResult;
import com.dsj.entity.QueryPageBean;
import com.dsj.entity.Result;
import com.dsj.pojo.TravelItem;
import com.dsj.service.TravelItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author    DSJ
 * date:   2022/4/1 14:10
 **/
@RestController
@RequestMapping("/travelItem")
public class TravelItemController {
      @Reference
    private TravelItemService travelItemService;

      @RequestMapping("/findAll")
      public Result findAll(){
         List<TravelItem> list= travelItemService.findAll();
         return new Result(true,MessageConstant.QUERY_TRAVELITEM_SUCCESS,list);
      }
      @RequestMapping("/edit")
      @PreAuthorize("hasAnyAuthority('TRAVELITEM_EDIT')")
      public Result edit(@RequestBody TravelItem travelItem){
          try {
              travelItemService.edit(travelItem);
              return new Result(true, MessageConstant.EDIT_TRAVELITEM_SUCCESS);
          } catch (Exception e) {
              e.printStackTrace();
              return new Result(false, MessageConstant.EDIT_TRAVELITEM_FAIL);
          }
      }
      @RequestMapping("/getById")

      public Result getById(Integer id){
          try {
              TravelItem travelItem = travelItemService.getById(id);
              return new Result(true,MessageConstant.QUERY_TRAVELITEM_SUCCESS,travelItem);
          } catch (Exception e) {
              e.printStackTrace();
              return new Result(false,MessageConstant.QUERY_TRAVELITEM_FAIL);

          }
      }
      @RequestMapping("/delete")
      @PreAuthorize("hasAnyAuthority('TRAVELITEM_DELETE')")
      public Result delete(Integer id){
          try {
              travelItemService.delete(id);
              return new Result(true, MessageConstant.DELETE_TRAVELITEM_SUCCESS);
          } catch (Exception e) {
              e.printStackTrace();
              return new Result(false, MessageConstant.DELETE_TRAVELITEM_FAIL);
          }
      }
      @RequestMapping("/add")
      @PreAuthorize("hasAnyAuthority(TRAVELITEM_ADD)")
      public Result add(@RequestBody TravelItem travelItem){
          try {
              travelItemService.add(travelItem);
              return new Result(true, MessageConstant.ADD_TRAVELITEM_SUCCESS);
          } catch (Exception e) {
              e.printStackTrace();
              return new Result(false, MessageConstant.ADD_TRAVELITEM_FAIL);
          }
      }
    @RequestMapping("/findPage")
    @PreAuthorize("hasAnyAuthority(('TRAVELITEM_QUERY'))")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
         return travelItemService.findPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize()
          ,queryPageBean.getQueryString());
    }
}
