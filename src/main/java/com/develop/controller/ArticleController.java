package com.develop.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.develop.model.Article;
import com.develop.service.ArticleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
@Tag(name = "Article Controller", description = "Endpoints para la gestión de artículos")
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/courses/{courseId}")
    @Operation(summary = "Agregar un artículo a un curso específico")
    @ApiResponse(responseCode = "201", description = "Artículo creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    public ResponseEntity<Article> addArticleToCourse(
            @PathVariable UUID courseId, 
            @Valid @RequestBody Article article) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(articleService.addArticleToCourse(courseId, article));
    }

    @GetMapping("/courses/{courseId}")
    @Operation(summary = "Listar todos los artículos de un curso")
    @ApiResponse(responseCode = "200", description = "Artículos obtenidos exitosamente")
    @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    public ResponseEntity<List<Article>> getArticlesByCourse(@PathVariable UUID courseId) {
        return ResponseEntity.ok(articleService.getArticlesByCourseId(courseId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener los detalles de un artículo específico")
    @ApiResponse(responseCode = "200", description = "Artículo obtenido exitosamente")
    @ApiResponse(responseCode = "404", description = "Artículo no encontrado")
    public ResponseEntity<Article> getArticleById(@PathVariable UUID id) {
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    @GetMapping
    @Operation(summary = "Buscar artículos por título")
    @ApiResponse(responseCode = "200", description = "Búsqueda de artículos realizada exitosamente")
    @ApiResponse(responseCode = "404", description = "No se encontraron artículos")
    public ResponseEntity<Optional<Article>> searchArticlesByTitle(@RequestParam String keyword) {
        return ResponseEntity.ok(articleService.searchArticlesByTitle(keyword));
    }
}