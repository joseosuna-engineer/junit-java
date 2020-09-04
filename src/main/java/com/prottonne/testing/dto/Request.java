package com.prottonne.testing.dto;

public class Request {

    private String fileName;
    private String base64;

    public Request() {
        super();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    @Override
    public String toString() {
        return "Request{" + "fileName=" + fileName + ", base64=" + base64 + '}';
    }

}
