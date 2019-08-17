package wang.ismy.travel.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author MY
 */
@Data
public class User {

    private Integer uid;

    private String username;

    private String password;

    private String name;

    private LocalDate birthday;

    private String sex;

    private String telephone;

    private String email;

    private String status;

    private String code;
}
