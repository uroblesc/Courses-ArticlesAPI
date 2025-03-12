package com.develop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Clase de Configuracion para Variables Dinamicas
 */
@Component
@ConfigurationProperties(prefix = "app")
public class ConfigProperties {
	
	@Value("${app.files.sqlfile-path}")
    private String sqlFilePath;
	
	@Value("${app.jwt.secret-key}")
	private String secretKey;
	
	@Value("${app.jwt.expiration-time}")
	private long expirationTime;
	
	public String getSQLFilePath() {
		return sqlFilePath;
	}
	
	public String getSecretKey() {
		return secretKey;
	}
	
	public long getExpirationTime() {
		return expirationTime;
	}

}
