package com.dokan.shppr.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
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

    @OneToMany(mappedBy = "order")
    private List<OrderItems> order_items;
}