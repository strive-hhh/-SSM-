package com.book.web;

import com.book.domain.Book;
import com.book.domain.ReaderCard;
import com.book.domain.ReaderInfo;
import com.book.service.BookService;
import com.book.service.LendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LendController {

    private LendService lendService;
    @Autowired
    public void setLendService(LendService lendService) {
        this.lendService = lendService;
    }
    private BookService bookService;
    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    //管理员端----借还流水日志
    @RequestMapping("/lendlist.html")
    public ModelAndView lendList(){

        ModelAndView modelAndView=new ModelAndView("admin_lend_list");
        modelAndView.addObject("list",lendService.lendList());
        return modelAndView;
    }

    //管理员端----删除借还日志
    @RequestMapping("/deleteLendlist.html")
    public String deleteLendlist(HttpServletRequest request,RedirectAttributes redirectAttributes){
        long sernum=Integer.parseInt(request.getParameter("sernum"));
        int res=lendService.deleteLendlist(sernum);

        if (res==1){
            redirectAttributes.addFlashAttribute("succ", "流水记录删除成功！");
            return "redirect:/lendlist.html";
        }else {
            redirectAttributes.addFlashAttribute("error", "流水记录删除失败！");
            return "redirect:/lendlist.html";
        }
    }

    //读者端导航栏-----我的借阅
    @RequestMapping("/mylend.html")
    public ModelAndView myLend(HttpServletRequest request){
        ReaderCard readerCard=(ReaderCard) request.getSession().getAttribute("readercard");
        ModelAndView modelAndView=new ModelAndView("reader_lend_list");
        modelAndView.addObject("list",lendService.myLendList(readerCard.getReaderId()));
        return modelAndView;
    }

    //读者端----借阅图书且借阅图书数目小于权限规定的数目
    @RequestMapping("/lendbook.html")
    public String bookLendDo(HttpServletRequest request,RedirectAttributes redirectAttributes){
        long bookId= Integer.parseInt(request.getParameter("bookId"));
        ReaderCard readerCard=(ReaderCard) request.getSession().getAttribute("readercard");
        int readerId=readerCard.getReaderId();

        boolean maxsucc=lendService.authorityReaderLendMax(readerId);
        if (maxsucc){
            boolean lendsucc=lendService.bookLend(bookId,readerId);
            if(lendsucc){
                redirectAttributes.addFlashAttribute("succ", "图书借阅成功！");
                return "redirect:/mylend.html";
            }else {
                redirectAttributes.addFlashAttribute("error", "图书库存不足！");
                return "redirect:/mylend.html";
            }
        }else {
            redirectAttributes.addFlashAttribute("error", "借阅图书超过上限！");
            return "redirect:/mylend.html";
        }
    }

    //读者端----归还图书
   @RequestMapping("/returnbook.html")
    public String bookReturn(HttpServletRequest request,RedirectAttributes redirectAttributes){
        int bookId=Integer.parseInt(request.getParameter("bookId"));
        int sernum=Integer.parseInt(request.getParameter("sernum"));
        System.out.println("bookId:"+bookId+"sernum:"+sernum);
        boolean retSucc=lendService.bookReturn(bookId,sernum);
        if (retSucc){
            redirectAttributes.addFlashAttribute("succ", "图书归还成功！");
            return "redirect:/mylend.html";
        }
        else {
            redirectAttributes.addFlashAttribute("error", "图书归还失败！");
            return "redirect:/mylend.html";
        }
    }
}
