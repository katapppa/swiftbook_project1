package springcourse.dao.Person;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import springcourse.models.Person;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    public List<Person> selectPeople() {

        return jdbcTemplate.query("SELECT * FROM Person ORDER BY id", new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> selectPerson(int id) {

        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new BeanPropertyRowMapper<>(Person.class), id)
                .stream().findAny();
    }

    public void createPerson(Person person) {

        jdbcTemplate.update("INSERT INTO Person(fio, birthdayyear, email) VALUES(?, ?, ?)",
                person.getFio(),
                person.getBirthdayYear(),
                person.getEmail());
    }

    public void updatePerson(int id, Person person) {

        jdbcTemplate.update("UPDATE Person SET fio=?, birthdayyear=?, email=? WHERE id=?",
                person.getFio(),
                person.getBirthdayYear(),
                person.getEmail(),
                id);
    }
}
