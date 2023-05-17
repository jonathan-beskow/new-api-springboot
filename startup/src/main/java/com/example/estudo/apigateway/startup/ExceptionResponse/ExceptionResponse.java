package com.example.estudo.apigateway.startup.ExceptionResponse;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {

    private static final Long serialVersionUID = 1L;
    private Date timeStamp;
    private String message;
    private String details;

    public ExceptionResponse(Date timeStamp, String message, String details) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }

    public static Long getSerialversionuid() {
        return serialVersionUID;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    

}
