package com.kimyeontae.history_info.like;
import com.kimyeontae.history_info.users.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(
        name = "like_table",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "target_id", "target_type"}
                )
        }
)
public class Like {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(name = "target_id", nullable = false)
    private Long targetId; // (post나 comment)의 ID

    @Column(name = "target_type", nullable = false)
    private String targetType; // "POST" 또는 "COMMENT"

    private LocalDateTime likedAt;

    // 연관관계 편의 메서드

    // 생성 메서드
    public Like(Users user, Long targetId, String targetType) {
        this.user = user;
        this.targetId = targetId;
        this.targetType = targetType;
        this.likedAt = LocalDateTime.now();
    }

    // 초기에는 이것도 성능이 좋으나, 나중에는 post_like나 comment_like로 분리하는게 성능이 좋아질 수 있음
}
