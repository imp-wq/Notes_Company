package com.itheima.test;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBatisTest {

    @Test
    public void testSelectAll() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        List<Brand> brands = brandMapper.selectAll();
        System.out.println(brands);

        sqlSession.close();
    }

    @Test
    public void testSelectById() throws Exception {
        int id = 1;

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        Brand brand = brandMapper.selectById(id);
        System.out.println(brand);

        sqlSession.close();
    }

    @Test
    public void testSelectByCondition() throws Exception {
        int status = 1;
        String companyName = "华为";
        String brandName = "华为";

        // 处理参数，用于模糊匹配
        companyName = "%" + companyName + "%";
        brandName = "%" + brandName + "%";

        // 封装参数对象
        // Brand brand = new Brand();
        // brand.setStatus(1);
        // brand.setCompanyName(companyName);
        // brand.setBrandName(brandName);

        // 构造map
        Map<String, Object> map = new HashMap<>();
        // map.put("status", status);
        map.put("companyName", companyName);
        map.put("brandName", brandName);

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 以散装参数形式，@Params注解指定变量
        // List<Brand> brands = brandMapper.selectByCondition(status, companyName, brandName);
        // 以对象形式传入参数，参数名与sql中变量对应
        // List<Brand> brands = brandMapper.selectByCondition(brand);
        // 以Map形式传入参数，key为sql中的变量，value为传入的值。
        List<Brand> brands = brandMapper.selectByCondition(map);

        System.out.println(brands);

        sqlSession.close();
    }

    @Test
    public void testSelectBySingleCondition() throws Exception {
        int status = 1;
        String companyName = "华为";
        String brandName = "华为";

        // 处理参数，用于模糊匹配
        companyName = "%" + companyName + "%";
        brandName = "%" + brandName + "%";

        // 封装参数对象
        Brand brand = new Brand();
        //brand.setStatus(status);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 以对象形式传入参数，参数名与sql中变量对应
        List<Brand> brands = brandMapper.selectBySingleCondition(brand);

        System.out.println(brands);

        sqlSession.close();
    }

    @Test
    public void testAdd() throws Exception {
        int status = 1;
        String companyName = "菠萝手机";
        String brandName = "菠萝";
        String description = "手机中的战斗机";
        int ordered = 100;

        // 封装参数对象
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);
        brand.setDescription(description);
        brand.setOrdered(ordered);

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 以对象形式传入参数，参数名与sql中变量对应
        brandMapper.add(brand);
        // 获取主键
        System.out.println("id为：" + brand.getId());
        // 需要手动提交事务
        sqlSession.commit();

        sqlSession.close();
    }
}
