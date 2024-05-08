package com.baidu.tieba.controller;

import com.baidu.tieba.common.api.ApiResult;
import com.baidu.tieba.model.entity.Post;
import com.baidu.tieba.model.entity.Promotion;
import com.baidu.tieba.service.PromotionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/promotion")
public class PromotionController extends BaseController{
    @Resource
    private PromotionService promotionService;

    @GetMapping("/all")
    public ApiResult<List<Promotion>> getPromotions(@RequestParam("key") String key){
        LambdaQueryWrapper<Promotion> w = new LambdaQueryWrapper<>();
        w.like(Promotion::getTitle,key);
        List<Promotion> list = promotionService.list(w);
        return ApiResult.success(list);
    }

    @GetMapping("/deleteP")
    public ApiResult<String> deleteP(@RequestParam("id") String id){
        promotionService.removeById(id);
        return ApiResult.success();
    }

    @PostMapping("/createPromotion")
    public ApiResult<String> createPromotion(@RequestBody Promotion promotion){
        promotionService.save(promotion);
        return ApiResult.success();
    }
}
