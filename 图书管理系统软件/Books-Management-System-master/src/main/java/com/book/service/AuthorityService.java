package com.book.service;

import com.book.dao.AuthorityDao;
import com.book.domain.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthorityService {

    private AuthorityDao authorityDao;
    @Autowired
    public void setAuthorityDao(AuthorityDao authorityDao) {
        this.authorityDao = authorityDao;
    }

    public ArrayList<Authority> getAuthorities(){
        return authorityDao.getAllAuthority();
    }

    public Authority getAuthority(String name){
        return authorityDao.findAuthorityByname(name);
    }

    public boolean deleteAuthority(String name){
        return authorityDao.deleteAuthority(name)>0;
    }

    public boolean editAuthority(Authority authority){
        return authorityDao.editAuthority(authority)>0;
    }
    public boolean addAuthority(Authority authority){
        return  authorityDao.addAuthority(authority)>0;
    }
}
