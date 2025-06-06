package com.crhistianm.springboot.jpa.springboot_jpa.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;

/**
 * Audit
 */
@Embeddable
public class Audit {

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @PrePersist
    public void prePersist(){
        System.out.println("Evento del ciclo de ciclo de vida del entity pre persist");
        this.createAt = LocalDateTime.now();
    }
    
}
