package com.exercise.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exercise.RoutingDataSourceContext;
import com.exercise.RoutingWith;
import com.exercise.springboot.entity.Product;
import com.exercise.springboot.mapper.ProductMapper;
import com.exercise.springboot.service.IProductService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author genetor
 * @since 2023-04-28
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Override
    public List<Product> findAllProductDB1() {
        String key = "db1DataSource";
        RoutingDataSourceContext routingDataSourceContext = new RoutingDataSourceContext(key);
        return this.list();
    }

    @Override
    public List<Product> findAllProductDB2() {
        String key = "db2DataSource";
        RoutingDataSourceContext routingDataSourceContext = new RoutingDataSourceContext(key);
        return this.list();
    }

    @RoutingWith(value = "db1DataSource")
    @Override
    public List<Product> findAllProductDB1_1() {
        return this.list();
    }

    @RoutingWith(value = "db2DataSource")
    @Override
    public List<Product> findAllProductDB2_2() {
        return this.list();
    }

    @Cacheable(cacheNames = {"product"}, key = "#id", condition = "#id>0")
    @Override
    public Product getByCache(Integer id) {
        return this.getById(id);
    }
}
