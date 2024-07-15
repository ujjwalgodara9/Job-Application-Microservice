package com.example.reviewmicroservice.Reviews.Service;


import com.example.reviewmicroservice.Reviews.DTO.ReviewMessage;
import com.example.reviewmicroservice.Reviews.Model.Review;

import java.util.List;

public interface ReviewService {
   

    List<Review> getallreview(Long companyId);

    Review getallreviewbyreviewid( Long reviewId);

    boolean addreview(Review review,Long companyId);

    boolean updatereview( Long reviewId, Review review);

    boolean deletereview( Long reviewId);


}
