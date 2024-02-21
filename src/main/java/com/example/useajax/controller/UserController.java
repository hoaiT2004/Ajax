package com.example.useajax.controller;

import com.example.useajax.model.User;
import com.example.useajax.repository.UserRepository;
import com.example.useajax.service.JwtTokenService;
import com.example.useajax.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api")
public class UserController {

    @Autowired
    private UserRepository userService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserService userService1;


    @GetMapping("/login")
    public String get(){
        return "index";
    }

    @PostMapping("/user")
    public ResponseEntity<?> login(@RequestBody User user) {
        User userDetails = userService.findUserByUsername(user.getUsername());
        if (userDetails != null && user.getPassword().equals(userDetails.getPassword())) {
            String token = jwtTokenService.generateToken(userDetails.getUsername());
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("/create")
    public String create() {
       return "create";
    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/home")
    public String home(){
        return "Success";//nó đ chạy cái này
    }

    @PostMapping("/home")
    public ResponseEntity<?> home2(HttpServletRequest request){
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        token = token.substring(7);
        String username = jwtTokenService.getUsernameFromToken(token);
        System.out.println("Post:token="+token+"\nusername="+username);
        return ResponseEntity.status(HttpStatus.OK).body("123");
    }
}

