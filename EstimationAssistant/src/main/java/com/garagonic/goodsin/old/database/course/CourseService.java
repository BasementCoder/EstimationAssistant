package com.garagonic.estimationAssistant.old.database.course;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
	
//	@Autowired
//	private CourseRepository courseRepository;
  
	
	public List<Course> getAllCourses(Integer topicId){
//		List <Course> courses = new ArrayList<>();
//		courseRepository.findByTopicId(topicId)
//		.forEach(courses::add);
//		return courses;
		return null;
	}
	
	public Course getCourse(int id) {
//		return courseRepository.findById(id).orElse(null);
		return null;
	}

	public void addCourse(Course course) {
//		courseRepository.save(course);
		
	}

	public void updateCourse(Course course) {
//		courseRepository.save(course);
		
	}

	public void deleteCourse(int id) {
//		courseRepository.deleteById(id);
	}
	
}
