package com.example.BitlyCloneApplication.DTO;

import com.example.BitlyCloneApplication.model.ClickEvent;
import com.example.BitlyCloneApplication.model.User;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class URLMappingDTO {

    private String click_count;
    private String original_url;
    private String short_url;
    private LocalDate created_date;
    private User user;

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "URLMappingDTO{" +
                "click_count='" + click_count + '\'' +
                ", original_url='" + original_url + '\'' +
                ", short_url='" + short_url + '\'' +
                ", created_date=" + created_date +
                ", user=" + user +
                ", username='" + username + '\'' +
                ", click_id=" + click_id +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;
    private List<ClickEvent> click_id;


    public String getClick_count() {
        return click_count;
    }

    public void setClick_count(String click_count) {
        this.click_count = click_count;
    }

    public String getOriginal_url() {
        return original_url;
    }

    public void setOriginal_url(String original_url) {
        this.original_url = original_url;
    }

    public String getShort_url() {
        return short_url;
    }

    public void setShort_url(String short_url) {
        this.short_url = short_url;
    }

    public List<ClickEvent> getClick_id() {
        return click_id;
    }

    public void setClick_id(List<ClickEvent> click_id) {
        this.click_id = click_id;
    }

    public void setUser(User user) {
    }
}


//steps to do
//url shortening logic
//origginal url short url map
//any user can have multiple list of url
//find all url by username
//create a short url
//
