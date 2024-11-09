package springcourse.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingHistory {

    private int id;

    private int person_id;

    private int book_id;

    private LocalDateTime bookingStart;

    private LocalDateTime bookingEnd;
}
