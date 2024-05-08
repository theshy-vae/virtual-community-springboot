package com.baidu.tieba.service.impl;

import cn.hutool.core.date.DateTime;
import com.baidu.tieba.common.api.ValidationEnum;
import com.baidu.tieba.mapper.UserTagMapper;
import com.baidu.tieba.mapper.ValidationMapper;
import com.baidu.tieba.model.entity.UserTag;
import com.baidu.tieba.model.entity.Validation;
import com.baidu.tieba.service.ValidationService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.injector.methods.Insert;
import com.baomidou.mybatisplus.core.injector.methods.SelectOne;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 86166
 * @version 1.0
 * @description TODO
 * @date 2023/2/24 21:35
 */
@Service
public class ValidationServiceImpl extends ServiceImpl<ValidationMapper, Validation> implements ValidationService {

    @Resource
    ValidationService validationService;


    @Override
    public void saveCode(String email, String code, Integer type, DateTime expireDate) {
        //若数据库中，则删除
        LambdaQueryWrapper<Validation> wrapper = new LambdaQueryWrapper<>();
        Validation one = validationService.getOne(wrapper);
        if(one!=null) remove(wrapper);

        Validation validation = new Validation();
        validation.setEmail(email);
        validation.setCode(code);
        validation.setType(type);
        validation.setTime(expireDate);
        save(validation);
    }
}
