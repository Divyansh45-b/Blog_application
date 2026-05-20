package com.divyanshCode.BlogApplication.Security;

import com.divyanshCode.BlogApplication.Entity.Role;
import com.divyanshCode.BlogApplication.Entity.User;
import com.divyanshCode.BlogApplication.Exception.ResourceNotFound;
import com.divyanshCode.BlogApplication.Repository.userRepo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private userRepo userRepo;

    @NotNull
    @Override
    public UserDetails loadUserByUsername(@NotNull String username) throws UsernameNotFoundException
    {

        User user = this.userRepo.findByEmail(username)
                .orElseThrow(()->new ResourceNotFound(username +" username not found"));


        ///used grantedAuthorities to assign roles.
        List<GrantedAuthority> authorities = new ArrayList<>();
        /// forLoop to loop through every List of roles.
        for(Role r : user.getRoles())
        {
           authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
        }

        /// used spring build-in user to return userDetail
        return org.springframework.security.core.userdetails.User.builder()

              .username(user.getEmail())
              .password(user.getPassword())
              .authorities(authorities)
              .build();
    }
}
///jb tk authorities na di ho till then we can make empty list. -> collections.emptyList()