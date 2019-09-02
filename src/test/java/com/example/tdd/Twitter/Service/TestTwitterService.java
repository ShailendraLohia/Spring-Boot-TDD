package com.example.tdd.Twitter.Service;

import com.example.tdd.Twitter.Model.Tweet;
import com.example.tdd.Twitter.Repository.TwitterRepository;
import javassist.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestTwitterService {

    @Mock
    private TwitterRepository twitterRepository;

    private TwitterService twitterService;

    @Rule
    public ExpectedException expectedException=ExpectedException.none();

    @Before
    public void setUp() {
        twitterService=new TwitterService(twitterRepository);
    }

    @Test
    public void test_getTweet() throws Exception {

        when(twitterRepository.findById(anyString())).
                thenReturn(Optional.of(new Tweet("id","Hello")));

        Optional<Tweet> tweet=twitterService.getTweet("123");
        assertThat(tweet.get().getId().equals("id"));
        assertThat(tweet.get().getMessage().equals("Hello"));

        //Assertions.assertThat(tweet.get().getId().equals("id"));

    }

    @Test
    public void test_tweetNotFound() throws Exception {
        when(twitterRepository.findById(anyString()))
                .thenReturn(null);

        expectedException.expect(NotFoundException.class);

        Tweet tweet=twitterService.getTweet("123").get();
        //Assertions.assertThat(tweet.getId().equals("id"));



    }

    @Test
    public void test_addNewTweet() throws Exception {

        //TwitterService service = mock(TwitterService.class);

        Tweet tweet=new Tweet("id","Hello");
        //doNothing().when(twitterRepository.save(any()));
        twitterService.addNewTweet(tweet);
        verify(twitterRepository).save(tweet); // verify method also mock things. Due to this no use of when method.

    }
}
