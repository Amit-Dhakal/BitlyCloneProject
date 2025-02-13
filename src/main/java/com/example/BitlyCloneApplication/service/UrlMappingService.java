package com.example.BitlyCloneApplication.service;

import com.example.BitlyCloneApplication.DTO.URLMappingDTO;
import com.example.BitlyCloneApplication.model.URLMapping;
import com.example.BitlyCloneApplication.model.User;
import com.example.BitlyCloneApplication.repository.UrlMappingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class UrlMappingService {

    @Autowired
    UrlMappingRepo urlMappingRepo;

    public URLMappingDTO createShortUrl(String originalURL,User user) {
        String shortURL = generateShortURL(originalURL,user);
        URLMapping urlMapping = new URLMapping();
        urlMapping.setOriginalUrl(originalURL);
        urlMapping.setShortUrl(shortURL);
        urlMapping.setCreatedDate(LocalDateTime.now());
      //  urlMapping.setClick_id();
     //   urlMapping.setClick_count();
        urlMapping.setUser(user);
        urlMappingRepo.save(urlMapping);
        URLMappingDTO urlMappingDTO =new URLMappingDTO();
        urlMappingDTO.setOriginal_url(urlMapping.getOriginalUrl());
        urlMappingDTO.setShort_url(urlMapping.getShortUrl());
   //     urlMappingDTO.setCreated_date(urlMapping.getCreatedDate());
        urlMappingDTO.setUser(urlMapping.getUser());
      //  urlMappingDTO.setClick_count(urlMapping.getClickCount());
        return urlMappingDTO;
    }

     //logic building part
    private String generateShortURL(String originalURL, User user) {
        String characters="ABCDEFGHIJKLMNOPQRSTUVWXYZABCDasdadhjjklmnopqr1278965";
        Random random=new Random();
        StringBuilder shorturl=new StringBuilder(10);
        for(int i=0;i<10;i++){
            shorturl.append(characters.charAt(random.nextInt(characters.length())));
        }
        return shorturl.toString();
    }


}
