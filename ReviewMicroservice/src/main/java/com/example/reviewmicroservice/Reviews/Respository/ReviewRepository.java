package com.example.reviewmicroservice.Reviews.Respository;


import com.example.reviewmicroservice.Reviews.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<List<Review>> findByCompanyId(Long companyId);
}
