package com.example.tdd.Twitter.repository;

import com.example.tdd.Twitter.Model.Tweet;
import com.example.tdd.Twitter.Repository.TwitterRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestTwitterRepository {

    @Autowired
    private TwitterRepository twitterRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void test_findByTweetId() throws Exception {
        Tweet tweet = testEntityManager.persistFlushFind(new Tweet("123","Hello"));

        Tweet tweet1 = twitterRepository.findById("123").get();

        Assertions.assertThat(tweet1.getId().equals("123"));
        Assertions.assertThat(tweet1.getMessage().equals("Hello"));

    }
 }
