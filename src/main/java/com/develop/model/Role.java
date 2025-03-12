package com.develop.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
	ADMIN,
    USER;
    
    @JsonCreator
    public static Role fromString(String value) {
        if ("ROLE_ADMIN".equalsIgnoreCase(value)) {
            return ADMIN;
        } else if ("ROLE_USER".equalsIgnoreCase(value)) {
            return USER;
        }
        throw new IllegalArgumentException("Rol no válido: " + value);
    }

    @JsonValue
    public String toValue() {
        return "ROLE_" + this.name();
    }
}
