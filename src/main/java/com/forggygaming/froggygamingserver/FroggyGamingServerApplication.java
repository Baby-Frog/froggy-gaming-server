package com.forggygaming.froggygamingserver;

import com.forggygaming.froggygamingserver.entity.Customer;
import com.forggygaming.froggygamingserver.entity.Role;
//import com.forggygaming.froggygamingserver.entity.Users;
//import com.forggygaming.froggygamingserver.service.UserServices;
import com.forggygaming.froggygamingserver.service.CustomerServices;
//import com.forggygaming.froggygamingserver.service.UserServices;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class FroggyGamingServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FroggyGamingServerApplication.class, args);
    }
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder() ;
//    }
//    @Bean
//    CommandLineRunner run(CustomerServices customerservices){
//        return args -> {
//            customerservices.saveRole(new Role("ROLE_USER"));
//            customerservices.saveRole(new Role("ROLE_ADMIN"));
//            customerservices.saveRole(new Role("ROLE_MANAGER"));
//            customerservices.saveRole(new Role("ROLE_SUPER_ADMIN"));
//
//            customerservices.insertCustomer(new Customer("vietvo","123"));
//            customerservices.insertCustomer(new Customer("namdan","123"));
//            customerservices.insertCustomer(new Customer("minhnhi","123"));
//            customerservices.insertCustomer(new Customer("khoingan","123"));
////
//            customerservices.addRoleToCustomer("vietvo","ROLE_USER");
//            customerservices.addRoleToCustomer("namdan","ROLE_USER");
//            customerservices.addRoleToCustomer("minhnhi","ROLE_ADMIN");
//            customerservices.addRoleToCustomer("khoingan","ROLE_SUPER_ADMIN");
//        };
//    }
}
