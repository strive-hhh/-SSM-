package com.book.dao;

import com.book.domain.Myshelf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@Repository
public class MyshelfDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String ADD_SHELF_SQL="INSERT INTO myshelf VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    private final static String DELETE_SHELF_SQL="delete from myshelf where book_id = ?  ";
    //private final static String QUERY_ALL_SHELVES_SQL="SELECT * FROM myshelf ";
    private final static String MY_SHELF_LIST_SQL="SELECT * FROM myshelf WHERE reader_id = ? ";

    //为了放入书架后显示一条book的所有信息
    private final static String GET_BOOKINFO_SQL="SELECT * FROM book_info WHERE book_id=?";

    public int addMyshelf(Myshelf ms){
        int readerId=ms.getReaderId();
        String name=ms.getName();
        int stock=ms.getStock();
        String author=ms.getAuthor();
        String publish=ms.getPublish();
        String isbn=ms.getIsbn();
        String introduction=ms.getIntroduction();
        String language=ms.getLanguage();
        BigDecimal price=ms.getPrice();
        Date pubdate=ms.getPubdate();
        int classId=ms.getClassId();
        long bookId=ms.getBookId();

        return jdbcTemplate.update(ADD_SHELF_SQL,new Object[]{readerId,bookId,name,stock,author,publish,isbn,introduction,language,price,pubdate,classId});
    }

    public int deleteMyshelf(long bookId){

        return jdbcTemplate.update(DELETE_SHELF_SQL,bookId);
    }

    public ArrayList<Myshelf> myShelfList(int readerId){
        final ArrayList<Myshelf> list=new ArrayList<Myshelf>();

        jdbcTemplate.query(MY_SHELF_LIST_SQL, new Object[]{readerId},new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                    Myshelf ms =new Myshelf();
                    ms.setReaderId(resultSet.getInt("reader_id"));
                    ms.setPrice(resultSet.getBigDecimal("price"));
                    ms.setPublish(resultSet.getString("publish"));
                    ms.setPubdate(resultSet.getDate("pubdate"));
                    ms.setName(resultSet.getString("name"));
                    ms.setIsbn(resultSet.getString("isbn"));
                    ms.setClassId(resultSet.getInt("class_id"));
                    ms.setBookId(resultSet.getLong("book_id"));
                    ms.setAuthor(resultSet.getString("author"));
                    ms.setIntroduction(resultSet.getString("introduction"));
                    ms.setStock(resultSet.getInt("stock"));
                    ms.setLanguage(resultSet.getString("language"));
                    list.add(ms);
                }
            }
        });
        return list;
    }

    public Myshelf myShelfBook(long bookId){
        Myshelf ms =new Myshelf();
        jdbcTemplate.query(GET_BOOKINFO_SQL, new Object[]{bookId},new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                    ms.setPrice(resultSet.getBigDecimal("price"));
                    ms.setPublish(resultSet.getString("publish"));
                    ms.setPubdate(resultSet.getDate("pubdate"));
                    ms.setName(resultSet.getString("name"));
                    ms.setIsbn(resultSet.getString("isbn"));
                    ms.setClassId(resultSet.getInt("class_id"));
                    ms.setBookId(resultSet.getLong("book_id"));
                    ms.setAuthor(resultSet.getString("author"));
                    ms.setIntroduction(resultSet.getString("introduction"));
                    ms.setStock(resultSet.getInt("stock"));
                    ms.setLanguage(resultSet.getString("language"));
                }
            }
        });
        return ms;
    }
}
