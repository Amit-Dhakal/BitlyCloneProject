package com.example.BitlyCloneApplication.DTO;
import lombok.Data;
import java.util.Date;

@Data
public class ClickEventDTO {

    private long count;
    private Date click_date;

    public long getCount() {
        return count;
    }
    public void setCount(long count) {
        this.count = count;
    }
    public Date getClick_date() {
        return click_date;
    }
    public void setClick_date(Date click_date) {
        this.click_date = click_date;
    }
}

// any one user have multiple url
//one user any one url and total number of clicks on that url
//click count should be on list how much count is made
//