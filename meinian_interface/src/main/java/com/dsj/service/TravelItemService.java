package com.dsj.service;

import com.dsj.entity.PageResult;
import com.dsj.pojo.TravelItem;

import java.util.List;

public interface TravelItemService {

    void add(TravelItem travelItem);

    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    void delete(Integer id);

    TravelItem getById(Integer id);

    void edit(TravelItem travelItem);

    List<TravelItem> findAll();
}
