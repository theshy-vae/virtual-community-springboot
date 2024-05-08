package com.baidu.tieba.mapper;

import com.baidu.tieba.model.entity.Post;
import com.baidu.tieba.model.vo.PostVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface TopicMapper extends BaseMapper<Post> {
    /**
     * 分页查询首页话题列表
     * <p>
     *
     * @param page
     * @param tab
     * @return
     */
    Page<PostVO> selectListAndPage(Page<PostVO> page, @Param("tab") String tab,@Param("search") String search);

    List<Post> getRecommend(@Param("id") String id);
}
