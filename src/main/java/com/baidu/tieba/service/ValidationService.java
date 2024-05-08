package com.baidu.tieba.service;

import cn.hutool.core.date.DateTime;
import com.baidu.tieba.model.entity.Validation;
import com.baomidou.mybatisplus.extension.service.IService;


public interface ValidationService extends IService<Validation> {
    /***
    * @description 保存邮箱与验证码的绑定条目
    * @param email
     * @param code
     * @param code1
     * @param offsetMinute 验证码五分钟过期
    */
    public void saveCode(String email, String code, Integer type, DateTime offsetMinute);

}
