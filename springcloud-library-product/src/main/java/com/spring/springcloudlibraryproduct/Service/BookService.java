package com.spring.springcloudlibraryproduct.Service;


import com.spring.springcloudlibraryproduct.pojo.book;
import com.spring.springcloudlibraryproduct.pojo.bookloan;
import com.spring.springcloudlibraryproduct.pojo.booksell;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "get_book",method = RequestMethod.GET)
    public book get_book(@RequestParam(value = "book_id")Integer book_id);

    @RequestMapping(value = "sellbook",method = RequestMethod.POST)
    public int sellbook(@RequestParam(value = "book_name",required = false)String book_name,
                           @RequestParam(value = "book_authod",required = false)String book_authod,
                           @RequestParam(value = "book_price",required = false)BigDecimal book_price,
                           @RequestParam(value = "book_type",required = false)Integer book_type,
                           @RequestParam(value = "book_id",required = false)Integer book_id,
                           @RequestParam(value = "handler",required = false)String handler);

    @RequestMapping(value = "loan_Book",method = RequestMethod.POST)
    public int loan_Book(@RequestBody bookloan bookloan);

    @RequestMapping(value = "getloanCount",method = RequestMethod.POST)
    public int getloanCount(@RequestParam(value = "book_name",required = false)String book_name,
                            @RequestParam(value = "loan_loantimeP",required = false)String loan_loantimeP,
                            @RequestParam(value = "loan_loantimeS",required = false)String loan_loantimeS);

    @RequestMapping(value = "getBookloanList",method = RequestMethod.POST)
    public List<bookloan> getBookloanList(@RequestParam(value = "book_name",required = false)String book_name,
                                          @RequestParam(value = "loan_loantimeP",required = false)String loan_loantimeP,
                                          @RequestParam(value = "loan_loantimeS",required = false)String loan_loantimeS,
                                          @RequestParam(value = "pageNo",required = false)Integer pageNo,
                                          @RequestParam(value = "pageSize",required = false)Integer pageSize);

    @RequestMapping(value = "return_book", method = RequestMethod.GET)
    public int return_book(@RequestParam(value = "loan_id",required = false)Integer loan_id);

    @RequestMapping(value = "getbooksellCount", method = RequestMethod.GET)
    public int getbooksellCount();

    @RequestMapping(value = "getbooksell", method = RequestMethod.GET)
    public List<booksell> getbooksell(@RequestParam(value = "book_name")Integer pageNo,
                                      @RequestParam(value = "pageSize")Integer pageSize);
}
