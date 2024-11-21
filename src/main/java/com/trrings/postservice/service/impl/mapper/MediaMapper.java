package com.trrings.postservice.service.impl.mapper;

import com.trrings.postservice.entity.Media;
import com.trrings.postservice.vo.PostResVO;

public class MediaMapper {

    public static PostResVO.MediaVO mediaResponse(Media media) {
        return PostResVO.MediaVO
                .builder()
                .mediaId(media.getId())
                .mediaType(media.getMediaType())
                .filename(media.getUrl())
                .postId(media.getPost().getId())
                .build();
    }
}
