package lv.spoti.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lv.spoti.models.User;
import lv.spoti.repos.IUserRepo;



@Service
public class MyUserDetailsService implements UserDetailsService {

    private IUserRepo userRepo;

    public MyUserDetailsService(IUserRepo userRepos) {
        this.userRepo = userRepos;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findUserByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("No user found with email");
        }
       
        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .roles(user.getRole().toString())
                        .build();

        return userDetails;
    }
}