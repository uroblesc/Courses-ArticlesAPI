package com.develop.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.develop.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, UUID> {
	List<Article> findByCourseId(UUID courseId);
    Optional<Article> findByTitle(String title);
}
