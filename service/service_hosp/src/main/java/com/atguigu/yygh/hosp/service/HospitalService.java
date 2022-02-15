package com.atguigu.yygh.hosp.service;

import com.atguigu.yygh.model.hosp.Hospital;
import com.atguigu.yygh.vo.hosp.HospitalQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface HospitalService {
    //上传医院接口
    void save(Map<String, Object> parameterMap);

    Hospital getByHoscode(String hoscode);

    //医院列表(条件查询分页)
    Page<Hospital> selectHospPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo);

    //更新医院上限状态
    void updateStatus(String id, Integer status);

    //医院详情信息
    Map<String,Object> getHospById(String id);
}
