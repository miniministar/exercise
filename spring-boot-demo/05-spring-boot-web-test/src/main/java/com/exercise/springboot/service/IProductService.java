package com.exercise.springboot.service;

import com.exercise.springboot.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author genetor
 * @since 2023-04-28
 */
public interface IProductService extends IService<Product> {

    List<Product> findAllProductDB1();

    List<Product> findAllProductDB2();

    List<Product> findAllProductDB1_1();

    List<Product> findAllProductDB2_2();

    Product getByCache(Integer id);
}
