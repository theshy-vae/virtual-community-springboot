package com.baidu.tieba.mapper;

import com.baidu.tieba.model.entity.Tag;
import com.baidu.tieba.model.vo.MyTagVO;
import com.baidu.tieba.model.vo.ParentTagVO;
import com.baidu.tieba.model.vo.PostVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface TagMapper extends BaseMapper<Tag> {
    Page<PostVO> getList(Page<PostVO> page, String tab, String search, List<String> list);

    List<Tag> getHotTags();

    List<ParentTagVO> getParentTags(String userId);

    List<MyTagVO> listMyTag(String username);
}
