package com.book.dao;

import com.book.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import sun.security.pkcs11.wrapper.Constants;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Repository
public class BookDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //增加和显示详情时需要上传图片
    private final static String ADD_BOOK_SQL="INSERT INTO book_info VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final static String DELETE_BOOK_SQL="delete from book_info where book_id = ?  ";
    private final static String EDIT_BOOK_SQL="update book_info set name= ? ,stock=?, author= ? ,publish= ? ,ISBN= ? ,introduction= ? ,language= ? ,price= ? ,pubdate= ? ,class_id= ? ,state= ?  where book_id= ? ;";
    private final static String QUERY_ALL_BOOKS_SQL="SELECT * FROM book_info ";
    //根据书号查询图书
    private final static String GET_BOOKID_SQL="SELECT * FROM book_info where book_id = ? ";

    //查询匹配图书的个数
    private final static String MATCH_BOOK_SQL="SELECT count(*) FROM book_info WHERE book_id like ?  or name like ?";
    //根据书名和书号模糊查询
    private final static String QUERY_BOOK_SQL="SELECT * FROM book_info WHERE book_id like  ?  or name like ?";
    //根据类别查询匹配的图书个数
    private final static String MATCH_CLASS_SQL="SELECT count(*) FROM book_info WHERE class_id=(SELECT class_id FROM class_info WHERE class_name=?)";
    //根据类别查询图书
    private final static String GET_CLASS_SQL="SELECT * FROM book_info where class_id=(SELECT class_id FROM class_info WHERE class_name=?)";

    //根据lend_list找出借出最多的bookId
    private final static String BOOK_RANK_SQL="SELECT * FROM book_info WHERE book_id IN" +
            "(SELECT book_id FROM lend_list GROUP BY book_id ORDER BY count(book_id)  DESC)";

    //查询匹配个数
    public int matchBook(String searchWord){
        String swcx="%"+searchWord+"%";
        return jdbcTemplate.queryForObject(MATCH_BOOK_SQL,new Object[]{swcx,swcx},Integer.class);
    }

    //查询类别匹配个数
    public int matchClass(String searchWord){
        return jdbcTemplate.queryForObject(MATCH_CLASS_SQL,new Object[]{searchWord},Integer.class);
    }

    //根据书名和书号模糊查询
    public ArrayList<Book> queryBook(String sw){
        String swcx="%"+sw+"%";
        final ArrayList<Book> books=new ArrayList<Book>();
        jdbcTemplate.query(QUERY_BOOK_SQL, new Object[]{swcx,swcx}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    Book book = new Book();
                    book.setName(resultSet.getString("name"));
                    book.setStock(resultSet.getInt("stock"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setBookId(resultSet.getLong("book_id"));
                    book.setClassId(resultSet.getInt("class_id"));
                    book.setIntroduction(resultSet.getString("introduction"));
                    book.setIsbn(resultSet.getString("isbn"));
                    book.setLanguage(resultSet.getString("language"));

                    book.setPubdate(resultSet.getDate("pubdate"));
                    book.setPrice(resultSet.getBigDecimal("price"));
                    book.setState(resultSet.getInt("state"));
                    book.setPublish(resultSet.getString("publish"));
                    books.add(book);
                }
            }
        });
        return books;
    }

    //根据类别号查询
    public ArrayList<Book> queryClassBook(String sw){

        final ArrayList<Book> books=new ArrayList<Book>();
        jdbcTemplate.query(GET_CLASS_SQL, new Object[]{sw}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    Book book = new Book();
                    book.setName(resultSet.getString("name"));
                    book.setStock(resultSet.getInt("stock"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setBookId(resultSet.getLong("book_id"));
                    book.setClassId(resultSet.getInt("class_id"));
                    book.setIntroduction(resultSet.getString("introduction"));
                    book.setIsbn(resultSet.getString("isbn"));
                    book.setLanguage(resultSet.getString("language"));

                    book.setPubdate(resultSet.getDate("pubdate"));
                    book.setPrice(resultSet.getBigDecimal("price"));
                    book.setState(resultSet.getInt("state"));
                    book.setPublish(resultSet.getString("publish"));
                    books.add(book);
                }
            }
        });
        return books;
    }

    public ArrayList<Book> getAllBooks(){
        final ArrayList<Book> books=new ArrayList<Book>();

        jdbcTemplate.query(QUERY_ALL_BOOKS_SQL, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                    Book book =new Book();
                    book.setPrice(resultSet.getBigDecimal("price"));
                    book.setPic(resultSet.getString("pic"));
                    book.setState(resultSet.getInt("state"));
                    book.setPublish(resultSet.getString("publish"));
                    book.setPubdate(resultSet.getDate("pubdate"));
                    book.setName(resultSet.getString("name"));
                    book.setIsbn(resultSet.getString("isbn"));
                    book.setClassId(resultSet.getInt("class_id"));
                    book.setBookId(resultSet.getLong("book_id"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setIntroduction(resultSet.getString("introduction"));
                    book.setStock(resultSet.getInt("stock"));
                    book.setLanguage(resultSet.getString("language"));
                    books.add(book);
                }
            }
        });
        return books;
    }

    public int deleteBook(long bookId){
        return jdbcTemplate.update(DELETE_BOOK_SQL,bookId);
    }

    public int addBook(Book book){
        String name=book.getName();
        String pic=book.getPic();
        int stock=book.getStock();
        String author=book.getAuthor();
        String publish=book.getPublish();
        String isbn=book.getIsbn();
        String introduction=book.getIntroduction();
        String language=book.getLanguage();
        BigDecimal price=book.getPrice();
        Date pubdate=book.getPubdate();
        int classId=book.getClassId();
        int state=book.getState();

        return jdbcTemplate.update(ADD_BOOK_SQL,new Object[]{name,pic,stock,author,publish,isbn,introduction,language,price,pubdate,classId,state});
    }

    public Book getBook(Long bookId){
        final Book book =new Book();
        jdbcTemplate.query(GET_BOOKID_SQL, new Object[]{bookId}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                    book.setAuthor(resultSet.getString("author"));
                    book.setPic(resultSet.getString("pic"));
                    book.setBookId(resultSet.getLong("book_id"));
                    book.setClassId(resultSet.getInt("class_id"));
                    book.setIntroduction(resultSet.getString("introduction"));
                    book.setIsbn(resultSet.getString("isbn"));
                    book.setLanguage(resultSet.getString("language"));
                    book.setName(resultSet.getString("name"));
                    book.setStock(resultSet.getInt("stock"));
                    book.setPubdate(resultSet.getDate("pubdate"));
                    book.setPrice(resultSet.getBigDecimal("price"));
                    book.setState(resultSet.getInt("state"));
                    book.setPublish(resultSet.getString("publish"));
            }
        });
        return book;
    }
    public int editBook(Book book){
        Long bookId=book.getBookId();
        String name=book.getName();
        String author=book.getAuthor();
        String publish=book.getPublish();
        String isbn=book.getIsbn();
        String introduction=book.getIntroduction();
        String language=book.getLanguage();
        BigDecimal price=book.getPrice();
        Date pubdate=book.getPubdate();
        int classId=book.getClassId();
        int stock=book.getStock();
        int state=book.getState();

        return jdbcTemplate.update(EDIT_BOOK_SQL,new Object[]{name,stock,author,publish,isbn,introduction,language,price,pubdate,classId,state,bookId});
    }

    //得到前十本图书
    public ArrayList<Book> getTopBooks(){
        final ArrayList<Book> books=new ArrayList<Book>();

        jdbcTemplate.query(BOOK_RANK_SQL, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                    Book book =new Book();
                    book.setPrice(resultSet.getBigDecimal("price"));
                    book.setPic(resultSet.getString("pic"));
                    book.setState(resultSet.getInt("state"));
                    book.setPublish(resultSet.getString("publish"));
                    book.setPubdate(resultSet.getDate("pubdate"));
                    book.setName(resultSet.getString("name"));
                    book.setIsbn(resultSet.getString("isbn"));
                    book.setClassId(resultSet.getInt("class_id"));
                    book.setBookId(resultSet.getLong("book_id"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setIntroduction(resultSet.getString("introduction"));
                    book.setStock(resultSet.getInt("stock"));
                    book.setLanguage(resultSet.getString("language"));
                    books.add(book);
                }
            }
        });
        return books;
    }
}
