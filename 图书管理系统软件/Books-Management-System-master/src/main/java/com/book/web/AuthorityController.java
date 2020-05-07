package com.book.web;

import com.book.domain.Authority;
import com.book.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class AuthorityController {
    private AuthorityService authorityService;

    @Autowired
    public void setAuthorityService(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @RequestMapping("allauthorities.html")
    public ModelAndView allReaders(){
        ArrayList<Authority> authorities=authorityService.getAuthorities();
        ModelAndView modelAndView=new ModelAndView("admin_authority");
        modelAndView.addObject("authorities",authorities);
        return modelAndView;
    }

    @RequestMapping("authority_delete.html")
    public String readerDelete(HttpServletRequest request, RedirectAttributes redirectAttributes){

        String name=request.getParameter("name");
        boolean success=authorityService.deleteAuthority(name);

        if(success){
            redirectAttributes.addFlashAttribute("succ", "删除成功！");
            return "redirect:/allauthorities.html";
        }else {
            redirectAttributes.addFlashAttribute("error", "删除失败！");
            return "redirect:/allauthorities.html";
        }
    }

    @RequestMapping("authority_edit.html")
    public ModelAndView readerInfoEdit(HttpServletRequest request){
        String name=request.getParameter("name");

        Authority authority=authorityService.getAuthority(name);
        ModelAndView modelAndView=new ModelAndView("admin_authority_edit");
        modelAndView.addObject("authority",authority);
        return modelAndView;
    }
    @RequestMapping("authority_edit_do.html")
    public String readerInfoEditDo(HttpServletRequest request,int level,int lend_max,RedirectAttributes redirectAttributes){
        String name=request.getParameter("name");

        Authority authority=new Authority();
        authority.setName(name);
        authority.setLevel(level);
        authority.setLend_max(lend_max);

        boolean succ=authorityService.editAuthority(authority);
        if(succ){
            redirectAttributes.addFlashAttribute("succ", "权限信息修改成功！");
            return "redirect:/allauthorities.html";
        }else {
            redirectAttributes.addFlashAttribute("error", "权限信息修改失败！");
            return "redirect:/allauthorities.html";
        }
    }


    @RequestMapping("authority_add.html")
    public ModelAndView readerInfoAdd(){
        ModelAndView modelAndView=new ModelAndView("admin_authority_add");
        return modelAndView;
    }
    @RequestMapping("authority_add_do.html")
    public String bookclassAddDo(int levelMax,int level,String name,RedirectAttributes redirectAttributes){
        Authority authority=new Authority();
        authority.setName(name);
        authority.setLevel(level);
        authority.setLend_max(levelMax);
        boolean succ=authorityService.addAuthority(authority);

        if (succ){
            redirectAttributes.addFlashAttribute("succ", "添加权限成功！");
            return "redirect:/allauthorities.html.html";
        }else {
            redirectAttributes.addFlashAttribute("succ", "添加权限失败!");
            return "redirect:/allauthorities.html.html";
        }
    }
}
