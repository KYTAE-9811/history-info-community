package com.kimyeontae.history_info.postTopic;

import com.kimyeontae.history_info.posts.Posts;
import com.kimyeontae.history_info.topic.Topic;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class PostTopic {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "postTopic")
    private List<Posts> posts;

    @OneToMany(mappedBy = "postTopic")
    private List<Topic> topics;
}
