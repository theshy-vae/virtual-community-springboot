package com.baidu.tieba.mapper;

import com.baidu.tieba.model.entity.User;
import com.baidu.tieba.model.vo.PostVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;

@Resource
public interface UserMapper extends BaseMapper<User> {
    Page<PostVO> init(Page<PostVO> page,@Param("username") String username);

    User selectByUsername(@Param("username") String username);
}
