package com.baidu.tieba.service;



import com.baidu.tieba.model.entity.Post;
import com.baidu.tieba.model.entity.Tag;
import com.baidu.tieba.model.vo.MyTagVO;
import com.baidu.tieba.model.vo.ParentTagVO;
import com.baidu.tieba.model.vo.PostVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface TagService extends IService<Tag> {
    /**
     * 获取标签关联话题
     *
     * @param topicPage
     * @param id
     * @return
     */
    Page<Post> selectTopicsByTagId(Page<Post> topicPage, String id);

    Tag getTagByTagName(String tagName);
    /**
     * 获取贴吧帖子
     */
    Page<PostVO> getList(Page<PostVO> page,String tab,String search,String tagName);

    /**
     * 获取热门贴吧
     */
    List<Tag> getHotTags();

    /**
     * 获取用户关注的贴吧
     */
    List<ParentTagVO> getParentTags(String userId);
    /**
     * 获取用户创建的吧
     */
    List<MyTagVO> listMyTag(String username);
}
