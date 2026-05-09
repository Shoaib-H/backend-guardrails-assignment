package com.example.guardrails.entity;

import jakarta.persistence.*;

@Entity
public class Bot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String personaDescription;

    public Long getId() {
        return id;
    }

    public String getId(Long id) {
        return null;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonaDescription() {
        return personaDescription;
    }

    public void setPersonaDescription(String personaDescription) {
        this.personaDescription = personaDescription;
    }
}