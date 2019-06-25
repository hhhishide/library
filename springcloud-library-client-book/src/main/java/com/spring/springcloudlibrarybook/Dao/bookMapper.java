package com.spring.springcloudlibrarybook.Dao;

import com.spring.springcloudlibrarybook.pojo.book;
import com.spring.springcloudlibrarybook.pojo.bookloan;
import com.spring.springcloudlibrarybook.pojo.booksell;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

public interface bookMapper {

    public int getCount(@Param("book_name") String book_name, @Param("book_authod")String book_authod
            , @Param("book_damage")Integer book_damage);

    public List<book> getBookList(@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize,@Param("book_name") String book_name
            , @Param("book_authod")String book_authod, @Param("book_damage")Integer book_damage);

    public int addBook(@Param("book_price")BigDecimal book_price, @Param("book_name")String book_name, @Param("book_authod")String book_authod, @Param("book_type")Integer book_type);

    public book get_book(@Param("book_id")Integer book_id);

    public int sellbook(@Param("book_id")Integer book_id);

    public int addsell(@Param("book_price")BigDecimal book_price, @Param("book_name")String book_name, @Param("book_authod")String book_authod, @Param("book_type")Integer book_type,@Param("handler")String handler);

    public int loan_Book(bookloan bookloan);

    public int getloanCount(@Param("book_name")String book_name, @Param("loan_loantimeP")String loan_loantimeP, @Param("loan_loantimeS")String loan_loantimeS);

    public List<bookloan> getBookloanList(@Param("book_name")String book_name, @Param("loan_loantimeP")String loan_loantimeP
            , @Param("loan_loantimeS")String loan_loantimeS,@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize);

    public int return_book(@Param("loan_id")Integer loan_id);

    public List<booksell> getbooksell(@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize);

    public int getbooksellCount();
}
