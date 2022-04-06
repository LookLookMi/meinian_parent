package com.dsj.service;

import com.dsj.entity.PageResult;
import com.dsj.entity.QueryPageBean;
import com.dsj.pojo.Address;

import java.util.List;

/**
 * Author    DSJ
 * date:   2022/4/5 15:47
 **/
public interface AddressService {
    List<Address> findAllMaps();

    PageResult findPage(QueryPageBean queryPageBean);

    void add(Address address);

    void deleteById(Integer id);
}
