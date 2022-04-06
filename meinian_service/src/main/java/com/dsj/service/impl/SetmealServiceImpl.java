package com.dsj.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dsj.constant.RedisConstant;
import com.dsj.dao.SetmealDao;
import com.dsj.entity.PageResult;
import com.dsj.pojo.Setmeal;
import com.dsj.service.SetmealService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author    DSJ
 * date:   2022/4/2 15:39
 **/
@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealDao setmealDao;
    @Autowired
    private JedisPool jedisPool;

    @Override
    public void add(Integer[] travelgroupIds, Setmeal setmeal) {
        setmealDao.add(setmeal);
        Integer id = setmeal.getId();
        setSetmealAndTravelGroup(travelgroupIds,id);
        String pic = setmeal.getImg();
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,pic);
    }

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
      PageHelper.startPage(currentPage, pageSize);
        Page page = setmealDao.findPage(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public List getSetmeal() {
        return setmealDao.getSetmeal();
    }

    @Override
    public Setmeal findById(Integer id) {
        return setmealDao.findById(id);
    }

    @Override
    public Setmeal getSetmealById(Integer id) {
        return setmealDao.getSetmealById(id);
    }

    @Override
    public List<Map> getSetmealReport() {
        return setmealDao.getSetmealReport();
    }

    private void setSetmealAndTravelGroup(Integer[] travelgroupIds, Integer id) {
        if(travelgroupIds!=null &&travelgroupIds.length>0){
            for (Integer travelgroupId : travelgroupIds) {
                HashMap<String, Integer> map = new HashMap<>();
                map.put("travelgroupId",travelgroupId);
                map.put("setmealId",id);
                setmealDao.addSetmealAndTravelGroup(map);
            }
        }
    }
}
