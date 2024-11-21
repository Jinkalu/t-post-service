package com.trrings.postservice.entity;

import com.triings.trringscommon.entity.Users;
import com.triings.trringscommon.utils.AbstractAuditEntity;
import com.trrings.postservice.enums.PostStatus;
import com.trrings.postservice.enums.PostType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@SuperBuilder
@Table(name = "post")
public class Post extends AbstractAuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uid")
    private Users user;
    private String content;
    private String location;
    @Enumerated(EnumType.STRING)
    private PostType postType;
    @Enumerated(EnumType.STRING)
    private PostStatus status;
    @OneToMany(mappedBy = "post")
    private Set<Media> medias;

}
