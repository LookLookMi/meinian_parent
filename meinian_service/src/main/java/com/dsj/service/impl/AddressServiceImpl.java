package com.dsj.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dsj.dao.AddressDao;
import com.dsj.entity.PageResult;
import com.dsj.entity.QueryPageBean;
import com.dsj.pojo.Address;
import com.dsj.service.AddressService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author    DSJ
 * date:   2022/4/5 15:48
 **/
@Service(interfaceClass = AddressService.class)
@Transactional
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressDao addressDao;

    @Override
    public List<Address> findAllMaps() {
        return addressDao.findAllMaps();

    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
       Page page= addressDao.findPage(queryPageBean.getQueryString());
       PageResult pageResult=new PageResult(page.getTotal(),page.getResult());
        return pageResult;
    }

    @Override
    public void add(Address address) {
        addressDao.add(address);
    }

    @Override
    public void deleteById(Integer id) {
        addressDao.deleteById(id);
    }
}
