package com.trrings.postservice.service.impl;

import com.triings.trringscommon.exception.ApiError;
import com.triings.trringscommon.exception.ValidationException;
import com.triings.trringscommon.utils.CommonUtils;
import com.trrings.postservice.entity.Media;
import com.trrings.postservice.entity.Post;
import com.trrings.postservice.enums.PostStatus;
import com.trrings.postservice.repository.MediaRepository;
import com.trrings.postservice.service.MediaService;
import com.trrings.postservice.service.impl.mapper.MediaMapper;
import com.trrings.postservice.vo.PostResVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;

    @Override
    public List<PostResVO.MediaVO> uploadMedia(Object owner, List<MultipartFile> files) {
        List<Media> mediaList = new ArrayList<>();
        files.forEach(file -> {
            MediaType mediaType = determineMediaType(file);
            String finalFileName = generateFileName(mediaType);
//            awsS3Service.uploadFile(file, finalFileName);
           Media media = createMediaEntity(owner, finalFileName, mediaType);
            mediaList.add(media);
        });
        return mapToMediaResponse(mediaRepository.saveAll(mediaList));
    }


    private List<PostResVO.MediaVO> mapToMediaResponse(List<Media> medias) {
        return medias.stream()
                .map(MediaMapper::mediaResponse).collect(Collectors.toList());
    }

    private Media createMediaEntity(Object owner, String finalFileName, MediaType mediaType) {
        return Media.builder()
                .post(owner instanceof Post ? (Post) owner : null)
//                .comment(owner instanceof CommunityPostComment ? (CommunityPostComment) owner : null)
                .url(finalFileName)
                .mediaType(mediaType.getType())
                .status(PostStatus.PUBLISHED)
                .build();
    }


    private String generateFileName(MediaType mediaType) {
        String newFileName = CommonUtils.generateRandomString() + "_" + System.currentTimeMillis();
        return newFileName + CommonUtils.getFileExtension(mediaType);
    }

    private MediaType determineMediaType(MultipartFile file) {
        MediaType mediaType = CommonUtils.getContentType(Objects.requireNonNull(file.getOriginalFilename()));
        if (mediaType == null) {
            throw new ValidationException(ApiError.builder()
                    .httpStatus(BAD_REQUEST)
                    .code(String.valueOf(BAD_REQUEST.value()))
                    .status(BAD_REQUEST.name())
                    .errors(List.of("Invalid media format"))
                    .build());
        }
        return mediaType;
    }
}
