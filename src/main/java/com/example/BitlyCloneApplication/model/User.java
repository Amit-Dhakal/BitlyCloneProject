package com.example.BitlyCloneApplication.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer userid;
    private String username;
    private String email;
    private String password;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public List<URLMapping> getUrl_id() {
        return url_id;
    }

    public void setUrl_id(List<URLMapping> url_id) {
        this.url_id = url_id;
    }

  //  @OneToOne(fetch= FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "USERS_ROLES",joinColumns = @JoinColumn(name = "userid", referencedColumnName = "userid")
            ,inverseJoinColumns = @JoinColumn(name = "rid",referencedColumnName = "rid"))
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @OneToMany
    private List<URLMapping> url_id;

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", url_id=" + url_id +
                '}';
    }
}
