package com.dokan.shppr.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String customer_name;

    @Column(nullable = false)
    private String customer_email;

    @Column(nullable = false)
    private String customer_status;

    @Column(nullable = false)
    private BigDecimal total_price;

    @Column(nullable = false)
    private LocalDateTime order_date;

    @PrePersist
    public void prePersist() {
        this.order_date = LocalDateTime.now();
    }
}