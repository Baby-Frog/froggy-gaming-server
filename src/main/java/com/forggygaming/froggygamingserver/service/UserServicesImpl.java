//package com.forggygaming.froggygamingserver.service;
//
//
//import com.forggygaming.froggygamingserver.entity.Role;
//import com.forggygaming.froggygamingserver.entity.Users;
//import com.forggygaming.froggygamingserver.repository.RoleRepo;
//import com.forggygaming.froggygamingserver.repository.UserRepo;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//@Slf4j
//public class UserServicesImpl implements UserServices, UserDetailsService {
//    private final UserRepo userRepo;
//    private final RoleRepo roleRepo;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public Users saveUser(Users users) {
//        log.info("saving user to database");
//        users.setPassword(passwordEncoder.encode(users.getPassword()));
//        return userRepo.save(users);
//    }
//
//    @Override
//    public Role saveRole(Role role) {
//        log.info("saving user to database");
//        return roleRepo.save(role);
//    }
//
//    @Override
//    public void addRoleToUser(String username, String roleName) {
//        log.info("adding role to user in database");
//
//        Users user=userRepo.findByUsername(username);
//        Role role=roleRepo.findByRoleName(roleName);
//        user.getRoles().add(role);
//    }
//
//    @Override
//    public Users getUser(String username) {
//        log.info("fetching user");
//
//        return userRepo.findByUsername(username);
//    }
//
//    @Override
//    public List<Users> getUsers() {
//        log.info("fetching all user");
//
//        return userRepo.findAll();
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Users user=userRepo.findByUsername(username);
//        if (user==null){
//            log.error("User not found");
//            throw  new UsernameNotFoundException("User not found");
//        }else {
//            log.info("user found in the db:"+username);
//        }
//        Collection<SimpleGrantedAuthority>authorities=new ArrayList<>();
//        user.getRoles().forEach(role ->
//                authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
//        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
//    }
//}
