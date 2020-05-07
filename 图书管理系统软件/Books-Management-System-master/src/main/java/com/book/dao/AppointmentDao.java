package com.book.dao;

import com.book.domain.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@Repository
public class AppointmentDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //管理员端
    private final static String ALL_APPOINT_INFO_SQL="SELECT * FROM appointment";
    private final static String DELETE_APPOINT_INFO_SQL="UPDATE appointment SET state=-1 where name=?";
    private final static String UPDATE_APPOINT_STATE_SQL="UPDATE appointment SET state=1 where name=?";

    //读者端
    private final static String ADD_APPOINTMENT_SQL="INSERT INTO appointment (reader_id,name,author,publish,appoint_time,state) VALUES(?,?,?,?,?,?)";
    private final static String MY_APPOINTMENT_SQL="SELECT * FROM appointment where reader_id=?";


    //管理员端显示全部读者的预约记录
    public ArrayList<Appointment> getAllAppointment() {
        final ArrayList<Appointment> appointments=new ArrayList<Appointment>();
        jdbcTemplate.query(ALL_APPOINT_INFO_SQL, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                    Appointment appointment=new Appointment();
                    appointment.setReaderId(resultSet.getInt("reader_id"));
                    appointment.setName(resultSet.getString("name"));
                    appointment.setAuthor(resultSet.getString("author"));
                    appointment.setPublish(resultSet.getString("publish"));
                    appointment.setState(resultSet.getInt("state"));

                    appointment.setAppoint_time(resultSet.getDate("appoint_time"));
                    appointments.add(appointment);
                }
            }
        });
        return appointments;
    }

    public int deleteAppointment(String name){
        return jdbcTemplate.update(DELETE_APPOINT_INFO_SQL,name);
    }
    //管理员端审核预约过程
    public int updateAppointment(String name){
        return jdbcTemplate.update(UPDATE_APPOINT_STATE_SQL,new Object[]{name});
    }

    //读者端预约新书
    public int addAppointment(Appointment appointment){
        int readerId=appointment.getReaderId();
        String name=appointment.getName();
        String author=appointment.getAuthor();
        String publish=appointment.getPublish();
        Date appointtime=appointment.getAppoint_time();
        int state=appointment.getState();

        return jdbcTemplate.update(ADD_APPOINTMENT_SQL,new Object[]{readerId,name,author,publish,appointtime,0});
    }

    //显示某一个读者的全部预约记录
    public ArrayList<Appointment> getMyAppointment(int readerId){
        final ArrayList<Appointment> appointments=new ArrayList<Appointment>();
        jdbcTemplate.query(MY_APPOINTMENT_SQL, new Object[]{readerId}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();

                while (resultSet.next()){
                    Appointment appointment=new Appointment();
                    appointment.setReaderId(resultSet.getInt("reader_id"));
                    appointment.setName(resultSet.getString("name"));
                    appointment.setAuthor(resultSet.getString("author"));
                    appointment.setPublish(resultSet.getString("publish"));
                    appointment.setState(resultSet.getInt("state"));

                    appointment.setAppoint_time(resultSet.getDate("appoint_time"));
                    appointments.add(appointment);
                }
            }
        });
        return appointments;
    }
}
