package com.book.web;

import com.book.domain.Book;
import com.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.UUID;


@Controller
public class BookController {
    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    //管理员端---查询图书
    @RequestMapping("/querybook.html")
    public ModelAndView queryBookDo(HttpServletRequest request,String searchWord){
        boolean exist=bookService.matchBook(searchWord);
        if (exist){
            ArrayList<Book> books = bookService.queryBook(searchWord);
            ModelAndView modelAndView = new ModelAndView("admin_books");
            modelAndView.addObject("books",books);
            return modelAndView;
        }
        else{
            return new ModelAndView("admin_books","error","没有匹配的图书");
        }
    }


    @RequestMapping("/reader_querybook.html")
    public ModelAndView readerQueryBook(){
       return new ModelAndView("reader_book_query");
    }

    //@ResponseBody
    @RequestMapping("/reader_querybook_do.html")
    public String readerQueryBookDo(String searchWord, HttpServletRequest request, RedirectAttributes redirectAttributes){
        //读者端---根据书名和书号模糊查询
        String searchClass1=request.getParameter("searchClass");
        request.getSession().setAttribute("searchClass1",searchClass1);
        if(searchClass1.equals("bookname")||searchClass1.equals("bookid"))
        {
            boolean exist=bookService.matchBook(searchWord);
            if (exist){
                ArrayList<Book> books = bookService.queryBook(searchWord);
                redirectAttributes.addFlashAttribute("books", books);
                return "redirect:/reader_querybook.html";
            }
            else{
                redirectAttributes.addFlashAttribute("error", "没有匹配的图书！");
                return "redirect:/reader_querybook.html";
            }
        }else
        {
            //读者端---根据图书类别查询
            boolean exist=bookService.matchClass(searchWord);
            System.out.println("exist="+exist);
            if (exist){
                ArrayList<Book> books = bookService.queryClassBook(searchWord);
                redirectAttributes.addFlashAttribute("books", books);
                return "redirect:/reader_querybook.html";
            }
            else{
                redirectAttributes.addFlashAttribute("error", "没有匹配的图书！");
                return "redirect:/reader_querybook.html";
            }
        }
    }

    //管理员端---显示所有图书
    @RequestMapping("/allbooks.html")
    public ModelAndView allBook(){
        ArrayList<Book> books=bookService.getAllBooks();
        ModelAndView modelAndView=new ModelAndView("admin_books");
        modelAndView.addObject("books",books);
        return modelAndView;
    }

    //管理员端删除图书
    @RequestMapping("/deletebook.html")
    public String deleteBook(HttpServletRequest request,RedirectAttributes redirectAttributes){
        long bookId=Integer.parseInt(request.getParameter("bookId"));
        int res=bookService.deleteBook(bookId);

        if (res==1){
            redirectAttributes.addFlashAttribute("succ", "图书删除成功！");
            return "redirect:/allbooks.html";
        }else {
            redirectAttributes.addFlashAttribute("error", "图书删除失败！");
            return "redirect:/allbooks.html";
        }
    }

    //管理员端----增加图书
    @RequestMapping("/book_add.html")
    public ModelAndView addBook(HttpServletRequest request){
            return new ModelAndView("admin_book_add");
    }

