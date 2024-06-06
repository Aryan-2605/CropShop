package CropShop.Support.adminAuth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import CropShop.Support.model.admin;
import CropShop.Support.repositories.adminRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  private adminRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    { 
      admin Admin = repository.findByUsername(username);
      
      if (Admin!=null) {
    	  System.out.println(Admin.getUsername());
      
    	  List<GrantedAuthority> authorities = new ArrayList<>();
    	  authorities.add(new SimpleGrantedAuthority("ADMIN"));
    	 
    	  UserDetails admin = new org.springframework.security.core
              .userdetails.User(username, Admin.getPassword()
              , true, true, true, true, 
              authorities);
          return admin;
      }else {
    	  throw new UsernameNotFoundException("User not authorized.");
      }
    }
    
}
