package com.forggygaming.froggygamingserver.service;
import com.forggygaming.froggygamingserver.entity.Customer;
//import com.forggygaming.froggygamingserver.entity.Users;
//import com.forggygaming.froggygamingserver.repository.UserRepo;
import com.forggygaming.froggygamingserver.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    CustomerRepo customerRepo;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepo.findByUsername(username);
        return CustomerDetailImpl.build(customer);
    }
}
