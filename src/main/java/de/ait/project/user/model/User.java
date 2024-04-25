package de.ait.project.user.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import de.ait.project.advertisement.model.Advertisement;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

// Эта аннотация генерирует конструктор, принимающий все аргументы для всех полей класса.
@AllArgsConstructor
// Эта аннотация генерирует конструктор без аргументов.
@NoArgsConstructor
// Эти аннотации генерируют геттеры и сеттеры для всех полей класса.
@Getter
@Setter
// Эта аннотация генерирует паттерн Builder для класса, что позволяет создавать экземпляры класса с более читаемым и гибким кодом.
@Builder
// Эта аннотация указывает, что класс является сущностью, которая будет отображаться в базе данных.
@Entity
// Эта аннотация указывает на таблицу базы данных, которая будет отображать эту сущность. В данном случае, сущность будет отображаться в таблице с именем "t_user".
@Table(name="t_user")

public class User {

    // Эти аннотации определяют первичный ключ для сущности. @Id обозначает поле как первичный ключ, @GeneratedValue указывает способ генерации значений для этого поля (в данном случае - автоматически увеличиваемое значение), а @Column указывает имя столбца в базе данных.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    // Эти аннотации обозначают отношение "один ко многим" между сущностями User и Advertisement. @OneToMany указывает, что один пользователь может иметь много объявлений. mappedBy = "author" указывает, что поле author в классе Advertisement отображает это отношение. @JsonBackReference предотвращает бесконечную рекурсию при сериализации в JSON, обеспечивая игнорирование этого поля при сериализации.
    @OneToMany(mappedBy = "author")
    @JsonBackReference
    private List<Advertisement> advertisements;

}
