package com.kimyeontae.history_info.topic;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;

    // 토픽 찾아오기
    public Topic findTopic(Long id) {
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Topic not found"));
        return topic;
    }

    // 모든 토픽 가져오기
    public List<Topic> AllTopics() {
        List<Topic> topics = topicRepository.findAll();
        return topics;
    }

}
