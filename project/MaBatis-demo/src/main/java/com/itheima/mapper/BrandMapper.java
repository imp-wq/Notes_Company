package com.itheima.mapper;

import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BrandMapper {

    List<Brand> selectAll();

    Brand selectById(int id);

    /**
     * 多条件查询
     */
    List<Brand> selectByCondition(@Param("status") int status, @Param("companyName") String companyName, @Param("brandName") String brandName);

    List<Brand> selectByCondition(Brand brand);

    List<Brand> selectByCondition(Map map);

    /**
     * 单条件动态查询
     */
    List<Brand> selectBySingleCondition(Brand brand);


}
