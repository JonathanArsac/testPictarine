package com.example.testpictarine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class ArticleServiceException extends Exception {

    public ArticleServiceException(String s, RestClientException e) {
        super(s, e);
    }

}
