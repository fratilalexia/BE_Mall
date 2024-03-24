package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="store")
public class Store {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   Long id;

   @Column(name = "name")
   @NonNull
   private String name;

   @Column(name = "maxCustomers")
   @NonNull
   private Integer maxCustomers;

   @Column(name = "profit")
   @NonNull
   private Double profit;

   @OneToMany(cascade = CascadeType.ALL,
           orphanRemoval = true,
           mappedBy = "store",
           fetch = FetchType.EAGER)
   @JsonIgnore
   private List<Salesperson> salespersons;

   @OneToMany(cascade = CascadeType.ALL,
           orphanRemoval = true,
           mappedBy = "store",
           fetch = FetchType.EAGER)
   @JsonIgnore
   private List<Transaction> transactions;
}
