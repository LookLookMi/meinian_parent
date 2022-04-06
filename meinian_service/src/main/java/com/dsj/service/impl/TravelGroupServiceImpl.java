package com.dsj.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dsj.dao.TravelGroupDao;
import com.dsj.entity.PageResult;
import com.dsj.pojo.TravelGroup;
import com.dsj.service.TravelGroupService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * Author    DSJ
 * date:   2022/4/2 10:36
 **/
@Service(interfaceClass = TravelGroupService.class)
@Transactional
public class TravelGroupServiceImpl implements TravelGroupService{
    @Autowired
    private TravelGroupDao travelGroupDao;

    @Override
    public void add(Integer[] travelItemIds, TravelGroup travelGroup) {
        travelGroupDao.add(travelGroup);
        Integer id = travelGroup.getId();
        setTravelGroupAndTravelItem(id,travelItemIds);
    }

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<TravelGroup> page=travelGroupDao.findPage(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public TravelGroup getById(Integer id) {
        return travelGroupDao.getById(id);
    }

    @Override
    public List<Integer> getTravelItemIdsByTravelGroupId(Integer travelGroupId) {
        return travelGroupDao.getTravelItemIdsByTravelGroupId(travelGroupId);
    }

    @Override
    public void edit(Integer[] travelItemIds, TravelGroup travelGroup) {
        travelGroupDao.edit(travelGroup);
        Integer travelGroupId = travelGroup.getId();
        travelGroupDao.delete(travelGroupId);
       setTravelGroupAndTravelItem(travelGroupId,travelItemIds);
    }

    @Override
    public List<TravelGroup> findAll() {
        return travelGroupDao.findAll();
    }

    private void setTravelGroupAndTravelItem(Integer id, Integer[] travelItemIds) {
        if(travelItemIds!=null&&travelItemIds.length>0){
            for (Integer itemId : travelItemIds) {
                HashMap<String, Integer> map = new HashMap<>();
                 map.put("travelGroup",id);
                 map.put("travelItem",itemId);
                 travelGroupDao.addTravelGroupAndTravelItem(map);
            }
        }
    }

}
