package Pojo.pojo;

/**
 * 出勤类
 */
public class attendance {
    private int id;
    private int userid;
    private String morning_time;
    private String evening_time;
    private Integer morning_state;
    private Integer evening_state;
    private String Ondate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getMorning_time() {
        return morning_time;
    }

    public void setMorning_time(String morning_time) {
        this.morning_time = morning_time;
    }

    public String getEvening_time() {
        return evening_time;
    }

    public void setEvening_time(String evening_time) {
        this.evening_time = evening_time;
    }

    public Integer getMorning_state() {
        return morning_state;
    }

    public void setMorning_state(Integer morning_state) {
        this.morning_state = morning_state;
    }

    public Integer getEvening_state() {
        return evening_state;
    }

    public void setEvening_state(Integer evening_state) {
        this.evening_state = evening_state;
    }

    public String getOndate() {
        return Ondate;
    }

    public void setOndate(String ondate) {
        Ondate = ondate;
    }
}
