package com.agony.alarmsystem.controller;

import com.agony.alarmsystem.common.BaseResponse;
import com.agony.alarmsystem.common.ResultUtils;
import com.agony.alarmsystem.exception.ErrorCode;
import com.agony.alarmsystem.exception.ThrowUtils;
import com.agony.alarmsystem.model.dto.UserDTO;
import com.agony.alarmsystem.model.entity.User;
import com.agony.alarmsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Agony
 * @create: 2025/4/21 20:58
 * @describe:
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/create")
    public BaseResponse<User> createUser(@RequestBody UserDTO userDTO) {
        return ResultUtils.success(userService.createUser(userDTO));
    }

    @PostMapping("/update")
    public BaseResponse<User> updateUser(@RequestBody UserDTO userDTO) {
        return ResultUtils.success(userService.updateUser(userDTO));
    }

    @GetMapping("/get/{id}")
    public BaseResponse<User> getUserById(@PathVariable Long id) {
        return ResultUtils.success(userService.getById(id));
    }


    @GetMapping("/list")
    public BaseResponse<List<User>> getAllUsers() {
        return ResultUtils.success(userService.list());
    }

    @DeleteMapping("/delete")
    public BaseResponse<Boolean> deleteUser(Long id) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR, "删除请求参数错误");
        boolean result = userService.removeById(id);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR, "用户删除失败");
        return ResultUtils.success(true);
    }
}
