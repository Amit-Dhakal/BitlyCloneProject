package com.example.BitlyCloneApplication.routes;

import com.example.BitlyCloneApplication.model.ClickEvent;
import com.example.BitlyCloneApplication.model.URLMapping;
import com.example.BitlyCloneApplication.model.User;
import com.example.BitlyCloneApplication.repository.ClickRepo;
import com.example.BitlyCloneApplication.repository.UrlMappingRepo;
import com.example.BitlyCloneApplication.repository.UserRepository;
import com.example.BitlyCloneApplication.service.UrlMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;

@RestController
@Configuration
public class RedirectController {

   private UrlMappingRepo urlMappingRepo;
   private ClickRepo clickRepo;
   private UserRepository userRepo;

    @Autowired
  public RedirectController(UrlMappingRepo urlMappingRepo,ClickRepo clickRepo,UserRepository userRepo){
      this.urlMappingRepo=urlMappingRepo;
      this.clickRepo=clickRepo;
      this.userRepo=userRepo;
  }

//    @GetMapping("/redirect/{shortUrl}")
//public RedirectView getOriginalURL(@PathVariable String shortUrl){
//    //steps
//    //get all by shorturl
//    //get originalurl by shorturl
//    //if method of originalurl is not present then create one method of original url returning
//    //redirect to original url page
//    URLMapping urlMapping=urlMappingRepo.findByShortUrl(shortUrl);
//    String originalUrl=urlMapping.getOriginalUrl();
//    return new RedirectView(originalUrl);
//}

    @GetMapping("/redirect/{shortUrl}")
public ResponseEntity<Void> getOriginalURL(@PathVariable String shortUrl){
//1 urlmap can have multiple click
      //  clickEvent.setClick_id();
        URLMapping urlMapping=urlMappingRepo.findByShortUrl(shortUrl);
        String originalUrl=urlMapping.getOriginalUrl();

        if(urlMapping!=null){

            ClickEvent clickEvent=new ClickEvent();

           urlMapping.setClickCount(urlMapping.getClickCount()+1);
            urlMappingRepo.save(urlMapping);
            clickEvent.setClickDate(LocalDate.now());
            clickEvent.setUrlMapping(urlMapping);
            clickEvent.setCount(clickEvent.getCount()+1);
            clickRepo.save(clickEvent);
    }
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("Location",originalUrl);
return ResponseEntity.status(301).headers(httpHeaders).build();
}

    @GetMapping("/totalclicks/{shortUrl}")
    public ResponseEntity<Void> getTotalClicksOnUrlByDate(@PathVariable String shortUrl) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         String username= authentication.getName();
         User user=userRepo.findByUsernameOrEmail(username,username);

        ClickEvent clickEvent=new ClickEvent();
        List<URLMapping> userUrl=urlMappingRepo.findByUser(user);
        URLMapping urlMapping=urlMappingRepo.findByShortUrl(shortUrl);
       String originalUrl= urlMapping.getOriginalUrl();
       System.out.println("ORIGINAL URL IS :::"+originalUrl);

       if(originalUrl!=null){
           clickEvent.setClickDate(LocalDate.now());
           clickEvent.setUrlMapping(urlMapping);
         // clickEvent.setCount(urlMapping.getClickCount()+1);
          clickEvent.setCount(clickEvent.getCount()+1);
           urlMapping.setUser(user);
           urlMapping.setClickCount(urlMapping.getClickCount()+1);
           urlMappingRepo.save(urlMapping);
           clickRepo.save(clickEvent);
       }
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("Location",originalUrl);
        return ResponseEntity.status(301).headers(httpHeaders).build();
    }
}







//refactoring
//check SOLID PRINCIPLES
//CODE FIORMATTING
//EXCEPTION HANDLING ,CUSTOM EXCEPTION HANDLING
//REWRITING CODE IF NECESSARY BASED ON CODE LENGTH SIZE REDUCTION
//UNNECESSARY CODE REMOVAL,WRITE CODE MIMINIMUM AS POSSIBLE
//SCALING