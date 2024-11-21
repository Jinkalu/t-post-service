package com.trrings.postservice.service.impl;

import com.triings.trringscommon.entity.Users;
import com.triings.trringscommon.enums.UserStatus;

import com.trrings.postservice.entity.Post;
import com.trrings.postservice.repository.PostRepository;
import com.trrings.postservice.repository.users.UsersRepository;
import com.trrings.postservice.service.MediaService;
import com.trrings.postservice.service.PostService;
import com.trrings.postservice.service.impl.mapper.PostMapper;
import com.trrings.postservice.vo.PostCreationVO;
import com.trrings.postservice.vo.PostResVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UsersRepository usersRepository;
    private final MediaService mediaService;

    @Override
    public PostResVO create(PostCreationVO request, List<MultipartFile> files) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = usersRepository.findByEmailAndStatus(name, UserStatus.ACTIVE)
                .orElseThrow();
        Post post = postRepository.save(PostMapper.mapToPostEntity(request, user));
        List<PostResVO.MediaVO> mediaResponse = new ArrayList<>();
        if (!CollectionUtils.isEmpty(files) &&
                (Objects.nonNull(files.getFirst()) || !files.getFirst().isEmpty())) {
            mediaResponse = mediaService.uploadMedia(post, files);
        }
        PostResVO postResponse = PostMapper.mapToPostResponse(post, user);
        postResponse.setMediaDetails(mediaResponse);
        return postResponse;
    }


}
