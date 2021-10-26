package com.bosonit.springboot.db2.exception;

import java.util.Date;

public class CustomError {
    private Date timestamp;
    private int httpCode;
    private String mensaje;


    public CustomError(Date timestamp, int httpCode, String message) {
        super();
        this.timestamp = timestamp;
        this.httpCode = httpCode;
        this.mensaje = message;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public Date getTime() {
        return timestamp;
    }

    public String getMensaje() {
        return mensaje;
    }
}