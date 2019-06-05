package com.spring.springcloudlibrarybook.Controller;

import com.spring.springcloudlibrarybook.Dao.bookMapper;
import com.spring.springcloudlibrarybook.pojo.book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    bookMapper bookMapper;

    @ResponseBody
    @RequestMapping(value = "getCount",method = RequestMethod.GET)
    public int getCount(@RequestParam(value = "book_name",required = false)String book_name
            , @RequestParam(value = "book_authod",required = false)String book_authod
            , @RequestParam(value = "book_damage",required = false)Integer book_damage){
        return bookMapper.getCount(book_name,book_authod,book_damage);
    }

    @ResponseBody
    @RequestMapping(value = "getBooklist",method = RequestMethod.GET)
    public List<book> getBookList(@RequestParam(value = "pageNo",required = false)Integer pageNo
            , @RequestParam(value = "pageSize",required = false)Integer pageSize
            , @RequestParam(value = "book_name",required = false)String book_name
            , @RequestParam(value = "book_authod",required = false)String book_authod
            , @RequestParam(value = "book_damage",required = false)Integer book_damage){
        return bookMapper.getBookList(pageNo,pageSize,book_name,book_authod,book_damage);
    }

    @ResponseBody
    @RequestMapping(value = "addBook",method = RequestMethod.POST)
    public int addBook(@RequestParam(value = "book_price",required = false) BigDecimal book_price
            , @RequestParam(value = "book_name",required = false)String book_name
            , @RequestParam(value = "book_authod",required = false)String book_authod
            , @RequestParam(value = "book_type",required = false)Integer book_type){
        return bookMapper.addBook(book_price,book_name,book_authod,book_type);
    }
}
