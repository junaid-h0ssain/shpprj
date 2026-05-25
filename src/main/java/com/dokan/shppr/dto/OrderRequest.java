package com.dokan.shppr.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {

    @NotBlank(message = "Customer name is required")
    private String customerName;

    @Email(message = "Customer email must be valid")
    @NotBlank(message = "Customer email is required")
    private String customerEmail;

    @NotBlank(message = "Customer status is required")
    private String customerStatus;

    @NotEmpty(message = "Order items are required")
    @Valid
    private List<OrderItemsRequest> orderItems;
}

