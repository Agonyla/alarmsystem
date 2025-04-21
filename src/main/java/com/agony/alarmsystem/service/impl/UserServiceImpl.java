package com.agony.alarmsystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.agony.alarmsystem.exception.ErrorCode;
import com.agony.alarmsystem.exception.ThrowUtils;
import com.agony.alarmsystem.mapper.UserMapper;
import com.agony.alarmsystem.model.dto.UserDTO;
import com.agony.alarmsystem.model.entity.User;
import com.agony.alarmsystem.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Agony
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2025-04-21 21:50:27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User createUser(UserDTO userDTO) {
        ThrowUtils.throwIf(userDTO == null, ErrorCode.PARAMS_ERROR, "用户请求为空");

        User user = new User();
        BeanUtil.copyProperties(userDTO, user);

        int id = userMapper.insert(user);
        // boolean result = this.(user);
        ThrowUtils.throwIf(id <= 0, ErrorCode.OPERATION_ERROR, "用户创建失败");
        return user;
    }

    @Override
    public User updateUser(UserDTO userDTO) {
        User user = new User();
        BeanUtil.copyProperties(userDTO, user);
        boolean result = this.updateById(user);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR, "用户更新失败");
        return user;
    }
}




