package com.forggygaming.froggygamingserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class JwtResponse {
    private String jwt;
    private Long Cusid;
    private String username;
    private String password;
    private List<String> roles;




}
