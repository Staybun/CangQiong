package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetMealDishMapper {

    /**
     * 根据菜品id查询套餐id
     * @param dishids
     * @return
     */
    List<Long> getSetmealIdsByDishIds(List<Long> dishids);

    /**
     * 批量删除套餐和菜品的关联数据
     * @param setmealId
     */
    void deleteBySetmealId(Long setmealId);

    /**
     * 根据套餐id查询套餐和菜品的关联数据
     * @param id
     * @return
     */
    List<SetmealDish> getBySetmealId(Long id);

    /**
     * 批量插入套餐和菜品的关联数据
     * @param setmealDishes
     */
    void insertBatch(List<SetmealDish> setmealDishes);
}
