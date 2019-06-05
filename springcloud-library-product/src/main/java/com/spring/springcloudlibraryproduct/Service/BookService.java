package com.spring.springcloudlibraryproduct.Service;


import com.spring.springcloudlibraryproduct.pojo.book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(name = "library-book")
public interface BookService {

    @RequestMapping(value = "getCount",method = RequestMethod.GET)
    public int getCount(@RequestParam(value = "book_name",required = false)String book_name
            , @RequestParam(value = "book_authod",required = false)String book_authod
            , @RequestParam(value = "book_damage",required = false)Integer book_damage
    );

    @RequestMapping(value = "getBooklist",method = RequestMethod.GET)
    public List<book> getBooklist(@RequestParam(value = "pageNo",required = false)Integer pageNo
            , @RequestParam(value = "pageSize",required = false)Integer pageSize
            , @RequestParam(value = "book_name",required = false)String book_name
            , @RequestParam(value = "book_authod",required = false)String book_authod
            , @RequestParam(value = "book_damage",required = false)Integer book_damage
    );

    @RequestMapping(value = "addBook",method = RequestMethod.POST)
    public int addBook(@RequestParam(value = "book_price",required = false)BigDecimal book_price
            , @RequestParam(value = "book_name",required = false)String book_name
            , @RequestParam(value = "book_authod",required = false)String book_authod
            , @RequestParam(value = "book_type",required = false)Integer book_type);
}
