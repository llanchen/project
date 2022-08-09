package com.seehope.service;

import com.seehope.dao.CateMapper;
import com.seehope.entity.Cate;
import com.seehope.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CateService {
    SqlSession sqlSession= MyBatisUtil.getSession();
    CateMapper cateMapper=sqlSession.getMapper(CateMapper.class);


    public List<Cate> findCates() {
        return  cateMapper.findCates();
    }

    public void addCate(Cate cate){
        cateMapper.addCate(cate);
         sqlSession.commit();
    }
}
