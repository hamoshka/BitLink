package shop.voda.shortlinks.domain;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.voda.shortlinks.entities.Domain;
import shop.voda.shortlinks.entities.User;
import shop.voda.shortlinks.user.UserRepository;

@Service
@Transactional
public class DomainService {

	@Autowired
	private DomainRepository domainRepository;
	@Autowired
	private UserRepository userRepository;
	
	
	public List<Domain> listAll() {
		return domainRepository.findAll();
	}
	
	public void save(Domain domain) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		  User user = userRepository.findByEmail(auth.getName());
		domain.setCreatedBy(user);
		domain.setCreationDate(new Date());
		domainRepository.save(domain);
	}
	
	public Domain get(long id) {
		return domainRepository.findById(id).get();
	}
	
	public void delete(long id) {
		domainRepository.deleteById(id);
	}

	public boolean checkDublicates(String name) {

		Domain domain = domainRepository.findByName(name);
		
		return domain!=null?true:false;
	}
}
