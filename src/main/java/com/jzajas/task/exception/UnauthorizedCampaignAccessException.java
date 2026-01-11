package com.jzajas.task.exception;

public class UnauthorizedCampaignAccessException extends RuntimeException {
    public UnauthorizedCampaignAccessException(String message) {
        super(message);
    }
}
