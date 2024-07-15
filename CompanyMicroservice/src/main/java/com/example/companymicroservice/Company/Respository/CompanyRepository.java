package com.example.companymicroservice.Company.Respository;


import com.example.companymicroservice.Company.Model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
