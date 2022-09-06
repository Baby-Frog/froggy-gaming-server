package com.forggygaming.froggygamingserver;

//import com.forggygaming.froggygamingserver.service.UserServices;
import com.forggygaming.froggygamingserver.entity.Customer;
import com.forggygaming.froggygamingserver.entity.Role;
import com.forggygaming.froggygamingserver.service.CustomerServices;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class FroggyGamingServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FroggyGamingServerApplication.class, args);
    }

//    @Bean
//    CommandLineRunner run(CustomerServices customerServices){
//        return args -> {
//            customerServices.saveRole(new Role(1,"ROLE_USER"));
//            customerServices.saveRole(new Role(2,"ROLE_ADMIN"));
//            customerServices.saveRole(new Role(3,"ROLE_MANAGER"));
//            customerServices.saveRole(new Role(4,"ROLE_SUPER_ADMIN"));
//
//            customerServices.insertCustomer(new Customer("vietvo","123"));
//            customerServices.insertCustomer(new Customer("namdan","123"));
//            customerServices.insertCustomer(new Customer("minhnhi","123"));
//            customerServices.insertCustomer(new Customer("khoingan","123"));
//
//            customerServices.addRoleToCustomer("vietvo","ROLE_USER");
//            customerServices.addRoleToCustomer("namdan","ROLE_USER");
//            customerServices.addRoleToCustomer("minhnhi","ROLE_ADMIN");
//            customerServices.addRoleToCustomer("khoingan","ROLE_SUPER_ADMIN");
//        };
//    }
}
