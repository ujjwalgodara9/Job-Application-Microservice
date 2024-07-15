package com.example.companymicroservice.Company.messaging;

import com.example.companymicroservice.Company.DTO.ReviewMessage;
import com.example.companymicroservice.Company.Service.CompanyService;
import com.netflix.discovery.converters.Auto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageConsumer {

    @Autowired
    private CompanyService companyService;



    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage) {
        companyService.updatecompanyRating(reviewMessage);
    }
}
