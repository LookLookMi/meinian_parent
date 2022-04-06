package com.dsj.service;

import com.dsj.entity.PageResult;
import com.dsj.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealService {

    void add(Integer[] travelgroupIds, Setmeal setmeal);

    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    List getSetmeal();

    Setmeal findById(Integer id);

    Setmeal getSetmealById(Integer id);

    List<Map> getSetmealReport();
}
