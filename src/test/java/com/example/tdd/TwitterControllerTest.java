package com.example.tdd;

import com.example.tdd.Twitter.Exception.TweetNotFoundException;
import com.example.tdd.Twitter.Model.Tweet;
import com.example.tdd.Twitter.Service.TwitterService;
import com.example.tdd.Twitter.controller.TwitterController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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

    @MockBean
    private TwitterService twitterService;

    @Test
    public void test_addNewMessage() throws Exception {
        given(twitterService.getTweet(anyString())).willReturn(new Tweet("id","Hello"));

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
}
