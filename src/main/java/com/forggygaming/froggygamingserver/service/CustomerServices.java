package com.forggygaming.froggygamingserver.service;


import com.forggygaming.froggygamingserver.entity.Customer;
//import com.forggygaming.froggygamingserver.entity.Users;
import com.forggygaming.froggygamingserver.entity.ResponseObject;
import com.forggygaming.froggygamingserver.entity.Role;
import com.forggygaming.froggygamingserver.repository.CustomerRepo;
//import com.forggygaming.froggygamingserver.repository.UserRepo;
import com.forggygaming.froggygamingserver.repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServices {

    private final CustomerRepo customerRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    public Customer findByCusEmail(String email) {
        return customerRepo.findByCusEmail(email);
    }
    public Customer findByCusPhoneNumber(Long phoneNumber){
        return customerRepo.findByCusPhoneNumber(phoneNumber);
    }
    public List<Customer> getAllCustomer() {
        return customerRepo.findAll();
    }

    public Customer updateCustomer(Customer customer){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Customer found= customerRepo.findByUsername(currentPrincipalName);
        found.setCusAddress(customer.getCusAddress());
        found.setCusEmail(customer.getCusEmail());
        found.setUpdatedAt(LocalDate.now());
        found.setCusFirstname(customer.getCusFirstname());
        found.setCusAvatarPath(customer.getCusAvatarPath());
        found.setCusPhoneNumber(customer.getCusPhoneNumber());
        found.setPassword(customer.getPassword());
        return customerRepo.save(found);
    }
    public Customer insertCustomer(Customer customer) {
        log.info("Saving customer....");
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepo.save(customer);
    }

    public Role saveRole(Role roleName){
        log.info("Saving role..."+roleName);
       return roleRepo.save(roleName);
    }
    public void addRoleToCustomer(String username,String roleName){
        Role role=roleRepo.findByRoleName(roleName);
        Customer customer=customerRepo.findByUsername(username);
        log.info("Add role to customer...");
        customer.getRoles().add(role);
        customerRepo.save(customer);
    }
    public Customer findByCusId(Long id){
        return customerRepo.findById(id).orElseThrow(RuntimeException::new);
    }
    public Customer ViewProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        log.info("User:"+currentPrincipalName);
        Customer customer = customerRepo.findByUsername(currentPrincipalName);

        return customer ;
    }

}
