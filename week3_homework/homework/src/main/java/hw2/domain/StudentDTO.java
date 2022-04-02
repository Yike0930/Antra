package hw2.domain;

import lombok.Data;


@Data
public class StudentDTO {
    private int id;
    private String full_name;


    public StudentDTO(Student stu) {
        this.id = stu.getId();
        this.full_name = stu.getName();
    }
}
