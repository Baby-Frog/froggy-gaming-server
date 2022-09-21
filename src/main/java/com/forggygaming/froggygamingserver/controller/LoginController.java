package com.forggygaming.froggygamingserver.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.forggygaming.froggygamingserver.dto.JwtResponse;
import com.forggygaming.froggygamingserver.dto.LoginRequest;
import com.forggygaming.froggygamingserver.entity.Customer;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.repository.CustomerRepo;
import com.forggygaming.froggygamingserver.repository.RoleRepo;
//import com.forggygaming.froggygamingserver.repository.UserRepo;
import com.forggygaming.froggygamingserver.security.jwt.JwtUtils;
import com.forggygaming.froggygamingserver.service.CustomerDetailImpl;
import com.forggygaming.froggygamingserver.service.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:3000")

public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    CustomerServices customerServices;
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    RoleRepo roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken obj =
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(obj);


        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        CustomerDetailImpl customerDetail = (CustomerDetailImpl) authentication.getPrincipal();
        List<String> roles = customerDetail.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());


        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "login successfully", new JwtResponse(jwt,
                customerDetail.getId(),
                customerDetail.getUsername(),
                customerDetail.getPassword(),
                roles)));
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseObject> registerUser(@RequestBody Customer customer) {
        Customer found = customerRepo.findByUsername(customer.getUsername());
        return (found == null) ? ResponseEntity.ok()
                .body(new ResponseObject("OK", "Register successfully", customerServices.registerUser(customer)))
                : ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(new ResponseObject("Failed", "username does exist!!!", new ArrayList<>()));
    }
}
