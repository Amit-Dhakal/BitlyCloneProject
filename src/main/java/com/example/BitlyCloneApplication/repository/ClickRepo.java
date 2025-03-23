package com.example.BitlyCloneApplication.repository;
import com.example.BitlyCloneApplication.model.ClickEvent;
import com.example.BitlyCloneApplication.model.URLMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClickRepo extends JpaRepository<ClickEvent,Integer> {
   // @Query(value = "SELECT * FROM ClickEvent ce WHERE urlMapping = ?1 AND click_date BETWEEN ?2 AND ?3", nativeQuery = true)
    List<ClickEvent> findByUrlMappingAndClickDateBetween(URLMapping urlMapping,LocalDate start,LocalDate end);
    List<ClickEvent> findByUrlMappingInAndClickDateBetween(List<URLMapping> urlMapping,LocalDate start,LocalDate end);

    List<ClickEvent> findAll();

}
