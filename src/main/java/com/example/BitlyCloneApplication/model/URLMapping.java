package com.example.BitlyCloneApplication.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class URLMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer url_id;

    @Column(name="click_count")
    private long clickCount;

    @Column(name="original_url")
    private String originalUrl;

    @Column(name="short_url")
    private String shortUrl;

    @Column(name="created_date")
    private LocalDateTime createdDate;

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "URLMapping{" +
                "url_id=" + url_id +
                ", clickCount='" + clickCount + '\'' +
                ", originalUrl='" + originalUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                ", createdDate=" + createdDate +
                ", user=" + user +
                ", click_id=" + click_id +
                '}';
    }

    @ManyToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(fetch= FetchType.EAGER,cascade = CascadeType.ALL)
    private List<ClickEvent> click_id;

    public Integer getUrl_id() {
        return url_id;
    }

    public void setUrl_id(Integer url_id) {
        this.url_id = url_id;
    }


    public List<ClickEvent> getClick_id() {
        return click_id;
    }

    public void setClick_id(List<ClickEvent> click_id) {
        this.click_id = click_id;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public long getClickCount() {
        return clickCount;
    }

    public void setClickCount(long clickCount) {
        this.clickCount = clickCount;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

}
