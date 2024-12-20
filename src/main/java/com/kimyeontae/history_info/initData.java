package com.kimyeontae.history_info;

import com.kimyeontae.history_info.topic.Topic;
import com.kimyeontae.history_info.topic.TopicRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class initData {

    @Autowired
    private TopicRepository topicRepository;

    @PostConstruct
    public void init() {
        topicRepository.save(new Topic("절대왕정기", "중세 후기부터 근세까지 약 3세기간 왕에게 권력이 집중되는 현상을 일컫는 역사학계의 용어다. 근래는 프랑스 혁명 이후 부르주아 자본주의 체제에서 구제도를 가리키는 앙시앵 레짐과 같이 군주를 폭정의 핵심으로 지목하면서 사용한 용어이나, 후에 역사학계에서 단어를 차용해서 근세에 서구에서 진행된 중앙집권화 경향을 가리키는 말로 사용되었다."));
        topicRepository.save(new Topic("제국주의", "제국주의는 열강들이 강한 경제력과 군사력을 앞세워 다른 나라에 대해 정치, 경제 및 문화적 지배력을 확대하려는 사상과 그러한 사상을 바탕으로 한 정책을 의미한다. 따라서 동아시아의 '황제가 다스리는 나라'라는 뜻의 제국과는 의미가 다르다."));
        topicRepository.save(new Topic("제 1차 세계대전", "제1차 세계 대전의 원인은 100년 간의 평화 시대 속에서 지속적인 팽창을 이룬 유럽 열강들의 제국주의적 팽창 정책과 그 과정에서 소외된 독일 제국으로 대표되는 신흥 제국들의 불만, 유럽 내 민족주의적 갈등 등 다수의 요소들이 복합적으로 섞여있다"));
        topicRepository.save((new Topic("제 2차 세계대전", "제2차 세계 대전은 1939년 나치 독일의 폴란드 침공에 의해 발발하여 나치 독일, 파시스트 이탈리아, 일본 제국 3국을 중심으로 한 추축국과, 이에 미국과 영국, 소련, 중화민국, 프랑스가 이끄는 연합국이 맞서면서 1945년 일본 제국이 항복할 때까지 총 6년 동안 이어진 전쟁이다.")));

    }
}
