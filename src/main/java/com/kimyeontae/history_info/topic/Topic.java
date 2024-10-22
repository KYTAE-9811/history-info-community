package com.kimyeontae.history_info.topic;

import com.kimyeontae.history_info.postTopic.PostTopic;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Topic {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "postTopic_id")
    private PostTopic postTopic;

    /*
    연관관계 편의 메서드
     */
    public void setPostTopic(PostTopic postTopic) {
        this.postTopic = postTopic;
    }

    /*
    생성 메서드
     */
    public Topic(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
