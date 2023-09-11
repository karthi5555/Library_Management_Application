package com.example.library_management_system.model;


import com.example.library_management_system.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int regNo;

    String name;

    int age;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @Column(unique = true, nullable = false)
    String emailId;

    @OneToOne(mappedBy = "student")
    LibraryCard libraryCard;

}