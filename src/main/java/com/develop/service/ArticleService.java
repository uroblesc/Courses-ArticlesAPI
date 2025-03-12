package com.develop.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.develop.model.Article;
import com.develop.model.Course;
import com.develop.repository.ArticleRepository;
import com.develop.repository.CourseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CourseRepository courseRepository;

    public Article addArticleToCourse(UUID courseId, Article article) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Curso No Encontrado con ID: " + courseId));

        article.setCourse(course);
        return articleRepository.save(article);
    }

    public List<Article> getArticlesByCourseId(UUID courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new EntityNotFoundException("Curso No Identificado con ID: " + courseId);
        }
        return articleRepository.findByCourseId(courseId);
    }

    public Article getArticleById(UUID articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(() -> new EntityNotFoundException("Article No Encontrado con ID: " + articleId));
    }

    public Optional<Article> searchArticlesByTitle(String tittle) {
        return articleRepository.findByTitle(tittle);
    }
}
