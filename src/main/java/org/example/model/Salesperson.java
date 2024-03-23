package org.example.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="salesperson")
public class Salesperson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "firstName")
    @NonNull
    private String firstName;

    @Column(name = "lastName")
    @NonNull
    private String lastName;

    @Column(name = "payCheck")
    @NonNull
    private Double payCheck;

    @ManyToOne
    @JoinColumn(name = "storeId")
    private Store store;

}
