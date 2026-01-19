package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.vo.UserLoginVO;

/**
 * 微信登录
 * @Param userLoginDTO
 * @return
 */
public interface UserService {
    User wxLogin(UserLoginDTO userLoginDTO);
}
