package com.book.web;

import com.book.domain.Appointment;
import com.book.domain.ReaderCard;
import com.book.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class AppointmentController {

    private AppointmentService appointmentService;

    @Autowired
    public void setAppointmentService(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    //管理员端---显示全部预约记录
    @RequestMapping("allappointments.html")
    public ModelAndView allAppointments(){
        ArrayList<Appointment> appointments=appointmentService.appointmentInfos();
        ModelAndView modelAndView=new ModelAndView("admin_appointment");
        modelAndView.addObject("appointments",appointments);
        return modelAndView;
    }
    //管理员端----同意预约申请
    @RequestMapping(value = "/update_appointment_do.html")
    public String updateAppointmentDo(HttpServletRequest request,RedirectAttributes redirectAttributes){
        String name=request.getParameter("name");           //书名
        boolean success=appointmentService.updateAppointment(name);
        return "redirect:/allappointments.html";
    }

    //管理员端---否决预约申请
    @RequestMapping("appointment_delete.html")
    public String readerDelete(HttpServletRequest request, RedirectAttributes redirectAttributes){
        String name=request.getParameter("name");
        boolean success=appointmentService.deleteAppointment(name);

        return "redirect:/allappointments.html";
//        if(success){
//            redirectAttributes.addFlashAttribute("succ", "删除成功！");
//            return "redirect:/allappointments.html";
//        }else {
//            redirectAttributes.addFlashAttribute("error", "删除失败！");
//            return "redirect:/allappointments.html";
//        }
    }

    //读者端---增加一条预约记录
    @RequestMapping("/appointment_add.html")
    public ModelAndView addAppointment(HttpServletRequest request){
        return new ModelAndView("reader_appointment_add");
    }

    @RequestMapping(value = "/appointment_add_do.html")
    public String addAppointmentDo(int readerId,String name,String author,String publish,String appoint_time,RedirectAttributes redirectAttributes){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date apptime=new Date();

        try{
            java.util.Date date=sdf.parse(appoint_time);
            apptime=date;
        }catch (ParseException e){
            e.printStackTrace();
        }
        Appointment appointment=new Appointment();
        appointment.setReaderId(readerId);
        appointment.setName(name);
        appointment.setAuthor(author);
        appointment.setPublish(publish);
        appointment.setAppoint_time(apptime);
        appointment.setState(0);
        boolean succ=appointmentService.addAppointment(appointment);
        if (succ){
            redirectAttributes.addFlashAttribute("succ", "图书预约记录添加成功！");
            return "redirect:/myappointments.html";
        }
        else {
            redirectAttributes.addFlashAttribute("succ", "图书预约记录添加失败！");
            return "redirect:/myappointments.html";
        }
    }

    //读者端---显示预约记录，查询状态
    @RequestMapping("/myappointments.html")
    public ModelAndView myAppointments(HttpServletRequest request){
        ReaderCard readerCard=(ReaderCard) request.getSession().getAttribute("readercard");
        ModelAndView modelAndView=new ModelAndView("reader_appointment");
        modelAndView.addObject("appointments",appointmentService.getMyAppointment(readerCard.getReaderId()));
        return modelAndView;
    }
}
