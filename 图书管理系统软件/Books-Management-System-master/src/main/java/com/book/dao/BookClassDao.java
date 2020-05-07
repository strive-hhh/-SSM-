package com.book.dao;

import com.book.domain.BookClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class BookClassDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String ALL_BOOKCLASS_INFO_SQL="SELECT * FROM class_info";
    private final static String GET_BOOKCLASS_SQL="SELECT * FROM class_info where class_id = ? ";
    private final static String ADD_BOOKCLASS_INFO_SQL="INSERT INTO class_info VALUES(?,?)";
    private final static String DELETE_BOOKCLASS_INFO_SQL="DELETE FROM class_info where class_id = ? ";
    private final static String UPDATE_BOOKCLASS_INFO_SQL="UPDATE class_info set class_name= ? where class_id = ? ";

    public ArrayList<BookClass> getAllBookClass() {
        final ArrayList<BookClass> bookclasses=new ArrayList<BookClass>();
        jdbcTemplate.query(ALL_BOOKCLASS_INFO_SQL, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                    BookClass bookClass=new BookClass();
                    bookClass.setClassId(resultSet.getInt("class_id"));
                    bookClass.setClassName(resultSet.getString("class_name"));
                    bookclasses.add(bookClass);
                }
            }
        });
        return bookclasses;
    }

    public BookClass findBookClassById(int id){
        final BookClass bookClass=new BookClass();
        jdbcTemplate.query(GET_BOOKCLASS_SQL, new Object[]{id}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                bookClass.setClassId(resultSet.getInt("class_id"));
                bookClass.setClassName(resultSet.getString("class_name"));
            }
        });
        return bookClass;
    }

    public int addBookClass(BookClass bookClass){
        int id=bookClass.getClassId();
        String name=bookClass.getClassName();

        return jdbcTemplate.update(ADD_BOOKCLASS_INFO_SQL,new Object[]{id,name});
    }

    public int deleteBookClass(int id){
        return jdbcTemplate.update(DELETE_BOOKCLASS_INFO_SQL,id);
    }

    public int editBookClass(BookClass bookClass){
        int id=bookClass.getClassId();
        String name=bookClass.getClassName();

        return jdbcTemplate.update(UPDATE_BOOKCLASS_INFO_SQL,new Object[]{name,id});
    }

}
