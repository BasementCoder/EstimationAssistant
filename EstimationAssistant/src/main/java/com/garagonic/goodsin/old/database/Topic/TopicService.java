package com.garagonic.estimationAssistant.old.database.Topic;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TopicService {
	
//	@Autowired
//	private TopicRepository topicRepository;

	private List<Topic> topics =new ArrayList<>( Arrays.asList(
			new Topic(1,"Spring Framework","Spring Framework Description"),
			new Topic(2,"Java Core","Java Core Description"),
			new Topic(3,"JavaScript","JavaScript Description")
	
			));
	
	public List<Topic> getAllTopics(){
//		List <Topic> topics = new ArrayList<>();
//		topicRepository.findAll()
//		.forEach(topics::add);
//		return topics;
		return null;
	}
	
	public Topic getTopic(String id) {
//		return topicRepository.findById(id).orElse(null);
		return null;
	}

	public void addTopic(Topic topic) {
//		topicRepository.save(topic);
		
	}

	public void updateTopic(String id,Topic topic) {
//		topicRepository.save(topic);
		
	}

	public void deleteTopic(String id) {
//		topicRepository.deleteById(id);
	}
	
}
