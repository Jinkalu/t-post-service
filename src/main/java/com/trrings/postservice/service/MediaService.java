package com.trrings.postservice.service;

import com.trrings.postservice.entity.Post;
import com.trrings.postservice.vo.PostResVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {

    List<PostResVO.MediaVO> uploadMedia(Object post, List<MultipartFile> file);
}
