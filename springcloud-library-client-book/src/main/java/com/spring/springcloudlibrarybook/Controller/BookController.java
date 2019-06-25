package com.spring.springcloudlibrarybook.Controller;

import com.spring.springcloudlibrarybook.Dao.bookMapper;
import com.spring.springcloudlibrarybook.pojo.book;
import com.spring.springcloudlibrarybook.pojo.bookloan;
import com.spring.springcloudlibrarybook.pojo.booksell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @Transactional
    @ResponseBody
    @RequestMapping(value = "addBook",method = RequestMethod.POST)
    public int addBook(@RequestParam(value = "book_price",required = false) BigDecimal book_price
            , @RequestParam(value = "book_name",required = false)String book_name
            , @RequestParam(value = "book_authod",required = false)String book_authod
            , @RequestParam(value = "book_type",required = false)Integer book_type){
        return bookMapper.addBook(book_price,book_name,book_authod,book_type);
    }

    @ResponseBody
    @RequestMapping(value = "get_book",method = RequestMethod.GET)
    public Object get_book(@RequestParam(value = "book_id")Integer book_id){
        return bookMapper.get_book(book_id);
    }

    @Transactional
    @ResponseBody
    @RequestMapping(value = "sellbook",method = RequestMethod.POST)
    public Object sellbook(@RequestParam(value = "book_name",required = false)String book_name,
                           @RequestParam(value = "book_authod",required = false)String book_authod,
                           @RequestParam(value = "book_price",required = false) BigDecimal book_price,
                           @RequestParam(value = "book_type",required = false)Integer book_type,
                           @RequestParam(value = "book_id",required = false)Integer book_id,
                           @RequestParam(value = "handler",required = false)String handler){
        int result = bookMapper.sellbook(book_id);
        int result2 = bookMapper.addsell(book_price,book_name,book_authod,book_type,handler);
        if (result!=1 || result2!=1) {
            throw new RuntimeException("出售书籍失败");
        }
        return result+result2;
    }

    @ResponseBody
    @RequestMapping(value = "loan_Book",method = RequestMethod.POST)
    public int loan_Book(@RequestBody bookloan bookloan){
        return bookMapper.loan_Book(bookloan);
    }

    @ResponseBody
    @RequestMapping(value = "getloanCount",method = RequestMethod.POST)
    public int getloanCount(@RequestParam(value = "book_name",required = false)String book_name,
                            @RequestParam(value = "loan_loantimeP",required = false)String loan_loantimeP,
                            @RequestParam(value = "loan_loantimeS",required = false)String loan_loantimeS){
        return bookMapper.getloanCount(book_name,loan_loantimeP,loan_loantimeS);
    }

    @ResponseBody
    @RequestMapping(value = "getBookloanList",method = RequestMethod.POST)
    public List<bookloan> getBookloanList(@RequestParam(value = "book_name",required = false)String book_name,
                                          @RequestParam(value = "loan_loantimeP",required = false)String loan_loantimeP,
                                          @RequestParam(value = "loan_loantimeS",required = false)String loan_loantimeS,
                                          @RequestParam(value = "pageNo",required = false)Integer pageNo,
                                          @RequestParam(value = "pageSize",required = false)Integer pageSize){
        return bookMapper.getBookloanList(book_name,loan_loantimeP,loan_loantimeS,pageNo,pageSize);
    }

    @ResponseBody
    @RequestMapping(value = "return_book", method = RequestMethod.GET)
    public int return_book(@RequestParam(value = "loan_id",required = false)Integer loan_id){
        return bookMapper.return_book(loan_id);
    }

    @ResponseBody
    @RequestMapping(value = "getbooksell", method = RequestMethod.GET)
    public List<booksell> getbooksell(@RequestParam(value = "book_name")Integer pageNo,
                                      @RequestParam(value = "pageSize")Integer pageSize){
        return bookMapper.getbooksell(pageNo, pageSize);
    }

    @ResponseBody
    @RequestMapping(value = "getbooksellCount", method = RequestMethod.GET)
    public int getbooksellCount(){
        return bookMapper.getbooksellCount();
    }
}
