package com.book.web;

import com.book.domain.Myshelf;
import com.book.domain.ReaderCard;
import com.book.service.MyshelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class MyshelfController {

    private MyshelfService myshelfService;

    @Autowired
    public void setMyshelfService(MyshelfService myshelfService) {
        this.myshelfService = myshelfService;
    }

    @RequestMapping("/myshelves.html")
    public ModelAndView allMyshelves(HttpServletRequest request, RedirectAttributes redirectAttributes){
        ReaderCard readerCard=(ReaderCard) request.getSession().getAttribute("readercard");
        ArrayList<Myshelf> myshelves=myshelfService.getMyShelves(readerCard.getReaderId());
        ModelAndView modelAndView=new ModelAndView("reader_shelf");
        modelAndView.addObject("myshelves",myshelves);
        return modelAndView;
    }

    @RequestMapping("/myshelf_delete.html")
    public String myshelfDelete(HttpServletRequest request, RedirectAttributes redirectAttributes){
        int bookId= Integer.parseInt(request.getParameter("bookId"));
        boolean success=myshelfService.deleteMyshelf(bookId);

        if(success){
            redirectAttributes.addFlashAttribute("succ", "删除成功！");
            return "redirect:/myshelves.html";
        }else {
            redirectAttributes.addFlashAttribute("error", "删除失败！");
            return "redirect:/myshelves.html";
        }
    }

    @RequestMapping("myshelf_add.html")
    public String myshelfAdd(BookAddCommand bookAddCommand,HttpServletRequest request,RedirectAttributes redirectAttributes)throws NumberFormatException{
        long bookId=Integer.parseInt(request.getParameter("bookId"));
        Myshelf ms=myshelfService.myShelfBook(bookId);

        ReaderCard readerCard=(ReaderCard) request.getSession().getAttribute("readercard");
        int readerId=readerCard.getReaderId();
        ms.setReaderId(readerId);

        boolean succ=myshelfService.addMyshelf(ms);
        if (succ){
            redirectAttributes.addFlashAttribute("succ", "移入书架成功！");
            return "redirect:/myshelves.html";
        }
        else {
            redirectAttributes.addFlashAttribute("succ", "移入书架失败！");
            return "redirect:/myshelves.html";
        }
    }
}

