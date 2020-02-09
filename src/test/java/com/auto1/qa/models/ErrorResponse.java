package com.auto1.qa.models;

public class ErrorResponse {
    private String timestamp;
    private int status;
    private String error;
    private String exception;
    private String message;
    private String trace;
    private String path;

    // Getter Methods

    public String getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getException() {
        return exception;
    }

    public String getMessage() {
        return message;
    }

    public String getTrace() {
        return trace;
    }

    public String getPath() {
        return path;
    }

    // Setter Methods

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public void setPath(String path) {
        this.path = path;
    }
}