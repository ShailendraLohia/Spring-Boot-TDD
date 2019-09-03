package com.example.tdd.Vehicles.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error {

    private HttpStatus status;
    private List<String> errorMessages;
    private UUID uuid;
    private Date timestamp;

    public Error(HttpStatus status, List<String> errorMessages, Date timestamp) {
        this.status=status;
        this.errorMessages=errorMessages;
        this.timestamp=timestamp;
        uuid=UUID.randomUUID();
    }



}
