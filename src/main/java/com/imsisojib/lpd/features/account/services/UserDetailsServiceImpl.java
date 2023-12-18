package com.imsisojib.lpd.features.account.services;

import com.imsisojib.lpd.features.account.enums.ERole;
import com.imsisojib.lpd.features.account.models.entities.Role;
import com.imsisojib.lpd.features.account.models.entities.User;
import com.imsisojib.lpd.features.account.repositories.RepositoryRole;
import com.imsisojib.lpd.features.account.repositories.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    RepositoryUser repositoryUser;

    @Autowired
    RepositoryRole repositoryRole;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repositoryUser.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

    public UserDetails findUserDetailsByEmail(String email){
        User user = repositoryUser.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));

        return UserDetailsImpl.build(user);
    }

    public UserDetails findUserDetailsById(Long id){
        User user = repositoryUser.findUserById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + id));

        return UserDetailsImpl.build(user);
    }

    public Optional<User> findUserByEmail(String email){
        return  repositoryUser.findUserByEmail(email);
    }

    public User saveUser(User user){
        return repositoryUser.save(user);
    }

    public Optional<Role> findRoleByName(ERole name){
        return  repositoryRole.findByName(name);
    }

}
