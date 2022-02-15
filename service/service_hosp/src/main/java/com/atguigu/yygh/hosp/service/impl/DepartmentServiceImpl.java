package com.atguigu.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.yygh.hosp.repository.DepartmentRepository;
import com.atguigu.yygh.hosp.service.DepartmentService;
import com.atguigu.yygh.model.hosp.Department;
import com.atguigu.yygh.vo.hosp.DepartmentQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    //上传科室接口
    @Override
    public void save(Map<String, Object> parameterMap) {
        //parameterMap转换成Department对象
        String map = JSONObject.toJSONString(parameterMap);
        Department department = JSONObject.parseObject(map, Department.class);
        //根据医院编号以及科室编号查询出信息
        Department departmentExist = departmentRepository.getDepartmentByHoscodeAndDepcode(department.getHoscode(),department.getDepcode());
        //根据查询出的信息进行判断
        if (departmentExist!=null){
            department.setUpdateTime(new Date());
            department.setIsDeleted(0);
            departmentRepository.save(department);
        }else {
            department.setCreateTime(new Date());
            department.setUpdateTime(new Date());
            department.setIsDeleted(0);
            departmentRepository.save(department);
        }
    }

    @Override

    //❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤这一整个方法必须掌握，是mongoDb的基本操作
    public Page<Department> findPageDepartment(int page, int limit, DepartmentQueryVo departmentQueryVo) {
        //创建Pageable对象 设置当前页和每页记录数
        //由于0是第一页，page是从1开始的 所以 page-1
        Pageable pageable = PageRequest.of(page-1,limit);
        //创建example对象
        Department department = new Department();
        BeanUtils.copyProperties(departmentQueryVo,department);
        department.setIsDeleted(0);

        ExampleMatcher matcher = ExampleMatcher.matching()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);//withIgnoreCase含义：忽略大小写
        Example<Department> example = Example.of(department,matcher);

        Page<Department> all = departmentRepository.findAll(example, pageable);
        return all;
    }

    //删除医院接口
    @Override
    public void remove(String hoscode, String depcode) {
        //根据 医院编号 和 科室编号 查询出科室信息
        Department department = departmentRepository.getDepartmentByHoscodeAndDepcode(hoscode, depcode);
        if (department!=null){
            //调用方法删除
            departmentRepository.deleteById(department.getId());
        }


    }
}
