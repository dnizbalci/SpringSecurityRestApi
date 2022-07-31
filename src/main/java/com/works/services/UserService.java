package com.works.services;

import com.works.entities.Role;
import com.works.entities.User;
import com.works.repositories.RoleRepository;
import com.works.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserService implements UserDetailsService {

    final UserRepository uRepo;
    final PasswordEncoder encoder;
    final RoleRepository rRepo;

    public UserService(UserRepository uRepo, @Lazy PasswordEncoder encoder, RoleRepository rRepo) {
        this.uRepo = uRepo;
        this.encoder = encoder;
        this.rRepo = rRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDetails userDetails = null;
    Optional<User> oUser = uRepo.findByEmailEqualsIgnoreCase(username);
        if (oUser.isPresent()){
        User vtUser =oUser.get();
        userDetails = new org.springframework.security.core.userdetails.User(
                username,
                vtUser.getPassword(),
                vtUser.isEnabled(),
                vtUser.isTokenExpired(),
                true,
                true,
                getAuthorities(vtUser.getRoles())
        );
    }else {
        throw new UsernameNotFoundException("User not found");
    }
     return userDetails;
     }

    private List<GrantedAuthority> getAuthorities (List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority( role.getName() ));
        }
        return authorities;
    }


    public ResponseEntity register (User user) {
        Optional<User> oUser =uRepo.findByEmailEqualsIgnoreCase(user.getEmail());
        Map<String, Object> hm = new LinkedHashMap<>();
        if (oUser.isPresent()){
            hm.put("status", false);
            hm.put("message", "User is already registered");
            hm.put("result", user);
            return new ResponseEntity(hm,HttpStatus.BAD_REQUEST);
        } else {
            user.setPassword(encoder.encode(user.getPassword()));
            hm.put("status", true);
            hm.put("result", uRepo.save(user));
            return new ResponseEntity(hm,HttpStatus.OK);
        }

    }

    @Bean
    public PasswordEncoder encoder () {
        return new BCryptPasswordEncoder();
    }



    public ResponseEntity updateUser (User user){
        Map<String, Object> hm = new LinkedHashMap<>();
        Optional<User> oUser = uRepo.findByUidEquals(user.getUid());
        if (oUser.isPresent()){
            User dbUser = oUser.get();
            dbUser.setFirstName(user.getFirstName());
            dbUser.setLastName(user.getLastName());
            dbUser.setEmail(user.getEmail());
            dbUser.setPassword(user.getPassword());
            dbUser.setRoles(user.getRoles());
            uRepo.saveAndFlush(dbUser);
            hm.put("status", true);
            hm.put("message", "User information is uptated");
            hm.put("result", dbUser);
        }else{
            hm.put("status", false);
            hm.put("message","User Not Found" );
        }
        return new ResponseEntity(hm, HttpStatus.OK);
    }
}
