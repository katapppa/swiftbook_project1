package springcourse.dao.Book;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import springcourse.models.Book;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    public List<Book> selectBooks() {
        return jdbcTemplate.query("SELECT * FROM Book ORDER BY id", new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional<Book> selectBook(int id) {

        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new BeanPropertyRowMapper<>(Book.class), id)
                .stream().findAny();
    }

    public void createBook(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, author, year, status, status_reason) VALUES(?, ?, ?, ?, ?)",
                book.getName(),
                book.getAuthor(),
                book.getYear(),
                book.getStatus().name(),
                book.getStatus_reason());
    }

    public void updateBook(int id, Book book) {
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year=?, status=?, status_reason=? WHERE id=?",
                book.getName(),
                book.getAuthor(),
                book.getYear(),
                book.getStatus().name(),
                book.getStatus_reason(),
                id);
    }
}
