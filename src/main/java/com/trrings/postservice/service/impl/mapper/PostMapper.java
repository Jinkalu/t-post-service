package com.trrings.postservice.service.impl.mapper;

import com.triings.trringscommon.entity.Users;
import com.triings.trringscommon.mapper.UserMapper;
import com.trrings.postservice.entity.Post;
import com.trrings.postservice.enums.PostStatus;
import com.trrings.postservice.vo.PostCreationVO;
import com.trrings.postservice.vo.PostResVO;
import org.apache.commons.lang3.StringUtils;

public class PostMapper {

    public static Post mapToPostEntity(PostCreationVO request, Users user) {
        return Post.builder()
                .content(StringUtils.isEmpty(request.getContent()) ? null : request.getContent())
                .user(user)
                .location(StringUtils.isEmpty(request.getLocation()) ? null : request.getLocation())
                .postType(request.getPostType())
                .status(PostStatus.PUBLISHED)
                .build();
    }

    public static PostResVO mapToPostResponse(Post post, Users user) {
        return PostResVO.builder()
                .postId(post.getId())
                .userDetails(UserMapper.mapToUserVO(user))
                .content(StringUtils.isEmpty(post.getContent()) ? null : post.getContent())
                .location(StringUtils.isEmpty(post.getLocation()) ? null : post.getLocation())
                .status(post.getStatus())
                .createdAt(post.getCreatedAt())
                .build();
    }
}
