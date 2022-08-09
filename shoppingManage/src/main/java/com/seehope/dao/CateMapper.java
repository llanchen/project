package com.seehope.dao;

import com.seehope.entity.Cate;

import java.util.List;

public interface CateMapper {
    List<Cate> findCates();
    void addCate(Cate cate);
}
