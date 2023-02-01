package com.imsisojib.lpd.security.services;

import com.imsisojib.lpd.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  /*@Autowired
  UserRepository userRepository;*/

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    /*User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));*/
    User user = new User();
    user.setUsername("imsisojib");
    user.setId(1l);
    user.setEmail("imsisojib@gmail.com");
    user.setPassword("12345678");

    return UserDetailsImpl.build(user);
  }

}
