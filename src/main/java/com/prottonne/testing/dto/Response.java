package com.prottonne.testing.dto;

public class Response {

    private String Message;
    private Boolean uploaded;

    public Response() {
        super();
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public Boolean getUploaded() {
        return uploaded;
    }

    public void setUploaded(Boolean uploaded) {
        this.uploaded = uploaded;
    }

}
