package com.trrings.postservice.vo;


import com.triings.trringscommon.vo.UserVO;
import com.trrings.postservice.enums.PostStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResVO {
    private Long postId;
    private UserVO userDetails;
    private String content;
    private String location;
    @Enumerated(EnumType.STRING)
    private PostStatus status;
    private List<PostResVO.MediaVO> mediaDetails;
    private Long createdAt;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MediaVO {
        private Long mediaId;
        private Long postId;
        private String url;
        private String filename;
        private String mediaType;
    }
}
