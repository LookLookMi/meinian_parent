package com.dsj.dao;

import com.dsj.pojo.TravelItem;
import com.github.pagehelper.Page;

import java.util.List;

public interface TravelItemDao {
    void add(TravelItem travelItem);

    Page findPage(String queryString);

    void delete(Integer id);


    TravelItem getById(Integer id);

    void edit(TravelItem travelItem);

    List<TravelItem> findAll();

    long findCountByTravelitemId(Integer id);

    List<TravelItem> findTravelItemById(Integer id);
}
