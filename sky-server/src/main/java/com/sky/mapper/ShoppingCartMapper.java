package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    /**
     * 插入购物车数据
     * @param cart
     */
    List<ShoppingCart> list(ShoppingCart cart);

    /**
     * 插入购物车数据
     * @param cart
     */
    void updateNumberById(ShoppingCart cart);

    /**
     * 插入购物车数据
     * @param cart
     */
    void insert(ShoppingCart cart);

    /**
     * 根据用户id删除购物车数据
     * @param currentId
     */
    void deleteByUserId(Long currentId);

    /**
     * 根据id删除购物车数据
     * @param id
     */
    void deleteById(Long id);

    /**
     * 批量插入购物车数据
     * @param shoppingCartList
     */
    void insertBatch(List<ShoppingCart> shoppingCartList);
}
