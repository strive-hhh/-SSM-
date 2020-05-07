package com.book.domain;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class  Book implements Serializable{

    private long bookId;
    private String name;
    private String pic;
    private int stock;
    private String author;
    private String publish;
    private String isbn;
    private String introduction;
    private String language;
    private BigDecimal price;
    private Date pubdate;
    private int classId;
    private int state;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic=pic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public long getBookId() {
        return bookId;
    }

    public int getClassId() {
        return classId;
    }

    public Date getPubdate() {
        return pubdate;
    }

    public String getAuthor() {
        return author;
    }

    public String getIntroduction() {
        return introduction;
    }

    public int getStock(){
        return stock;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getLanguage() {
        return language;
    }

    public int getState() {
        return state;
    }

    public String getPublish() {
        return publish;
    }

    @Override
    public String toString() {
        return "这本书的信息为"+stock+pubdate+bookId+name+author+publish+isbn+introduction+language+price+classId+state;
    }
}
