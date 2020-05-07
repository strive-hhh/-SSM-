package com.book.web;

import com.book.domain.BookClass;
import com.book.service.BookClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class BookClassController {
    private BookClassService bookclassService;

    @Autowired
    public void setAuthorityService(BookClassService bookclassService) {
        this.bookclassService = bookclassService;
    }

    @RequestMapping("allbookclasses.html")
    public ModelAndView allBookclasses(){
        ArrayList<BookClass> bookClasses=bookclassService.getAllBookClass();
        ModelAndView modelAndView=new ModelAndView("admin_bookclass");
        modelAndView.addObject("bookclasses",bookClasses);
        return modelAndView;
    }

    @RequestMapping("bookclass_delete.html")
    public String bookclassDelete(HttpServletRequest request, RedirectAttributes redirectAttributes){

        int id=Integer.parseInt(request.getParameter("class_id"));
        boolean success=bookclassService.deleteBookClass(id);

        if(success){
            redirectAttributes.addFlashAttribute("succ", "删除成功！");
            return "redirect:/allbookclasses.html";
        }else {
            redirectAttributes.addFlashAttribute("error", "删除失败！");
            return "redirect:/allbookclasses.html";
        }
    }

    @RequestMapping("bookclass_edit.html")
    public ModelAndView readerInfoEdit(HttpServletRequest request){
        int id=Integer.parseInt(request.getParameter("class_id"));
        BookClass bookclass=bookclassService.getBookClass(id);
        ModelAndView modelAndView=new ModelAndView("admin_bookclass_edit");
        modelAndView.addObject("bookclass",bookclass);
        return modelAndView;
    }
    @RequestMapping("bookclass_edit_do.html")
    public String readerInfoEditDo(HttpServletRequest request,String name,RedirectAttributes redirectAttributes){
        int id=Integer.parseInt(request.getParameter("class_id"));

        BookClass bookClass=new BookClass();
        bookClass.setClassId(id);
        bookClass.setClassName(name);

        boolean succ=bookclassService.editBookClass(bookClass);
        if(succ){
            redirectAttributes.addFlashAttribute("succ", "图书类别信息修改成功！");
            return "redirect:/allbookclasses.html";
        }else {
            redirectAttributes.addFlashAttribute("error", "图书类别信息修改失败！");
            return "redirect:/allbookclasses.html";
        }
    }

    @RequestMapping("bookclass_add.html")
    public ModelAndView readerInfoAdd(){
        ModelAndView modelAndView=new ModelAndView("admin_bookclass_add");
        return modelAndView;
    }
    @RequestMapping("bookclass_add_do.html")
    public String bookclassAddDo(int classId,String className,RedirectAttributes redirectAttributes){
        BookClass bookclass=new BookClass();
        bookclass.setClassId(classId);
        bookclass.setClassName(className);
        boolean succ=bookclassService.addBookClass(bookclass);
        if (succ){
            redirectAttributes.addFlashAttribute("succ", "添加图书类别成功！");
            return "redirect:/allbookclasses.html";
        }else {
            redirectAttributes.addFlashAttribute("succ", "添加图书类别失败！");
            return "redirect:/allbookclasses.html";
        }
    }
}
