package de.ait.project.user.dto;

import de.ait.project.advertisement.dto.AdvertisementResponseDto;
import de.ait.project.advertisement.model.Advertisement;
import de.ait.project.user.model.User;
import de.ait.project.user.repository.UserRepository;
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
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<AdvertisementResponseDto> advertisementDtos;

    // Этот статический метод преобразует объект User в объект UserResponseDto. Он использует паттерн Builder для создания объекта UserResponseDto, используя данные из объекта User, включая список объявлений пользователя, который преобразуется в список AdvertisementResponseDto.
    public static UserResponseDto from(User user){
        return UserResponseDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .advertisementDtos( AdvertisementResponseDto.of(user.getAdvertisements()) )
                .build();
    }

    // Этот статический метод преобразует список объектов User в список объектов UserResponseDto, используя метод from(User user) для каждого объекта User в списке.
    public static List<UserResponseDto> from(List<User> users){
        return users.stream()
                .map(u-> UserResponseDto.from(u))
                .collect(Collectors.toList());
    }

}
