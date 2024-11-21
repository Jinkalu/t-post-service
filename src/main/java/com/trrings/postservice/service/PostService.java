package com.trrings.postservice.service;

import com.trrings.postservice.vo.PostCreationVO;
import com.trrings.postservice.vo.PostResVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    PostResVO create(PostCreationVO request, List<MultipartFile> file);
}
