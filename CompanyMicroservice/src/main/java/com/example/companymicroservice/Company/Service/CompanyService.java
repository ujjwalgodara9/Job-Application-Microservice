package com.example.companymicroservice.Company.Service;


import com.example.companymicroservice.Company.DTO.ReviewMessage;
import com.example.companymicroservice.Company.Model.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getallcompany();

    Company getcompanybyId(Long id);

    boolean addcomapny(Company company);

    boolean updatecompany(Long id, Company company);

    boolean deletecompanybyid(Long id);

    void updatecompanyRating(ReviewMessage reviewMessage);
}
