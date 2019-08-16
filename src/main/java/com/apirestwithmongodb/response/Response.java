package com.apirestwithmongodb.response;

public class Response<T> {

    private T data;
    private String message;
    private String error;

    public Response() {}

    public T getData() { return data; }

    public void setData(T data) { this.data = data; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public String getError() { return error; }

    public void setError(String error) { this.error = error; }

}
