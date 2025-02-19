package com.example.BitlyCloneApplication.config;

import com.example.BitlyCloneApplication.model.User;
import com.example.BitlyCloneApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userService.findByUsernameOrPassword(username,username);
      //  Collection<GrantedAuthority> authorityList=user.getRole().stream().map(roles->new SimpleGrantedAuthority(roles.getRname())).collect(Collectors.toList());
        Collection<SimpleGrantedAuthority> authorityList= List.of(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorityList);
    }


    //@Autowired
//    UserService userService;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user=userService.findByUsernameOrEmail(username,username);
//        List<GrantedAuthority> authorityList=user.getRole().stream().map(roles->new SimpleGrantedAuthority(roles.getRname())).collect(Collectors.toList());
//        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorityList);
//    }


}