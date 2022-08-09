package com.seehope.dao;

import com.seehope.entity.Mso;
import com.seehope.entity.Msoxq;

import java.util.List;

public interface MsoMapper {
    public List<Mso> findAllMsos();

    //查询指定编号的订单信息（含订单明细）
     Mso findMsoByMsoid(String msoid);

     void delMso(String msoid);
     void delMsoxq(String msoid);

    //修改订单的发货状态
     void updateMsoDeliveryState(String msoid);

}
