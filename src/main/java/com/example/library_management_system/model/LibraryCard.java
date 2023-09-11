package com.example.library_management_system.model;

import com.example.library_management_system.Enum.CardStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LibraryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String CardNo;

    @Enumerated(EnumType.STRING)
    CardStatus CardStatus;

    @CreationTimestamp
    Date issueDate;

    @OneToOne
    @JoinColumn
    Student student;

}