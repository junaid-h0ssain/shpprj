package com.dokan.shppr.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name="products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required")
    @Column(nullable = false)
    private String name;

    private String description;

    @NotBlank(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price cannot be below 0.0")
    @Column(nullable = false)
    private BigDecimal price;

    @Min(value = 0, message = "stock can't be less than 0")
    @Column(nullable = false)
    private Integer stock_quantity;
}


