package wang.ismy.travel.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Favorite {

    private Integer rid;

    private Integer uid;

    private LocalDate date;
}
