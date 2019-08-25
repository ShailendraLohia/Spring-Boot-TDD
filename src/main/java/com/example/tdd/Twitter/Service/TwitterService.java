package com.example.tdd.Twitter.Service;

import com.example.tdd.Twitter.Model.Tweet;
import org.springframework.stereotype.Service;

@Service
public class TwitterService {
    public Tweet getTweet(String tweetId) {
        return new Tweet("id","Hello");
    }
}
