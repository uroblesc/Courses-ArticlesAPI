package com.develop.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import com.develop.config.ConfigProperties;
import com.develop.exception.RunTimeException;

/*
 * Clase Generadora de Base de Datos
 */
@Component
public class DBGenerator {
	
	private static final Logger logger = LoggerFactory.getLogger(DBGenerator.class);

	private final ConfigProperties configProperties;
    private final JdbcTemplate jdbcTemplate;
    private final ResourceLoader resourceLoader;
    
    public DBGenerator(ConfigProperties configProperties, JdbcTemplate jdbcTemplate, ResourceLoader resourceLoader) {
        this.configProperties = configProperties;
        this.jdbcTemplate = jdbcTemplate;
        this.resourceLoader = resourceLoader;
    }
    
    @EventListener(ApplicationReadyEvent.class)
    public void executeStatament() {
    	// Verificamos que la ruta del archivo sql sea correcta
    	logger.info("Ejecutando script SQL desde: {}", configProperties.getSQLFilePath());
        try {
        	// Consulta para Validar la existencia de la Tabla previo a generarla
        	Integer countCourse = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM information_schema.tables "
        			+ "WHERE table_schema = 'courses_articles' AND table_name = 'courses'", Integer.class);
        	Integer countArticle = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM information_schema.tables "
        			+ "WHERE table_schema = 'courses_articles' AND table_name = 'articles'", Integer.class);
        	if (countCourse != null && countArticle != null && countCourse > 0 && countArticle > 0) {
                logger.info("Las tablas 'courses' & 'articles' ya existen.");
                return;
            }
        	// Genera la base de datos a partir del archivo sql dentro de una ruta especifica
            Resource resource = resourceLoader.getResource("classpath:" + configProperties.getSQLFilePath());
            ScriptUtils.executeSqlScript(getConnection(), resource);
            logger.info("Script SQL ejecutado correctamente.");
        } catch(Exception e) {
        	logger.error("Error al ejecutar el script SQL: {}", e.getMessage(), e);
        } finally {
        	if (getConnection() != null) {
                try {
                    getConnection().close();
                } catch (SQLException ex) {
                    logger.error("Error al cerrar la conexión: {}", ex.getMessage(), ex);
                }
            }
        }
    }
    
    public Connection getConnection() {
    	try {
        	DataSource dataSource = jdbcTemplate.getDataSource();
        	if(dataSource == null) {
        		throw new IllegalStateException("No se Encontro Datasource");
        	}
        	return dataSource.getConnection();
        } catch (SQLException e){
        	throw new RunTimeException("Error en la Conexion con la Base de Datos");
        }
    }

}
