package com.seehope.dao;

import com.seehope.entity.Pro;

import java.util.List;

public interface ProMapper {
     List<Pro> findAllPros();

     Pro findProbyId(int proid);

     void delPro(int id);

     void addPro(Pro pro);

     void updatePro(Pro pro);
}
