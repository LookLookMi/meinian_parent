package com.dsj.dao;

import com.dsj.pojo.Setmeal;
import com.dsj.pojo.TravelGroup;
import com.github.pagehelper.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SetmealDao {
    void addSetmealAndTravelGroup(HashMap<String, Integer> map);

    void add(Setmeal setmeal);

    Page findPage(String queryString);

    List getSetmeal();

    Setmeal findById(Integer id);

    Setmeal getSetmealById(Integer id);

    List<Map> getSetmealReport();
}
