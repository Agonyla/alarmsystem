package com.agony.alarmsystem.service;

import com.agony.alarmsystem.model.dto.UserDTO;
import com.agony.alarmsystem.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Agony
 * @description 针对表【user(用户)】的数据库操作Service
 * @createDate 2025-04-21 21:50:27
 */
public interface UserService extends IService<User> {
    User createUser(UserDTO userDTO);

    User updateUser(UserDTO userDTO);
}
