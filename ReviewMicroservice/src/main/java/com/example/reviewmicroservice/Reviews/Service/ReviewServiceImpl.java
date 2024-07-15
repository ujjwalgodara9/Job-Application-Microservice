package com.example.reviewmicroservice.Reviews.Service;

import com.example.reviewmicroservice.Reviews.Model.Review;
import com.example.reviewmicroservice.Reviews.Respository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

//    @Autowired
//    CompanyService companyService;


    @Override
    public List<Review> getallreview(@PathVariable Long companyId) {
        Optional<List<Review>> review = reviewRepository.findByCompanyId(companyId);

        if (review.isPresent()) {
            return review.orElse(null);
        }

        return null;

    }

    @Override
    public Review getallreviewbyreviewid( Long reviewId) {

//        List<Review> reviews = getallreview();
//        if (reviews != null) {
//            return reviews.stream()
//                    .filter(review -> review.getId().equals(reviewId))
//                    .findFirst()
//                    .orElse(null);
//
//        }

        Optional<Review> review = reviewRepository.findById(reviewId);

        if (review.isPresent()) {
            return review.get();
        }

        return null;
    }

    @Override
    public boolean addreview(Review review, Long companyId) {
        //Company company=reviewRepository.getcompanybyId(companyId);
        if(review.getCompanyId()!=null) {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        return false;

    }

    @Override
    public boolean updatereview( Long reviewId, Review review) {
        //Company company=companyService.getcompanybyId(companyId);
//        Review new_review=getallreviewbyreviewid(companyId,reviewId);
//        if(new_review!=null){
//            Review getreview = Review.builder()
//                    .id(new_review.getId())
//                    .description((review.getDescription()))
//                    .title(review.getTitle())
//                    .rating(review.getRating())
//                    .company(review.getCompany())
//                    .build();
//            reviewRepository.save(getreview);
//            return true;
//        }
//        return false;

        Review existingReview = reviewRepository.findById(reviewId).get();
        if (existingReview != null) {
            Review updatedReview = Review.builder()
                    .id(existingReview.getId())
                    .description(review.getDescription() != null ? review.getDescription() : existingReview.getDescription())
                    .title(review.getTitle() != null ? review.getTitle() : existingReview.getTitle())
                    .rating(review.getRating() != 0 ? review.getRating() : existingReview.getRating())
                    .companyId(existingReview.getCompanyId()) // Keep the existing company association
                    .build();
            reviewRepository.save(updatedReview);
            return true;
        }
        return false;
    }

    @Override
    public boolean deletereview( Long reviewId) {
        //Company company=companyService.getcompanybyId(companyId);
        Review review=reviewRepository.findById(reviewId).orElse(null);
        if(review!=null){
            reviewRepository.delete(review);
            return true;
        }
        return false;
    }
}
