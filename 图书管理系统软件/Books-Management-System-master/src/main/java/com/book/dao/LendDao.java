package com.book.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import com.book.domain.Lend;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
@Repository
public class LendDao {

    private JdbcTemplate jdbcTemplate;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private  final static String GET_BACK_SHOULD_DATE="UPDATE lend_list set back_should_date=date_add(lend_date,interval 2 MONTH )";

    private final static String BOOK_RETURN_SQL_ONE="UPDATE lend_list SET back_date = ?  WHERE book_id = ? AND back_date is NULL";

    private final static String BOOK_STOCK_INCREASE_ONE="UPDATE book_info SET stock=stock+1 WHERE book_id=? ";
    private final static String BOOK_STATE_ONE="UPDATE lend_list SET state=1 WHERE sernum=?";

    private final static String BOOK_LEND_SQL_ONE="INSERT INTO lend_list (book_id,reader_id,lend_date,state) VALUES ( ? , ? , ? ,?)";

    private final static String BOOK_STOCK_DECREASE_ONE="UPDATE book_info SET stock=stock-1 WHERE book_id=? and stock>0";
    private final static String BOOK_STATE_ZERO="UPDATE lend_list SET state=0 WHERE sernum=?";

    private final static String LEND_LIST_SQL="SELECT * FROM lend_list";

    private final static String MY_LEND_LIST_SQL="SELECT * FROM lend_list WHERE reader_id = ? ";

    private final static String DELETE_LEND_LIST_SQL="DELETE FROM lend_list WHERE sernum=?";

    //AND back_date is NULL如果不加这一句，计算读者借了几本书时会算上已经归还的
    private final static String READER_LEND_MAX_SQL="SELECT count(book_id) FROM lend_list WHERE reader_id=? AND back_date is NULL";

    private final static String AUTHORITY_LEND_MAX_SQL="SELECT lend_max FROM authority WHERE level=(SELECT level FROM reader_info WHERE reader_id=?)";

    public int bookShouldBack(){
        return jdbcTemplate.update(GET_BACK_SHOULD_DATE);
    }

    //还书（插入一条记录给借阅表，库存加一，状态位变为1：已还）
    public int bookReturnOne(long bookId){
        return  jdbcTemplate.update(BOOK_RETURN_SQL_ONE,new Object[]{df.format(new Date()),bookId});
    }
    public int stockIncreaseOne(long bookId){
        return jdbcTemplate.update(BOOK_STOCK_INCREASE_ONE,new Object[]{bookId});
    }
    public int bookStateOne(long sernum){
        return jdbcTemplate.update(BOOK_STATE_ONE,sernum);
    }

    //借书（插入一条记录给借阅表，库存减一，状态位为0：未还；这个状态位不用特地设置，初始状态就是0）
    public int bookLendOne(long bookId,int readerId){
        return  jdbcTemplate.update(BOOK_LEND_SQL_ONE,new Object[]{bookId,readerId,df.format(new Date()),0});
    }
    public int stockDecreaseOne(long bookId){
        return  jdbcTemplate.update(BOOK_STOCK_DECREASE_ONE,new Object[]{bookId});
    }


    public ArrayList<Lend> lendList(){
        final ArrayList<Lend> list=new ArrayList<Lend>();

        jdbcTemplate.query(LEND_LIST_SQL, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                    Lend lend=new Lend();
                    lend.setBackshouldDate(resultSet.getDate("back_should_date"));
                    lend.setBackDate(resultSet.getDate("back_date"));
                    lend.setBookId(resultSet.getLong("book_id"));
                    lend.setLendDate(resultSet.getDate("lend_date"));
                    lend.setReaderId(resultSet.getInt("reader_id"));
                    lend.setSernum(resultSet.getLong("sernum"));
                    lend.setState(resultSet.getInt("state"));
                    list.add(lend);
                }
            }
        });
        return list;
    }

    public ArrayList<Lend> myLendList(int readerId){
        final ArrayList<Lend> list=new ArrayList<Lend>();

        jdbcTemplate.query(MY_LEND_LIST_SQL, new Object[]{readerId},new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                    Lend lend=new Lend();
                    lend.setBackshouldDate(resultSet.getDate("back_should_date"));
                    lend.setBackDate(resultSet.getDate("back_date"));
                    lend.setBookId(resultSet.getLong("book_id"));
                    lend.setLendDate(resultSet.getDate("lend_date"));
                    lend.setReaderId(resultSet.getInt("reader_id"));
                    lend.setSernum(resultSet.getLong("sernum"));
                    lend.setState(resultSet.getInt("state"));
                    list.add(lend);
                }
            }
        });
        return list;
    }

    public int deleteLendlist(long sernum){
        return jdbcTemplate.update(DELETE_LEND_LIST_SQL,sernum);
    }

    //queryForObject(String sql, Object[] args, Class<T> requiredType)
    public int readerLendMax(int readerId){
        return jdbcTemplate.queryForObject(READER_LEND_MAX_SQL,new Object[]{readerId},Integer.class);
    }

    public int authorityLendMax(int readerId){
        return jdbcTemplate.queryForObject(AUTHORITY_LEND_MAX_SQL,new Object[]{readerId},Integer.class);
    }
}
