package com.book.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Appointment implements Serializable {

    private int readerId;
    private String name;
    private String author;
    private String publish;
    private Date appoint_time;
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public void setAppoint_time(Date appoint_time) {
        this.appoint_time = appoint_time;
    }

    public int getReaderId() {
        return readerId;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublish() {
        return publish;
    }

    public Date getAppoint_time() {
        return appoint_time;
    }

}
