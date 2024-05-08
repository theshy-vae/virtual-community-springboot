package com.baidu.tieba.service.impl;

import com.baidu.tieba.mapper.DanmuMapper;
import com.baidu.tieba.model.entity.Danmu;
import com.baidu.tieba.service.DanmuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class DanmuServiceImpl extends ServiceImpl<DanmuMapper, Danmu> implements DanmuService {
}
