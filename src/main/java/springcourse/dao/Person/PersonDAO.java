package springcourse.dao.Person;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import springcourse.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    public List<Person> index() {
        List<Person> people = new ArrayList<>();


        return people;
    }

//    public void save(Person person) {
//
//        try {
//            String SQL = String.format("INSERT INTO Person values(%d, '%s', %d, '%s')", ++PEOPLE_COUNT, person.getName(), person.getAge(), person.getEmail());
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
