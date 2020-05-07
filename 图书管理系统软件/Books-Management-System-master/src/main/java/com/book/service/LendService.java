package com.book.service;

import com.book.dao.LendDao;
import com.book.domain.Lend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LendService {
    private LendDao lendDao;

    @Autowired
    public void setLendDao(LendDao lendDao) {
        this.lendDao = lendDao;
    }

    public boolean bookReturn(long bookId,long sernum){
        return lendDao.stockIncreaseOne(bookId)>0&&lendDao.bookReturnOne(bookId)>0&&lendDao.bookStateOne(sernum)>0;
    }

    public boolean bookLend(long bookId,int readerId){
        return lendDao.bookLendOne(bookId,readerId)>0&&lendDao.stockDecreaseOne(bookId)>0&&lendDao.bookShouldBack()>0;
    }

    public ArrayList<Lend> lendList(){
        return lendDao.lendList();
    }

    public int deleteLendlist(long sernum){
        return lendDao.deleteLendlist(sernum);
    }

    public ArrayList<Lend> myLendList(int readerId){
        return lendDao.myLendList(readerId);
    }

    public boolean authorityReaderLendMax(int readerId){
        return lendDao.readerLendMax(readerId) < lendDao.authorityLendMax(readerId);
    }
}
