package com.digitaldot.employer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.digitaldot.employer.service.validator.ValidField;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(
        "user"
)
public class User {

    private String id;
    private String username;
    @ValidField(message = "Digite um email válido", type = ValidField.TypeValid.EMAIL)
    private String email;
    private String password;
    @JsonProperty("terms")
    private boolean acceptedTerms;
    @JsonProperty("active")
    private boolean isActive;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAcceptedTerms() {
        return acceptedTerms;
    }

    public void setAcceptedTerms(boolean acceptedTerms) {
        this.acceptedTerms = acceptedTerms;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
