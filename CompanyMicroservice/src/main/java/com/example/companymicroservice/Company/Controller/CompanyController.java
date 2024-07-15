package com.example.companymicroservice.Company.Controller;

import com.example.companymicroservice.Company.Model.Company;
import com.example.companymicroservice.Company.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getCompany() {
        List<Company> companyList = companyService.getallcompany();

        if(companyList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(companyList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.getcompanybyId(id);

        if(company==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(company,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody Company company) {
        boolean companyadded = companyService.addcomapny(company);

        if (companyadded) {
            return new ResponseEntity<>("Company added successfully",HttpStatus.OK);
        }

        return new ResponseEntity<>("Company not added",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        boolean updated=companyService.updatecompany(id,company);

        if (updated) {
            return new ResponseEntity<>("Company updated successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Company not updated",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        boolean deleted=companyService.deletecompanybyid(id);

        if (deleted) {
            return new ResponseEntity<>("Company deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Company not deleted",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
