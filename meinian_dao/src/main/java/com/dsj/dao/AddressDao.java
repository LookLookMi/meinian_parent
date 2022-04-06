package com.dsj.dao;

import com.dsj.entity.QueryPageBean;
import com.dsj.pojo.Address;
import com.github.pagehelper.Page;

import java.util.List;

public interface AddressDao {
    List<Address> findAllMaps();


    Page findPage(String queryString);

    void add(Address address);

    void deleteById(Integer id);
}
