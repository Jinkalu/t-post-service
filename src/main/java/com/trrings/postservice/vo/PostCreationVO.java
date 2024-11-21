package com.trrings.postservice.vo;

import com.trrings.postservice.enums.PostType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCreationVO {
    private String content;
    private String location;
    private PostType postType;
}
