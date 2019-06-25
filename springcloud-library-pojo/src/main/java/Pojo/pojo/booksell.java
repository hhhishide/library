package Pojo.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 书籍出售类
 */
public class booksell {
    private int id;
    private String book_name;
    private int book_type;
    private String authod;
    private Date sell_time;
    private BigDecimal price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public int getBook_type() {
        return book_type;
    }

    public void setBook_type(int book_type) {
        this.book_type = book_type;
    }

    public String getAuthod() {
        return authod;
    }

    public void setAuthod(String authod) {
        this.authod = authod;
    }

    public Date getSell_time() {
        return sell_time;
    }

    public void setSell_time(Date sell_time) {
        this.sell_time = sell_time;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
