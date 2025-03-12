package com.develop.service;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import com.develop.model.Course;
import com.develop.repository.CourseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService {
	
	private final CourseRepository courseRepository;
    
    public Course createCourse(Course course) {
    	if (course.getTitle() == null || course.getTitle().isEmpty()) {
            throw new IllegalArgumentException("El título no puede ser vacío");
        }
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(UUID id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Curso No Encontrado con ID: " + id));
    }

    public Course updateCourse(UUID id, Course updatedCourse) {
        Course existingCourse = getCourseById(id);
        existingCourse.setTitle(updatedCourse.getTitle());
        existingCourse.setDescription(updatedCourse.getDescription());
        return courseRepository.save(existingCourse);
    }

    public void deleteCourse(UUID id) {
        Course course = getCourseById(id);
        courseRepository.delete(course);
    }

    public List<Course> searchCoursesByTitle(String keyword) {
        return courseRepository.findByTitleContaining(keyword);
    }
}
