package com.book.service;

import com.book.dao.MyshelfDao;
import com.book.domain.Myshelf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyshelfService {
    private MyshelfDao myshelfDao;

    @Autowired
    public void setMyshelfDao(MyshelfDao myshelfDao) {
        this.myshelfDao = myshelfDao;
    }

    public boolean addMyshelf(Myshelf ms){
        return myshelfDao.addMyshelf(ms)>0;
    }
    public ArrayList<Myshelf> getMyShelves(int readerId){
        return myshelfDao.myShelfList(readerId);
    }
    public boolean deleteMyshelf(long bookId){
        return myshelfDao.deleteMyshelf(bookId)>0;
    }

    public Myshelf myShelfBook(long bookId){
        return myshelfDao.myShelfBook(bookId);
    }
}
