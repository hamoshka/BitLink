package shop.voda.shortlinks.user;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.voda.shortlinks.entities.Role;
import shop.voda.shortlinks.entities.User;
import shop.voda.shortlinks.role.RoleRespository;

@Service
@Transactional
public class UserService {
	@Autowired
	 private UserRepository userRepository;
	 
	 @Autowired
	 private RoleRespository roleRespository;
	 
	 @Autowired
	 private BCryptPasswordEncoder bCryptPasswordEncoder;

	 public User findUserByEmail(String email) {
	  return userRepository.findByEmail(email);
	 }

	 public void saveUser(User user) {
	  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	  user.setActive(true);
	  Role userRole = roleRespository.findByRole("ADMIN");
	  user.setRoles(new ArrayList<Role>(Arrays.asList(userRole)));
	  userRepository.save(user);
	 }
}
