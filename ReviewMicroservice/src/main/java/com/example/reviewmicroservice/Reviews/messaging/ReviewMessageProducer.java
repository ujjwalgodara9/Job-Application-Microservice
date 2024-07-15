package com.example.reviewmicroservice.Reviews.messaging;

import com.example.reviewmicroservice.Reviews.DTO.ReviewMessage;
import com.example.reviewmicroservice.Reviews.Model.Review;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;



    public void sendMessage(Review review) {

        System.out.println("Sending message to Review Topic");
        ReviewMessage reviewMessage = new ReviewMessage();
        reviewMessage.setId(review.getId());
        reviewMessage.setTitle(review.getTitle());
        reviewMessage.setDescription(review.getDescription());
        reviewMessage.setRating(review.getRating());
        reviewMessage.setCompanyId(review.getCompanyId());
        rabbitTemplate.convertAndSend("companyRatingQueue", reviewMessage);
        System.out.println("Message sent to Review Topic");

    }

}
