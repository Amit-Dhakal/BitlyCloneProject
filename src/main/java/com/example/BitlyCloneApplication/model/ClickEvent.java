package com.example.BitlyCloneApplication.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class ClickEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer click_id;

    @Column(name="click_count")
    private long count;


    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Column(name="click_date")
    private LocalDate clickDate;

    public LocalDate getClickDate() {
        return clickDate;
    }

    public void setClickDate(LocalDate clickDate) {
        this.clickDate = clickDate;
    }

    public URLMapping getUrlMapping() {
        return urlMapping;
    }

    public void setUrlMapping(URLMapping urlMapping) {
        this.urlMapping = urlMapping;
    }

    @ManyToOne
    private URLMapping urlMapping;

    public Integer getClick_id() {
        return click_id;
    }

    public void setClick_id(Integer click_id) {
        this.click_id = click_id;
    }

}
