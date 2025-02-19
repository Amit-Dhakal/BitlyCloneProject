package com.example.BitlyCloneApplication.repository;

import com.example.BitlyCloneApplication.model.URLMapping;
import com.example.BitlyCloneApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UrlMappingRepo extends JpaRepository<URLMapping,Integer> {
   // User findByUsernameOrEmail(String username,String email);
Optional<URLMapping> findByShortUrl(String shortUrl);
List<URLMapping> findByUser(User user);
}
