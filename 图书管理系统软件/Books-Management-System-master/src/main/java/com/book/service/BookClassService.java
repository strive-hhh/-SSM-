package com.book.service;


import com.book.dao.BookClassDao;
import com.book.domain.BookClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookClassService {

    private BookClassDao bookClassDao;

    @Autowired
    public void setBookClassDao(BookClassDao bookClassDao) {
        this.bookClassDao= bookClassDao;
    }

    public ArrayList<BookClass> getAllBookClass(){
        return bookClassDao.getAllBookClass();
    }

    public BookClass getBookClass(int id){
        return bookClassDao.findBookClassById(id);
    }

    public boolean deleteBookClass(int id){
        return bookClassDao.deleteBookClass(id)>0;
    }

    public boolean editBookClass(BookClass bookClass){
        return bookClassDao.editBookClass(bookClass)>0;
    }
    public boolean addBookClass(BookClass bookClass){
        return  bookClassDao.addBookClass(bookClass)>0;
    }
}
