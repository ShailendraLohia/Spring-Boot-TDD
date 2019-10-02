package com.example.tdd.Twitter.controller;

import com.example.tdd.Twitter.Exception.TweetNotFoundException;
import com.example.tdd.Twitter.Model.Tweet;
import com.example.tdd.Twitter.Service.TwitterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TwitterController.class)
public class TwitterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TwitterService twitterService;

    private JacksonTester<Tweet> jacksonTester;

    @Before
    public void setUpMock() {
        JacksonTester.initFields(this,objectMapper);
    }

    @Test
    public void test_getTweet() throws Exception {
        given(twitterService.getTweet(anyString())).willReturn(Optional.of(new Tweet("id","Hello")));

        mockMvc.perform(get("/tweet/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("id"))
                .andExpect(jsonPath("message").value("Hello"));
    }

    @Test
    public void test_tweetNotFound() throws Exception {

        given(twitterService.getTweet(anyString())).willThrow(new TweetNotFoundException());
        mockMvc.perform(get("/tweet/123"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_addNewTweet() throws Exception {

        Tweet tweet = new Tweet("123","Hello, There!");
        String tweetJson= jacksonTester.write(tweet).getJson();

        //given(twitterService.addNewTweet(tweet)).willAnswer();
        //willDoNothing().given(twitterService.addNewTweet(any()));
        mockMvc.perform(post("/tweet")
                .content(tweetJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    // Throw validation Error

    @Test
    public void test_invalidTweetData() throws Exception {

        Tweet tweet = new Tweet("","Hello, There!"); //TODO why one arg constructor not working?
        String tweetJson= jacksonTester.write(tweet).getJson();

        mockMvc.perform(post("/tweet")
                .content(tweetJson)
                .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().is4xxClientError()) ;
    }

    // Empty body: TODO: What http status code needs to be returned?
    @Test
    public void test_emptyRequestBody() throws Exception {

        mockMvc.perform(post("/tweet"))
                .andExpect(status().isBadRequest());
    }


}
