package com.garagonic.estimationAssistant.old.database.course;

import java.util.List;

public interface CourseRepository{// extends JpaRepository<Course, Integer> {
	
	public List<Course> findByTopicId(Integer topicId);
		
	
	
}
