package springcourse.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import springcourse.models.enums.BookStatusEnum;

@Data
public class Book {

    private int id;

    @NotEmpty(message = "Book name should not be empty")
    private String name;

    @NotEmpty(message = "Author should not be empty")
    private String author;

    @Min(value = 0, message = "Book year should be more than 0")
    private int year;

    private BookStatusEnum status;

    private String status_reason;
}
