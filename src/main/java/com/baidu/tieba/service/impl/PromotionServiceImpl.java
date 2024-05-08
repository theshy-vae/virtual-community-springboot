package com.baidu.tieba.service.impl;

import com.baidu.tieba.mapper.PromotionMapper;
import com.baidu.tieba.model.entity.Promotion;
import com.baidu.tieba.service.PromotionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PromotionServiceImpl extends ServiceImpl<PromotionMapper, Promotion> implements PromotionService {
}
