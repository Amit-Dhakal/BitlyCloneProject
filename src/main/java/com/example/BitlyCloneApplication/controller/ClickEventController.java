package com.example.BitlyCloneApplication.controller;

import com.example.BitlyCloneApplication.model.ClickEvent;
import com.example.BitlyCloneApplication.model.URLMapping;
import com.example.BitlyCloneApplication.model.User;
import com.example.BitlyCloneApplication.repository.ClickRepo;
import com.example.BitlyCloneApplication.repository.UrlMappingRepo;
import com.example.BitlyCloneApplication.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/v1/public")
public class ClickEventController {
    private UserRepository userRepository;
    private ClickRepo clickRepo;
    private  UrlMappingRepo urlMappingRepo;
    Logger log = LoggerFactory.getLogger(Logger.class);

    @Autowired
    public ClickEventController(UserRepository userRepository,ClickRepo clickRepo,UrlMappingRepo urlMappingRepo){
        this.userRepository=userRepository;
        this.clickRepo=clickRepo;
        this.urlMappingRepo=urlMappingRepo;
    }

    @GetMapping("/analytics/{short_url}")
    public String getUrlAnalytics(@PathVariable("short_url") String shorturl, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS") LocalDate startDate, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS") LocalDate endDate, Model userurlData) {
        Optional<URLMapping> urlMapping = urlMappingRepo.findByShortUrl(shorturl);
        log.info("URL MAPPING ORIGINAL URL" + urlMapping.get().getOriginalUrl());
        List<ClickEvent> clickEventList = clickRepo.findByUrlMappingAndClickDateBetween(urlMapping.get(),startDate,endDate);
        log.info("LIST OF CLICK EVENT DATA" + clickEventList);
        userurlData.addAttribute("clickEventList",clickEventList);
        return "urlpage";
    }

    @GetMapping("/totalclicks")
    public String getTotalClicksOnUrlByDate(Authentication authentication,@RequestParam LocalDate startDate,@RequestParam LocalDate endDate,Model model) {
        String username=authentication.getName();
        Optional<User> user=userRepository.findByUsernameOrEmail(username,username);
        List<URLMapping> urlMappingList=urlMappingRepo.findByUser(user.get());
List<ClickEvent> clickEventList=clickRepo.findByUrlMappingInAndClickDateBetween(urlMappingList,startDate,endDate.plusDays(1));
        Map<LocalDate,Long> clickeventMapping=clickEventList.stream().collect(Collectors.groupingBy(click->click.getClickDate(),Collectors.counting()));
model.addAttribute("clickeventMapping",clickeventMapping);
return "urlpage";
    }
}








 //@PostMapping("/url/save")
 //public String saveClickEvent(Model model){
    // ClickEvent clickEvent=new ClickEvent();
    // clickEvent.setClick_id();
  //   clickEvent.setClick_date();
// }


 //private String convertLongUrlToShortUrl(String shortUrl){

    //mapping
     //input shortUrl --->>> longurl
     //
// }

//controller
//fetch information of click id click date no of click count
//save click event
//map longurl to shorturl
//getanalytics ---- displays all the users and url informations
//fetch data as list

//how to find total clicks ?
//steps to find totalclicks on url
//check if url is hit then increase click count
//user--->>>urlmapping---->>>clickeventmapping
//relations user has many urlmapping
//one url mapping can have multiple clicks
//user--urlmapping
//urlmapping--multiple clickevent
//
 //find user by username
//urlmaaping fetch
//if urlmapping is not null
//

//find totalnumber iof click on any date range ?
//