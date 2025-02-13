package com.example.BitlyCloneApplication.controller;

import com.example.BitlyCloneApplication.DTO.URLMappingDTO;
import com.example.BitlyCloneApplication.DTO.UrlMapData;
import com.example.BitlyCloneApplication.DTO.UserDTO;
import com.example.BitlyCloneApplication.Exception.CustomException;
import com.example.BitlyCloneApplication.model.User;
import com.example.BitlyCloneApplication.repository.UserRepository;
import com.example.BitlyCloneApplication.service.UrlMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/url")
public class URLMappingController {

    private UserRepository userRepository;
    private UrlMappingService urlMappingService;
    @Autowired
    public URLMappingController(UserRepository userRepository,UrlMappingService urlMappingService){
        this.userRepository=userRepository;
        this.urlMappingService=urlMappingService;
    }

    @GetMapping("/shorturl")
    public String createShortUrl(){
        return "urlpage";
    }

    private Map<String,String> populateKeyValue(UrlMapData keyvalue){
        Map<String,String> keyvaluedata=new HashMap<>();
        keyvaluedata.put(keyvalue.getKey(),keyvalue.getValue());
        return keyvaluedata;
    }

    @PostMapping("/shorturl")
    public ModelAndView createShortUrl(@ModelAttribute UrlMapData keyvalue) throws CustomException {

        try{
            ModelAndView shorturldata=new ModelAndView();
            Map<String,String> originalurlMap=populateKeyValue(keyvalue);
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String username=authentication.getName();
            User user=userRepository.findByUsernameOrEmail(username,username);
            String originalUrl=originalurlMap.get(keyvalue.getKey());
            URLMappingDTO urlShort=urlMappingService.createShortUrl(originalUrl,user);
            shorturldata.setViewName("urlpage");
            shorturldata.addObject("urlShort",urlShort);
            return shorturldata;
        }catch(Exception ce){
            throw new CustomException(ce.getMessage(),ce.getCause());
        }
    }

}




//find all url mapping by username
//