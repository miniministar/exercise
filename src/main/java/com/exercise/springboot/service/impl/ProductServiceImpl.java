package com.exercise.springboot.service.impl;

import com.exercise.springboot.entity.Product;
import com.exercise.springboot.mapper.ProductMapper;
import com.exercise.springboot.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
