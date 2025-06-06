package com.crhistianm.springboot.jpa.springboot_jpa.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * Audit
 */
@Embeddable
public class Audit {

    @Column(name = "create_at")
    private LocalDateTime createAt;

    
}
