package com.seehope.service;

import com.seehope.dao.ProMapper;
import com.seehope.entity.Pro;
import com.seehope.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ProService {
    SqlSession sqlSession= MyBatisUtil.getSession();
    ProMapper proMapper=sqlSession.getMapper(ProMapper.class);

    public Pro findId(int proid){
        return proMapper.findProbyId(proid);
    }

    public List<Pro> findAllPros(){
        return proMapper.findAllPros();
    }

    public void delPro(int id){
        proMapper.delPro(id);
        sqlSession.commit();
    }

    public void addPro(Pro pro){
        proMapper.addPro(pro);
        sqlSession.commit();
    }

    public void updatePro(Pro pro){
        proMapper.updatePro(pro);
        sqlSession.commit();
    }




}
