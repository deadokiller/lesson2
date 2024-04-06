package ru.vtb.javapro.homework.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "\"limits\"")
@Data
public class Limit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "\"limit\"", nullable = false)
    private BigDecimal limit;
    @Column(name = "user_id", nullable = false)
    private Long userId;

    public Limit(BigDecimal limit, Long userId) {
        this.limit = limit;
        this.userId = userId;
    }

    public Limit() {
    }
}
