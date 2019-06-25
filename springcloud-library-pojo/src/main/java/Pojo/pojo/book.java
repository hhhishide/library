package Pojo.pojo;

import java.math.BigDecimal;

/**
 * 书籍信息类
  */
public class book {
    private int book_id;
    private String book_name;
    private String book_authod;
    private BigDecimal book_price;
    private int book_state;
    private int book_type;
    private String book_addtime;
    private int book_damage;

    public String getBook_addtime() {
        return book_addtime;
    }

    public void setBook_addtime(String book_addtime) {
        this.book_addtime = book_addtime;
    }

    public int getBook_damage() {
        return book_damage;
    }

    public void setBook_damage(int book_damage) {
        this.book_damage = book_damage;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_authod() {
        return book_authod;
    }

    public void setBook_authod(String book_authod) {
        this.book_authod = book_authod;
    }

    public BigDecimal getBook_price() {
        return book_price;
    }

    public void setBook_price(BigDecimal book_price) {
        this.book_price = book_price;
    }

    public int getBook_state() {
        return book_state;
    }

    public void setBook_state(int book_state) {
        this.book_state = book_state;
    }

    public int getBook_type() {
        return book_type;
    }

    public void setBook_type(int book_type) {
        this.book_type = book_type;
    }
}
