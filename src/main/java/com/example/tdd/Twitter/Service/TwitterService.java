package com.example.tdd.Twitter.Service;

import com.example.tdd.Twitter.Model.Tweet;
import com.example.tdd.Twitter.Repository.TwitterRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TwitterService {

    //@Autowired
    private TwitterRepository twitterRepository;

    public TwitterService(TwitterRepository twitterRepository) {
        this.twitterRepository=twitterRepository;
    }

    public Optional<Tweet> getTweet(String tweetId) throws Exception{
         Tweet tweet= twitterRepository.findById(tweetId).get();
         if (tweet==null)
             throw new NotFoundException("Tweet Not Found");
        return Optional.of(tweet);
        //return new Tweet("id","Hello");
    }

    public void addNewTweet(Tweet tweet) {
        twitterRepository.save(tweet);
        return;
    }
}
