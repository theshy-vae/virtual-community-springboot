package com.baidu.tieba.service;

import com.baidu.tieba.model.dto.LoginDTO;
import com.baidu.tieba.model.dto.RegisterDTO;
import com.baidu.tieba.model.entity.User;
import com.baidu.tieba.model.vo.PostVO;
import com.baidu.tieba.model.vo.ProfileVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {
    User executeRegister(RegisterDTO dto);

    User getUserByUsername(String username);

    String executeLogin(LoginDTO dto);

    String executeAdminLogin(LoginDTO dto);

    Page<PostVO> init(Page<PostVO> page,String username);

    ProfileVO initUser(String username);

    //发送邮箱验证码
    int sendEmailCode(String email);


}
