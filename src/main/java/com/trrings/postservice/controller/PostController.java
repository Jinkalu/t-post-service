package com.trrings.postservice.controller;

import com.triings.trringscommon.vo.ResponseVO;
import com.trrings.postservice.service.PostService;
import com.trrings.postservice.vo.PostCreationVO;
import com.trrings.postservice.vo.PostResVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {

/*    @Value("${application.test}")
    private String variable;*/

    private final PostService postService;

    @GetMapping("/create")
    public ResponseVO<PostResVO> create(@RequestPart(required = false,value = "data") PostCreationVO request,
                                        @RequestParam(required = false)List<MultipartFile> file){
        return new ResponseVO<>("SUCCESS", HttpStatus.OK.name(),postService.create(request,file));
    }

    @GetMapping("/get-post")
    public ResponseVO<String> getPost(){
        return new ResponseVO<>("SUCCESS", HttpStatus.OK.name(),"POST_SERVICE : ");
    }

    @GetMapping("/get-all")
    public ResponseVO<?> getAll(){
        return new ResponseVO<>("SUCCESS", HttpStatus.OK.name(),null);
    }
}
