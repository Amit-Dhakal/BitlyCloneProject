package com.example.BitlyCloneApplication.JWTAuthentication;

import com.example.BitlyCloneApplication.model.User;
import com.example.BitlyCloneApplication.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
public class JWTUtils {
  private UserDetails userDetails;
    //extract jwt from headers
    @Autowired
    private UserRepository userService;

   @Value("${jwt.expiration}")
   private String jwtExpirations;

   @Value("${jwt.secret}")
   private String jwtSecret;

    public String getJWTFromHeaders(HttpServletRequest request){
        String bearerToken= request.getHeader("Authorization");
        if(bearerToken!=null && bearerToken.startsWith("Bearer ")){
            System.out.println("JWT TOKEN IS"+bearerToken);
            return bearerToken.substring(7);
        }else{
            return null;
        }
    }

//username,roles can be extracted from this
public String generateJWTToken(UserDetails userDetails){
     // User users=userService.findByUsernameOrEmail(username,password);
    User users=userService.getByUsernameOrPassword(userDetails.getUsername(),userDetails.getPassword());
//List<GrantedAuthority> authorityList= users.getRole().stream().map(roles->new SimpleGrantedAuthority(roles.getRname())).collect(Collectors.toList());
    Collection<SimpleGrantedAuthority> authorityList=List.of(new SimpleGrantedAuthority(users.getRole()));
String jwtToken= Jwts
        .builder()
        .setSubject(users.getUsername())
        .claim("roles",authorityList)
        .setIssuedAt(new Date())
        .setExpiration(new Date(new Date().getTime()+86400))
        .signWith(key())
        .compact();
return jwtToken;
    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

}








//username password supply
//create jwt
//jwt authentication token done
//security and jwt
//