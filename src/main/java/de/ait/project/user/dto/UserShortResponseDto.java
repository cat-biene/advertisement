package de.ait.project.user.dto;

import de.ait.project.advertisement.dto.AdvertisementResponseDto;
import de.ait.project.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserShortResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    // Этот статический метод преобразует объект User в объект UserResponseDto. Он использует паттерн Builder для создания объекта UserResponseDto, используя данные из объекта User, включая список объявлений пользователя, который преобразуется в список AdvertisementResponseDto.
    public static UserShortResponseDto from(User user) {
        return UserShortResponseDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    // Этот статический метод преобразует список объектов User в список объектов UserResponseDto, используя метод from(User user) для каждого объекта User в списке.
    public static List<UserResponseDto> from(List<User> users) {
        return users.stream()
                .map(u -> UserResponseDto.from(u))
                .collect(Collectors.toList());
    }

}

