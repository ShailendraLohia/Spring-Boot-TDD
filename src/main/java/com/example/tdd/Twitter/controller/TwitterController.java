package com.example.tdd.Twitter.controller;

import com.example.tdd.Twitter.Model.Tweet;
import com.example.tdd.Twitter.Service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tweet")
public class TwitterController {

    @Autowired
    private TwitterService twitterService;

    @GetMapping("/{tweetId}")
    public ResponseEntity<Tweet> addNewTweet(@PathVariable String tweetId) {

        return new ResponseEntity<>(twitterService.getTweet(tweetId)
                ,HttpStatus.OK);

    }
}
