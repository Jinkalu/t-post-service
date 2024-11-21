package com.trrings.postservice.entity;

import com.triings.trringscommon.utils.AbstractAuditEntity;
import com.trrings.postservice.enums.PostStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Media extends AbstractAuditEntity {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String mediaType;

    @Enumerated(EnumType.STRING)
    private PostStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}
