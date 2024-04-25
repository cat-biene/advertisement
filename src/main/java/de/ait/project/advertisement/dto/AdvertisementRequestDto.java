package de.ait.project.advertisement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvertisementRequestDto {

    @NotNull // поле не должно быть нулевым
    @NotBlank // поле-String не должно быть пустой строкой (т. е. оно должно содержать хотя бы один символ)
    @Pattern(regexp = "^[A-Z][a-zA-Z]*$")
    private String category;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Z][a-zA-Z]*$")
    @Size(min = 3, max = 20)
    private String title;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Z][a-zA-Z]*$")
    @Size(min = 20, max = 200)
    private String description;

}
