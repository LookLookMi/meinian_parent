package com.dsj.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dsj.dao.TravelItemDao;
import com.dsj.entity.PageResult;
import com.dsj.pojo.TravelGroup;
import com.dsj.pojo.TravelItem;
import com.dsj.service.TravelItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author    DSJ
 * date:   2022/4/1 14:12
 **/
@Service(interfaceClass = TravelItemService.class)
@Transactional
public class TravelItemServiceImpl implements TravelItemService {
    @Autowired
    private TravelItemDao travelItemDao;

    @Override
    public void add(TravelItem travelItem) {
        travelItemDao.add(travelItem);
    }

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<TravelGroup> page=travelItemDao.findPage(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void delete(Integer id) {
       long count=travelItemDao.findCountByTravelitemId(id);
       if(count>0){
           throw new RuntimeException("删除自由行失败，存在关联数据,请先解除关系");
       }
        travelItemDao.delete(id);
    }

    @Override
    public TravelItem getById(Integer id) {
        return travelItemDao.getById(id);
    }

    @Override
    public void edit(TravelItem travelItem) {
        travelItemDao.edit(travelItem);
    }

    @Override
    public List<TravelItem> findAll() {
        return travelItemDao.findAll() ;
    }

}
