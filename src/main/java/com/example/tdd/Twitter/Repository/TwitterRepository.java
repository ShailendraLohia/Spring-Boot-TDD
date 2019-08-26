package com.example.tdd.Twitter.Repository;

import com.example.tdd.Twitter.Model.Tweet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TwitterRepository extends CrudRepository<Tweet,String> {
}
