package springcourse.models;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class Person {

    private int id;

    @NotEmpty(message = "Fio should not be empty")
    @Pattern(regexp = "([A-Z]\\w+)(, [A-Z]\\w+)((, [A-Z]\\w+)|($))", message = "FIO is not valid to pattern Name, Surname, Patronymic (optional)")
    private String fio;

    @Min(value = 1900, message = "Birthday year should be greater than 1900")
    private int birthdayYear;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;
}
