package com.example.BitlyCloneApplication.controller;

import com.example.BitlyCloneApplication.DTO.URLMappingDTO;
import com.example.BitlyCloneApplication.DTO.UrlMapData;
import com.example.BitlyCloneApplication.Exception.CustomException;
import com.example.BitlyCloneApplication.model.User;
import com.example.BitlyCloneApplication.repository.UserRepository;
import com.example.BitlyCloneApplication.service.JasperReportService;
import com.example.BitlyCloneApplication.service.UrlMappingService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/url")
public class URLMappingController {

    private UserRepository userRepository;
    private UrlMappingService urlMappingService;
    private JasperReportService jasperService;

    @Autowired
    public URLMappingController(UserRepository userRepository,UrlMappingService urlMappingService,JasperReportService jasperService){
        this.userRepository=userRepository;
        this.urlMappingService=urlMappingService;
        this.jasperService=jasperService;
    }

//    @GetMapping("/shorturl")
//    public String createShortUrl(){
//        return "urlpage";
//    }

    private Map<String,String> populateKeyValue(UrlMapData keyvalue){
        Map<String,String> keyvaluedata=new HashMap<>();
        keyvaluedata.put(keyvalue.getKey(),keyvalue.getValue());
        return keyvaluedata;
    }


//method to create short url
    @GetMapping("/shorturl")
    public ModelAndView createShortUrl(@RequestParam("value") String keyvalue) throws CustomException {
        try{
            System.out.println(keyvalue);
            ModelAndView shorturldata=new ModelAndView();
         //   Map<String,String> originalurlMap=populateKeyValue(keyvalue);
            Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
            String username=authentication.getName();
            User user=userRepository.findByUsernameOrEmail(username,username);
           // String originalUrl=originalurlMap.get(keyvalue.getKey());
            URLMappingDTO urlShort=urlMappingService.createShortUrl(keyvalue,user);
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