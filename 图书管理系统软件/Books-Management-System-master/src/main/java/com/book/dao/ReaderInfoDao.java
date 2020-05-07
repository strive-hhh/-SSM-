package com.book.dao;

import com.book.domain.ReaderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@Repository
public class ReaderInfoDao {

    private JdbcTemplate jdbcTemplate;

    private final static String ADD_READER_INFO_SQL="INSERT INTO reader_info VALUES(?,?,?,?,?,?,?)";
    private final static String DELETE_READER_INFO_SQL="DELETE FROM reader_info where reader_id = ? ";
    private final static String GET_READER_INFO_SQL="SELECT * FROM reader_info where reader_id = ? ";
    private final static String UPDATE_READER_INFO="UPDATE reader_info set name = ?, level= ? ,sex = ? ,birth = ? ,address = ? ,telcode = ? where reader_id = ? ";
    private final static String ALL_READER_INFO_SQL="SELECT * FROM reader_info";

    //根据读者姓名和学号模糊查询
    private final static String QUERY_READER_SQL="SELECT * FROM reader_info WHERE name like ? or reader_id like ?";
    //查询匹配读者的个数
    private final static String MATCH_READER_SQL="SELECT count(*) FROM reader_info WHERE name like ? or reader_id like ?";

    public ArrayList<ReaderInfo> getAllReaderInfo() {
        final ArrayList<ReaderInfo> readers=new ArrayList<ReaderInfo>();
        jdbcTemplate.query(ALL_READER_INFO_SQL, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                    ReaderInfo reader=new ReaderInfo();
                    reader.setAddress(resultSet.getString("address"));
                    reader.setLevel(resultSet.getInt("level"));
                    reader.setBirth(resultSet.getDate("birth"));
                    reader.setName(resultSet.getString("name"));
                    reader.setReaderId(resultSet.getInt("reader_id"));
                    reader.setSex(resultSet.getString("sex"));
                    reader.setTelcode(resultSet.getString("telcode"));
                    readers.add(reader);
                }
            }
        });
        return readers;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ReaderInfo findReaderInfoByReaderId(int readerId){
        final ReaderInfo reader=new ReaderInfo();
        jdbcTemplate.query(GET_READER_INFO_SQL, new Object[]{readerId}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                reader.setAddress(resultSet.getString("address"));
                reader.setLevel(resultSet.getInt("level"));
                reader.setBirth(resultSet.getDate("birth"));
                reader.setName(resultSet.getString("name"));
                reader.setReaderId(resultSet.getInt("reader_id"));
                reader.setSex(resultSet.getString("sex"));
                reader.setTelcode(resultSet.getString("telcode"));
            }
        });
        return reader;
    }

    public int deleteReaderInfo(int readerId){
        return jdbcTemplate.update(DELETE_READER_INFO_SQL,readerId);
    }

    public int editReaderInfo(ReaderInfo readerInfo){
        String address=readerInfo.getAddress();
        int level=readerInfo.getLevel();
        Date birth=readerInfo.getBirth();
        String name=readerInfo.getName();
        int readerId=readerInfo.getReaderId();
        String sex=readerInfo.getSex();
        String telcode=readerInfo.getTelcode();
        return jdbcTemplate.update(UPDATE_READER_INFO,new Object[]{name,level,sex,birth,address,telcode,readerId});
    }

    public int addReaderInfo(ReaderInfo readerInfo){
        String address=readerInfo.getAddress();
        int level=readerInfo.getLevel();
        Date birth=readerInfo.getBirth();
        String name=readerInfo.getName();
        String sex=readerInfo.getSex();
        String telcode=readerInfo.getTelcode();
        int readerId=readerInfo.getReaderId();

        return jdbcTemplate.update(ADD_READER_INFO_SQL,new Object[]{readerId,name,level,sex,birth,address,telcode});
    }

    //查询匹配个数
    public int matchReader(String searchWord){
        String swcx="%"+searchWord+"%";
        return jdbcTemplate.queryForObject(MATCH_READER_SQL,new Object[]{swcx,swcx},Integer.class);
    }

    //根据类别号查询
    public ArrayList<ReaderInfo> queryReaderInfo(String sw) {
        String swcx="%"+sw+"%";
        final ArrayList<ReaderInfo> readerInfos = new ArrayList<ReaderInfo>();
        jdbcTemplate.query(QUERY_READER_SQL, new Object[]{swcx,swcx}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    ReaderInfo readerInfo = new ReaderInfo();
                    readerInfo.setReaderId(resultSet.getInt("reader_id"));
                    readerInfo.setName(resultSet.getString("name"));
                    readerInfo.setLevel(resultSet.getInt("level"));
                    readerInfo.setSex(resultSet.getString("sex"));
                    readerInfo.setBirth(resultSet.getDate("birth"));
                    readerInfo.setAddress(resultSet.getString("address"));
                    readerInfo.setTelcode(resultSet.getString("telcode"));
                    readerInfos.add(readerInfo);
                }
            }
        });
        return readerInfos;
    }
}
