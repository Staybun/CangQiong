package com.sky.controller.admin;

import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * 营业状态管理-管理端
 */
@RestController
@Slf4j
@RequestMapping("/admin/shop")
public class ShopController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public static final String KEY = "SHOP_STATUS";

    /**
     * 设置营业状态
     * @param status
     * @return
     */
    @PutMapping("/{status}")
    public Result setStatus(@PathVariable Integer status){
        log.info("设置店铺状态：{}", status == 1 ? "营业中" : "打烊中");
        redisTemplate.opsForValue().set("KEY", status);
        return Result.success();
    }

    /**
     * 获取营业状态
     * @return
     */
    @GetMapping("/status")
    public Result<Integer> getStatus(){
        Integer status = (Integer) redisTemplate.opsForValue().get("KEY");
        log.info("获取店铺状态：{}", status == 1 ? "营业中" : "打烊中");
        return Result.success(status);
    }
}
