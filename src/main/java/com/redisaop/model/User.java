package com.redisaop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User implements Serializable

{

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="first_name")

    private String firstName;

    @Column(name="last_name")

    private String LastName;

    @Column(name="email",unique = true)

    private String email;

    @Column(name="date_of_birth")

    private LocalDate  dateOfBirth;

    @Column(name="created_date")
    @JsonIgnore

    private LocalDate createdDate = LocalDate.now();

    @Column(name = "phone")

    private String phone;

    @Column(name="nationality_id")

    private Long nationalityId;

}
