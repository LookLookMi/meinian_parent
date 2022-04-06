package com.dsj.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dsj.constant.MessageConstant;
import com.dsj.constant.RedisConstant;
import com.dsj.entity.PageResult;
import com.dsj.entity.QueryPageBean;
import com.dsj.entity.Result;
import com.dsj.pojo.Setmeal;
import com.dsj.pojo.TravelGroup;
import com.dsj.service.SetmealService;
import com.dsj.service.TravelGroupService;
import com.dsj.util.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Author    DSJ
 * date:   2022/4/2 15:38
 **/
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference
    private SetmealService setmealService;
    @Autowired
    private JedisPool jedisPool;
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
       PageResult pageResult= setmealService.findPage(queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),queryPageBean.getQueryString());
        return pageResult;
    }
    @RequestMapping("/add")
    public Result add(Integer[] travelgroupIds, @RequestBody Setmeal setmeal){
        try {
            setmealService.add(travelgroupIds,setmeal);
            return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);

        }
    }
    @RequestMapping("/upload")
    public Result upload(MultipartFile imgFile){
        try {
            String filename = imgFile.getOriginalFilename();
            int index=filename.lastIndexOf(".");
            String suffix = filename.substring(index);
            String file=UUID.randomUUID().toString()+suffix;
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),file);
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,file);
            return new Result(true,MessageConstant.PIC_UPLOAD_SUCCESS,file);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(true,MessageConstant.PIC_UPLOAD_FAIL);

        }
    }

}
