package com.dsj.dao;

import com.dsj.pojo.TravelGroup;
import com.github.pagehelper.Page;

import java.util.HashMap;
import java.util.List;

public interface TravelGroupDao {
    void add(TravelGroup travelGroup);

    void addTravelGroupAndTravelItem(HashMap<String, Integer> map);

    Page<TravelGroup> findPage(String queryString);

    TravelGroup getById(Integer id);

    List<Integer> getTravelItemIdsByTravelGroupId(Integer travelGroupId);

    void edit(TravelGroup travelGroup);

    void delete(Integer travelGroupId);

    List<TravelGroup> findAll();

    List<TravelGroup> findTravelGroupById(Integer id);

}
