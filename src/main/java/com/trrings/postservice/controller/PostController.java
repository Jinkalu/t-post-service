package com.trrings.postservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.triings.trringscommon.vo.ResponseVO;
import com.trrings.postservice.service.PostService;
import com.trrings.postservice.vo.PostCreationVO;
import com.trrings.postservice.vo.PostResVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {
    private static final Logger log = LoggerFactory.getLogger(PostController.class);

/*    @Value("${application.test}")
    private String variable;*/

    private final PostService postService;

    @GetMapping("/create")
    public ResponseVO<PostResVO> create(@RequestPart(required = false, value = "data") PostCreationVO request,
                                        @RequestParam(required = false) List<MultipartFile> file) {
        return new ResponseVO<>("SUCCESS", HttpStatus.OK.name(), postService.create(request, file));
    }

    @GetMapping("/get-post")
    public ResponseVO<String> getPost() {
        return new ResponseVO<>("SUCCESS", HttpStatus.OK.name(), "POST_SERVICE : ");
    }

    @GetMapping("/get-all")
    public ResponseVO<?> getAll() {
        return new ResponseVO<>("SUCCESS", HttpStatus.OK.name(), null);
    }


    @PostMapping(value = "/upload-multiple", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFiles(
            @RequestPart(value = "files", required = false) List<MultipartFile> files,
            @RequestPart(value = "data", required = false) String rawData) {

        ObjectMapper objectMapper = new ObjectMapper();
        PostCreationVO data = null;
        try {
            if (rawData != null) {
                data = objectMapper.readValue(rawData, PostCreationVO.class);
            }
        } catch (JsonProcessingException e) {
            log.error("Failed to parse JSON data", e);
            return ResponseEntity.badRequest().body("Invalid JSON format");
        }

        StringBuilder fileNames = new StringBuilder();
        if (files != null) {
            files.forEach(file -> fileNames.append(file.getOriginalFilename()).append(", "));
        }
        log.info("Parsed Data: {}", data != null ? data.toString() : "No data");
        return ResponseEntity.ok("Files uploaded successfully: " + fileNames);
    }


/*    @PostMapping(value = "/upload-multiple", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFiles(@RequestPart("files") @ArraySchema(schema = @Schema(type = "string",
            format = "binary",
            description = "Files to upload",
            implementation = MultipartFile.class)) List<MultipartFile> files,
                                              @RequestPart(value = "data", required = false)
                                              @Schema(description = "JSON data", implementation = PostCreationVO.class)
                                              PostCreationVO data) {
        StringBuilder fileNames = new StringBuilder();
        files.forEach(file -> fileNames.append(file.getOriginalFilename()).append(", "));
        log.info("requestBody : : {}",data.toString());
        return ResponseEntity.ok("Files uploaded successfully: " + fileNames.toString());
    }*/


}
