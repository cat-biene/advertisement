package de.ait.project.user.dto;

import de.ait.project.user.model.User;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Эта аннотация является составной аннотацией, которая генерирует геттеры, сеттеры, методы equals(), hashCode() и toString() для всех полей класса.
@Data
// Эта аннотация генерирует конструктор, принимающий все аргументы для всех полей класса.
@AllArgsConstructor
// Эта аннотация генерирует конструктор без аргументов.
@NoArgsConstructor
// Эта аннотация генерирует паттерн Builder для класса, что позволяет создавать экземпляры класса с более читаемым и гибким кодом.
@Builder
public class UserRequestDto {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 20)
    @Pattern(regexp = "^[A-Z][a-zA-Z]*$")
    private String firstName;

    @NotNull
    @Pattern(regexp = "^[A-Z][a-zA-Z]*$")
    private String lastName;

    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 3, max = 20)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]+$")
    private String password;

    // Этот статический метод преобразует объект UserRequestDto в объект User. Он использует паттерн Builder для создания объекта User, используя данные из UserRequestDto.
    public static User toUser(UserRequestDto dto) {
        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }

}