    @RequestMapping(value = "/book_add_do.html",method = RequestMethod.POST)
    public String addBookDo(BookAddCommand bookAddCommand,RedirectAttributes redirectAttributes,@RequestParam("imgFile") MultipartFile file){
        Book book=new Book();

        if (file != null){
            // 生成图片存储的名称，UUID 避免相同图片名冲突
            String originalFileName = file.getOriginalFilename(); // 原始文件名
            String suffix = originalFileName.substring(originalFileName.lastIndexOf(".")); // 图片后缀
            String fileName = UUID.randomUUID().toString() + suffix;
            String filePath = "E:/upfile/" + fileName;
            File saveFile = new File(filePath);
            try {
                // 将上传的文件保存到服务器文件系统
                file.transferTo(saveFile);
                // 记录服务器文件系统图片名称
                book.setPic(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        book.setBookId(0);
        book.setName(bookAddCommand.getName());
        book.setPrice(bookAddCommand.getPrice());
        book.setState(bookAddCommand.getState());
        book.setPublish(bookAddCommand.getPublish());
        book.setPubdate(bookAddCommand.getPubdate());
        book.setName(bookAddCommand.getName());
        book.setIsbn(bookAddCommand.getIsbn());
        book.setClassId(bookAddCommand.getClassId());
        book.setAuthor(bookAddCommand.getAuthor());
        book.setIntroduction(bookAddCommand.getIntroduction());
        book.setStock(bookAddCommand.getStock());
        book.setLanguage(bookAddCommand.getLanguage());

        boolean succ=bookService.addBook(book);
        if (succ){
            redirectAttributes.addFlashAttribute("succ", "图书添加成功！");
            return "redirect:/allbooks.html";
        }
        else {
            redirectAttributes.addFlashAttribute("error", "图书添加失败！");
            return "redirect:/allbooks.html";
        }
    }

    @RequestMapping("/updatebook.html")
    public ModelAndView bookEdit(HttpServletRequest request){
        long bookId=Integer.parseInt(request.getParameter("bookId"));
        Book book=bookService.getBook(bookId);
        ModelAndView modelAndView=new ModelAndView("admin_book_edit");
        modelAndView.addObject("detail",book);
        return modelAndView;
    }

    @RequestMapping("/book_edit_do.html")
    public String bookEditDo(HttpServletRequest request,BookAddCommand bookAddCommand,RedirectAttributes redirectAttributes){
        long bookId=Integer.parseInt( request.getParameter("id"));
        Book book=new Book();
        book.setBookId(bookId);
        book.setPrice(bookAddCommand.getPrice());
        book.setState(bookAddCommand.getState());
        book.setPublish(bookAddCommand.getPublish());
        book.setPubdate(bookAddCommand.getPubdate());
        book.setName(bookAddCommand.getName());
        book.setIsbn(bookAddCommand.getIsbn());
        book.setClassId(bookAddCommand.getClassId());
        book.setAuthor(bookAddCommand.getAuthor());
        book.setIntroduction(bookAddCommand.getIntroduction());
        book.setStock(bookAddCommand.getStock());
        book.setLanguage(bookAddCommand.getLanguage());

        boolean succ=bookService.editBook(book);
        if (succ){
            redirectAttributes.addFlashAttribute("succ", "图书修改成功！");
            return "redirect:/allbooks.html";
        }
        else {
            redirectAttributes.addFlashAttribute("error", "图书修改失败！");
            return "redirect:/allbooks.html";
        }
    }

    @RequestMapping(value = "/bookdetail.html")
    public ModelAndView bookDetail(HttpServletRequest request){
        long bookId=Integer.parseInt(request.getParameter("bookId"));
        Book book=bookService.getBook(bookId);
        ModelAndView modelAndView=new ModelAndView("admin_book_detail");
        modelAndView.addObject("detail",book);
        return modelAndView;
    }

    //处理图片显示请求
    @RequestMapping("/showPic/{fileName}.{suffix}")
    public void showPicture(@PathVariable("fileName") String fileName,
                            @PathVariable("suffix") String suffix,
                            HttpServletResponse response){
        String realpath="E:/upfile/";
        File imgFile = new File(realpath+ fileName + "." + suffix);
        responseFile(response, imgFile);
    }

    //响应输出图片文件
    private void responseFile(HttpServletResponse response, File imgFile) {
        try(InputStream is = new FileInputStream(imgFile);
            OutputStream os = response.getOutputStream();){
            byte [] buffer = new byte[1024]; // 图片文件流缓存池
            while(is.read(buffer) != -1){
                os.write(buffer);
            }
            os.flush();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    //读者端---图书借阅榜
    @RequestMapping("/reader_borrowlist.html")
    public ModelAndView getTopBooks(){
        ArrayList<Book> books=bookService.getTopBooks();
        ModelAndView modelAndView=new ModelAndView("reader_borrowlist");
        modelAndView.addObject("topbooks",books);
        return modelAndView;
    }


}
