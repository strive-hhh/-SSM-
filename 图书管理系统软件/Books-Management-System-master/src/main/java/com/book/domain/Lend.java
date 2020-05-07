package com.book.domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Lend implements Serializable {

    private long sernum;
    private long bookId;
    private int readerId;
    private Date lendDate;
    private Date backshouldDate;
    private Date backDate;
    private int state;

    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public void setBackDate(Date backDate) {
       this.backDate=backDate;
    }

    public void setLendDate(Date lendDate) {
        this.lendDate = lendDate;
    }

    public void setSernum(long sernum) {
        this.sernum = sernum;
    }

    public int getReaderId() {
        return readerId;
    }

    public long getBookId() {
        return bookId;
    }

    public Date getBackDate() {
        return backDate;
    }

    public Date getLendDate() {
        return lendDate;
    }

    public long getSernum() {
        return sernum;
    }

    public Date getBackshouldDate() {
        return backshouldDate;
    }

    public void setBackshouldDate(Date backshouldDate) {
        this.backshouldDate = backshouldDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
