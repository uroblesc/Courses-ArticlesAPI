package com.develop.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.develop.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
	Optional<Course> findByTitle(String title);
	List<Course> findByTitleContaining(String keyword);
}
