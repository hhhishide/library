package com.spring.springcloudlibrarybook.Dao;

import com.spring.springcloudlibrarybook.pojo.book;
import org.apache.ibatis.annotations.Param;
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

}
