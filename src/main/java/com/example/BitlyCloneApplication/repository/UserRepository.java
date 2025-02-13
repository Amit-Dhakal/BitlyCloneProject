package com.example.BitlyCloneApplication.repository;

import com.example.BitlyCloneApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsernameOrEmail(String username,String email);
    @Query(value="select * from user where username=?1 or password=?2",nativeQuery = true)
    User getByUsernameOrPassword(String username,String password);
}







//jpql hql are same
//querparam,native query
//