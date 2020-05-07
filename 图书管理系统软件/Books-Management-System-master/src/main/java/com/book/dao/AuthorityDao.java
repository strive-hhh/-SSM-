package com.book.dao;

import com.book.domain.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class AuthorityDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String ALL_AUTHORITY_INFO_SQL="SELECT * FROM authority";
    private final static String GET_READER_INFO_SQL="SELECT * FROM authority where name = ? ";
    private final static String ADD_AUTHORITY_INFO_SQL="INSERT INTO authority VALUES(?,?,?)";
    private final static String DELETE_AUTHORITY_INFO_SQL="DELETE FROM authority where name = ? ";
    private final static String UPDATE_AUTHORITY_INFO_SQL="UPDATE authority set level= ? ,lend_max = ? where name = ? ";


    public ArrayList<Authority> getAllAuthority() {
        final ArrayList<Authority> authorities=new ArrayList<Authority>();
        jdbcTemplate.query(ALL_AUTHORITY_INFO_SQL, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                    Authority authority=new Authority();
                    authority.setName(resultSet.getString("name"));
                    authority.setLevel(resultSet.getInt("level"));
                    authority.setLend_max(resultSet.getInt("lend_max"));

                    authorities.add(authority);
                }
            }
        });
        return authorities;
    }

    public Authority findAuthorityByname(String name){
        final Authority authority=new Authority();
        jdbcTemplate.query(GET_READER_INFO_SQL, new Object[]{name}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                authority.setName(resultSet.getString("name"));
                authority.setLevel(resultSet.getInt("level"));
                authority.setLend_max(resultSet.getInt("lend_max"));
            }
        });
        return authority;
    }

    public int addAuthority(Authority authority){
        String name=authority.getName();
        int level=authority.getLevel();
        int lend_max=authority.getLend_max();

        return jdbcTemplate.update(ADD_AUTHORITY_INFO_SQL,new Object[]{name,level,lend_max});
    }

    public int deleteAuthority(String name){
        return jdbcTemplate.update(DELETE_AUTHORITY_INFO_SQL,name);
    }

    public int editAuthority(Authority authority){
        String name=authority.getName();
        int level=authority.getLevel();
        int lend_max=authority.getLend_max();
        return jdbcTemplate.update(UPDATE_AUTHORITY_INFO_SQL,new Object[]{level,lend_max,name});
    }

}
