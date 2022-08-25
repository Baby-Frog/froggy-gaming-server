package com.forggygaming.froggygamingserver;

import com.forggygaming.froggygamingserver.entity.Customer;
import com.forggygaming.froggygamingserver.entity.Role;
import com.forggygaming.froggygamingserver.entity.Users;
import com.forggygaming.froggygamingserver.service.UserServices;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class FroggyGamingServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FroggyGamingServerApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder() ;
    }
    @Bean
    CommandLineRunner run(UserServices userServices){
        return args -> {
            userServices.saveRole(new Role(1,"ROLE_USER"));
            userServices.saveRole(new Role(2,"ROLE_ADMIN"));
            userServices.saveRole(new Role(3,"ROLE_MANAGER"));
            userServices.saveRole(new Role(4,"ROLE_SUPER_ADMIN"));

            userServices.saveUser(new Users("viet","vietvo","123",new ArrayList<>(),new Customer()));
            userServices.saveUser(new Users("nam","namdan","123",new ArrayList<>(),new Customer()));
            userServices.saveUser(new Users("minh","minhnhi","123",new ArrayList<>(),new Customer()));
            userServices.saveUser(new Users("khoi","khoingan","123",new ArrayList<>(),new Customer()));

            userServices.addRoleToUser("vietvo","ROLE_USER");
            userServices.addRoleToUser("namdan","ROLE_USER");
            userServices.addRoleToUser("minhnhi","ROLE_ADMIN");
            userServices.addRoleToUser("khoingan","ROLE_SUPER_ADMIN");
        };
    }
}
