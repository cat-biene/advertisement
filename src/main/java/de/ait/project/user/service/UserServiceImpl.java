package de.ait.project.user.service;

import de.ait.project.advertisement.dto.AdvertisementRequestDto;
import de.ait.project.advertisement.model.Advertisement;
import de.ait.project.advertisement.repository.AdvertisementRepository;
import de.ait.project.exceptions.GeneralUnCheckedException;
import de.ait.project.exceptions.UserNotFoundException;
import de.ait.project.user.dto.UserRequestDto;
import de.ait.project.user.dto.UserResponseDto;
import de.ait.project.user.model.User;
import de.ait.project.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Эта аннотация говорит Spring, что этот класс является сервисом, который может быть автоматически обнаружен и внедрен в другие классы как зависимость.
@Service
// Эта аннотация генерирует конструктор, который внедряет зависимости, помеченные как final, в этот класс.
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {
    // Эти поля представляют репозитории для работы с сущностями User и Advertisement. Они помечены как final, поэтому должны быть инициализированы в конструкторе.
    private final UserRepository userRepository;
    private final AdvertisementRepository advertisementRepository;


    // Этот метод возвращает список всех пользователей, преобразуя их в список UserResponseDto, используя метод from класса UserResponseDto.
    @Override
    public List<UserResponseDto> findAll() {
        return UserResponseDto.from(userRepository.findAll());
    }

    // Этот метод находит пользователя по заданному id, или выбрасывает исключение UserNotFoundException, если пользователь не найден. Затем он преобразует найденного пользователя в UserResponseDto.
    @Override
    public UserResponseDto findById(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("Author id" + id + " not found"));
        return UserResponseDto.from(user);
    }

    // Этот метод сохраняет нового пользователя, используя данные из UserRequestDto, преобразуя их в объект User с помощью метода toUser, и затем преобразует созданного пользователя в UserResponseDto.
    @Override
    public UserResponseDto save(UserRequestDto userRequestDto) {
        User createdUser = userRepository.save(UserRequestDto.toUser(userRequestDto));
        return UserResponseDto.from(createdUser);
    }

    // Этот метод добавляет новое объявление для пользователя с заданным userId. Он сначала находит пользователя по его id, затем создает новое объявление, используя данные из AdvertisementRequestDto, связывая его с найденным пользователем и сохраняя объявление в репозитории. Затем он возвращает данные пользователя в виде UserResponseDto.
    @Override
    public UserResponseDto addAdvertisement(Long userId, AdvertisementRequestDto advertisementRequestDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        Advertisement advertisement = Advertisement.builder()
                .title(advertisementRequestDto.getTitle())
                .description(advertisementRequestDto.getDescription())
                .category(advertisementRequestDto.getCategory())
                .author(user)
                .build();
        advertisementRepository.save(advertisement);

        return UserResponseDto.from(user);
    }
}
