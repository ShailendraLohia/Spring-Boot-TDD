package com.example.tdd.Twitter.controller;

import com.example.tdd.Twitter.Model.Tweet;
import com.example.tdd.Twitter.Service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/tweet")
@Validated
public class TwitterController {

    @Autowired
    private TwitterService twitterService;

    @GetMapping("/{tweetId}")
    public ResponseEntity<Tweet> addNewTweet(@PathVariable String tweetId) throws Exception {

        Optional<Tweet> tweet=twitterService.getTweet(tweetId);
        //Tweet t= tweet.get();
        return new ResponseEntity<>(tweet.get(),HttpStatus.OK);



//        return new Optional<ResponseEntity<Tweet>>(twitterService.getTweet(tweetId),HttpStatus.OK)
//        return new Optional.of(ResponseEntity<>(twitterService.getTweet(tweetId)
//                ,HttpStatus.OK));

    }

    @PostMapping
    public ResponseEntity addNewTweet(@Valid @RequestBody Tweet tweet) {
        return new ResponseEntity<>(HttpStatus.CREATED);
        //return HttpStatus.OK;
    }
}
