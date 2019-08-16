package com.apirestwithmongodb.response;

public class UserResponse {

    private String _id;
    private String name;
    private String mail;
    private String password;
    private String status;

    // GETTERS AND SETTERS

    public String getId() { return _id; }

    public void setId(String id) { this._id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) { this.status = status; }

}
