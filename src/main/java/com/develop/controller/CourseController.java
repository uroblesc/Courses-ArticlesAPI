package com.develop.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.develop.model.Course;
import com.develop.service.CourseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping("/courses")
@Tag(name = "Course Controller", description = "Endpoints para la gestión de cursos")
public class CourseController {

    private final CourseService courseService;
    public CourseController(CourseService courseService) {
    	this.courseService = courseService;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo curso")
    @ApiResponse(responseCode = "201", description = "Curso creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    public ResponseEntity<Course> createCourse(@RequestBody @Valid Course course) {
    	System.out.println("Received course: " + course);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(course));
    }

    @GetMapping
    @Operation(summary = "Obtener la lista de cursos con opciones de búsqueda")
    @ApiResponse(responseCode = "200", description = "Cursos obtenidos exitosamente")
    @ApiResponse(responseCode = "404", description = "No se encontraron cursos")
    public ResponseEntity<List<Course>> getAllCourses(@RequestParam(required = false) String keyword) {
        List<Course> courses = (keyword == null) 
            ? courseService.getAllCourses() 
            : courseService.searchCoursesByTitle(keyword);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener los detalles de un curso específico")
    @ApiResponse(responseCode = "200", description = "Curso obtenido exitosamente")
    @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    public ResponseEntity<Course> getCourseById(@PathVariable UUID id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar información de un curso")
    @ApiResponse(responseCode = "200", description = "Curso actualizado exitosamente")
    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    public ResponseEntity<Course> updateCourse(@PathVariable UUID id, @Valid @RequestBody Course course) {
        return ResponseEntity.ok(courseService.updateCourse(id, course));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un curso")
    @ApiResponse(responseCode = "200", description = "Curso eliminado exitosamente")
    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    public ResponseEntity<Void> deleteCourse(@PathVariable UUID id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
