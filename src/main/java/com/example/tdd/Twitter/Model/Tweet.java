package com.example.tdd.Twitter.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tweet {

    @NotNull(message="Cannot be Null")
    @NotEmpty(message = "cannot be empty")
    @Id
    private String id;
    private String message;
}
