package com.sg.kafka.excpetion;

public class InSufficientCreditScoreException extends RuntimeException {

    public InSufficientCreditScoreException(String message) {
        super(message);
    }
}