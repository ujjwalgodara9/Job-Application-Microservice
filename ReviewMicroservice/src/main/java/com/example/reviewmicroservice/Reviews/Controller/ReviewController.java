package com.example.reviewmicroservice.Reviews.Controller;

import com.example.reviewmicroservice.Reviews.Model.Review;
import com.example.reviewmicroservice.Reviews.Service.ReviewService;
import com.example.reviewmicroservice.Reviews.messaging.ReviewMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewMessageProducer reviewMessageProducer;



    @GetMapping
    public ResponseEntity<List<Review>> getReviews(@RequestParam Long companyId ) {
        List<Review> review = reviewService.getallreview(companyId);

        if (review == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestBody Review review, @RequestParam Long companyId) {
        boolean added = reviewService.addreview(review, companyId);

        if(!added){
            return new ResponseEntity<>("Not able to add review",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        System.out.println("review producer");
        reviewMessageProducer.sendMessage(review);

        return new ResponseEntity<>("Added review",HttpStatus.CREATED);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview( @PathVariable Long reviewId) {
        Review review = reviewService.getallreviewbyreviewid(reviewId);

        if (review == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PostMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId, @RequestBody Review review) {
        boolean updated = reviewService.updatereview(reviewId, review);

        if(!updated){
            return new ResponseEntity<>("Error while Updating Review",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Updated Review",HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview( @PathVariable Long reviewId) {
        boolean deleted = reviewService.deletereview(reviewId);

        if(!deleted){
            return new ResponseEntity<>("Error while Deleting",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Deleted Review",HttpStatus.OK);
    }

    @GetMapping("/averageRating")
    public Double getAverageReview(@RequestParam Long companyId){
        List<Review> reviewList = reviewService.getallreview(companyId);
        return reviewList.stream().mapToDouble(Review::getRating).average().orElse(0.0);
    }

}
