package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    /**
     * 插入购物车数据
     * @param shoppingCart
     */
    List<ShoppingCart> list(ShoppingCart cart);

    /**
     * 插入购物车数据
     * @param shoppingCart
     */
    void updateNumberById(ShoppingCart cart);

    /**
     * 插入购物车数据
     * @param shoppingCart
     */
    void insert(ShoppingCart cart);

    /**
     * 根据用户id删除购物车数据
     * @param userId
     */
    void deleteByUserId(Long currentId);
}
