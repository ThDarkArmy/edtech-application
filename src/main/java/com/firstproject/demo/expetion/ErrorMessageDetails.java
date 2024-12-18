package com.firstproject.demo.expetion;

public class ErrorMessageDetails {
    private int statusCode;
    private String errorMessage;
    private Object errorBody;

    public ErrorMessageDetails() {
    }

    public ErrorMessageDetails(int statusCode, String errorMessage, Object errorBody) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
        this.errorBody = errorBody;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getErrorBody() {
        return errorBody;
    }

    public void setErrorBody(Object errorBody) {
        this.errorBody = errorBody;
    }
}
