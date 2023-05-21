package com.geekbrains.lesson_4;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(schema = "experiments", name = "students")
@NamedQueries({
        @NamedQuery(name = "Student.findById", query = "SELECT s FROM Student AS s WHERE s.id = :id"),
        @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student AS s"),
        @NamedQuery(name = "Student.deleteById", query = "DELETE FROM Student AS s WHERE s.id = :id"),
})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "mark")
    private BigDecimal mark;


}

