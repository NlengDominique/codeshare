package com.lndev.codeshut.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    @NotBlank(message = "Email est requis")
    @Email(message = "Email invalide")
    private String email;
    @NotBlank(message = "Le nom est requis")
    @Size(min = 3, message = "le nom doit avoir au moins 3 caracteres")
    private String name;
    @NotNull(message = "le salaire est requis")
    @Min(value = 5000, message = "Minimum 5000")
    private Long salary;
}