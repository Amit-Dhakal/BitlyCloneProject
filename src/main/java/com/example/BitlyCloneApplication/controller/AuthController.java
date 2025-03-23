package com.example.BitlyCloneApplication.controller;

import com.example.BitlyCloneApplication.DTO.LoginRequest;
import com.example.BitlyCloneApplication.DTO.RegisterRequest;
import com.example.BitlyCloneApplication.Exception.CustomException;
import com.example.BitlyCloneApplication.JWTAuthentication.JWTAuthenticationResponse;
import com.example.BitlyCloneApplication.JWTAuthentication.JWTUtils;
import com.example.BitlyCloneApplication.model.User;
import com.example.BitlyCloneApplication.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth/public")
public class AuthController {

   @Value("${jwt.secret}")
    private String secretKey;

   private UserRepository userRepository;
   private PasswordEncoder passwordEncoder;
   private AuthenticationManager authenticationManager;
   private JWTUtils jwtUtils;

    @Autowired
    public AuthController(UserRepository userRepository,PasswordEncoder passwordEncoder,AuthenticationManager authenticationManager,JWTUtils jwtUtils){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.authenticationManager=authenticationManager;
        this.jwtUtils=jwtUtils;
    }

    @GetMapping("/login")
    public String loginUserPage() {
        return "userlogin";
    }

    @GetMapping("/register")
    public String registerUserPage() {
        return "RegisterUser";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute RegisterRequest registerUser) throws CustomException {
        try{
            User user = new User();
            user.setUsername(registerUser.getUsername());
            user.setPassword(passwordEncoder.encode(registerUser.getPassword()));
            user.setEmail(registerUser.getEmail());
            user.setRole(registerUser.getRole());
            userRepository.save(user);
            return "userlogin";
        } catch (Exception ce) {
            throw new CustomException(ce.getMessage(),ce.getCause());
        }
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute LoginRequest loginRequest,Model authdata) throws CustomException {
        try{
            JWTAuthenticationResponse authenticationResponse =new JWTAuthenticationResponse();

          //  authdata.setViewName("accessToken");
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String access_token = jwtUtils.generateJWTToken(userDetails);

            Claims claims=decodeJWTToken(access_token);

            authenticationResponse.setUsers(claims.getSubject());
            authenticationResponse.setExpiration(claims.getExpiration());
            authenticationResponse.setIssuedAT(claims.getIssuedAt());
            authenticationResponse.setToken(access_token);
          //  authenticationResponse.setRoles(claims.get(userDetails.getAuthorities()).toString());
            authdata.addAttribute("authenticationResponse",authenticationResponse);

        }catch (Exception ex){
            throw new CustomException(ex.getMessage(),ex.getCause());
        }
        return "accessToken";
    }

    private Claims decodeJWTToken(String accessToken){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(accessToken)
                .getBody();
    }

}








//used in authentication
//custom login page create
//endpoint authenticate endpoints like register login view CRUD OPERATOIN
//Custom security page
//custom JWT page
//redirect page