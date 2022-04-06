package com.dsj.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dsj.constant.MessageConstant;
import com.dsj.entity.PageResult;
import com.dsj.entity.QueryPageBean;
import com.dsj.entity.Result;
import com.dsj.pojo.Address;
import com.dsj.service.AddressService;
import com.dsj.service.MemberService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author    DSJ
 * date:   2022/4/5 15:36
 **/
@RestController
@RequestMapping("/address")
public class AddressController {
    @Reference
    private AddressService addressService;
    @RequestMapping("/deleteById")
    public Result deleteById(Integer id){
       addressService.deleteById(id);
       return new Result(true,"删除地址成功");
    }
    @RequestMapping("/addAddress")
    public Result add(@RequestBody Address address){
        addressService.add(address);
        return new Result(true,"添加地址成功");
    }
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return addressService.findPage(queryPageBean);


    }
 @RequestMapping("/findAllMaps")
    public Map findAllMaps(){
     Map map = new HashMap();
     List<Map> gridnMaps=new ArrayList<>();
     List<Map> nameMaps=new ArrayList<>();
     List<Address> list= addressService.findAllMaps();
     for (Address address : list) {
         String addressName = address.getAddressName();
         Map<String,String> mapName=new HashMap<>();
         mapName.put("addressName",addressName);
         nameMaps.add(mapName);
         Map<String,String> gridMap=new HashMap<>();
            gridMap.put("lng",address.getLng());
            gridMap.put("lat",address.getLat());
         gridnMaps.add(gridMap);
     }

      map.put("gridnMaps",gridnMaps);
      map.put("nameMaps",nameMaps);
       return map;
 }
}
