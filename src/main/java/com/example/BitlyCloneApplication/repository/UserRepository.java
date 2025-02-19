package com.example.BitlyCloneApplication.repository;

import com.example.BitlyCloneApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUsernameOrEmail(String username,String email);
  //  User findByUsernameOrEmail(String username,String email);
    @Query(value="select * from user where username=?1 or password=?2",nativeQuery = true)
    Optional<User> getByUsernameOrPassword(String username,String password);
   // @Query(value="select * from user where username=?1 or password=?2",nativeQuery = true)
    User findByUsernameOrPassword(String username,String password);
}







//jpql hql are same
//querparam,native query
//