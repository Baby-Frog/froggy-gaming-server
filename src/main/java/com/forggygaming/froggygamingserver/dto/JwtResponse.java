package com.forggygaming.froggygamingserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class JwtResponse {
    private String access_token;
    private Long Cusid;
    private String username;
    private String password;
    private List<String> roles;




}
