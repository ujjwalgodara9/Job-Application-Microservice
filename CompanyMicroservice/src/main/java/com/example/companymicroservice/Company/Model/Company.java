package com.example.companymicroservice.Company.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double Rating;

//    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<Job> jobs;
//
//
//    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<Review> reviews;
}
