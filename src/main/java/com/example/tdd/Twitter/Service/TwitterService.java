package com.example.tdd.Twitter.Service;

import com.example.tdd.Twitter.Model.Tweet;
import com.example.tdd.Twitter.Repository.TwitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TwitterService {

    //@Autowired
    private TwitterRepository twitterRepository;

    public TwitterService(TwitterRepository twitterRepository) {
        this.twitterRepository=twitterRepository;
    }

    public Optional<Tweet> getTweet(String tweetId) {
        return twitterRepository.findById(tweetId);
        //return new Tweet("id","Hello");
    }

    public void addNewTweet(Tweet tweet) {
        twitterRepository.save(tweet);
        return;
    }
}
