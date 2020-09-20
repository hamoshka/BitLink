package shop.voda.shortlinks.url;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.voda.shortlinks.entities.Url;
import shop.voda.shortlinks.entities.User;
import shop.voda.shortlinks.user.UserRepository;

@Service
@Transactional
public class URLService {

	@Autowired
	private URLRepository urlRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Url> listAll() {
		return urlRepository.findAll();
	}
	public Url findById(Long id) {
		Optional<Url> url = urlRepository.findById(id);
		return url.get();
	}
	
	public void save(Url url) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		  User user = userRepository.findByEmail(auth.getName());
		  url.setCreatedBy(user);
		  url.setCreationDate(new Date());
		urlRepository.save(url);
	}
	
	public Url get(long id) {
		return urlRepository.findById(id).get();
	}
	
	public void delete(long id) {
		urlRepository.deleteById(id);
	}
	
	 public Url findByShortUrl(String shortUrl) {
		  return urlRepository.findByShortUrl(shortUrl);
	 }
	
	
	
	
	public String generateShortUrl() {
		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvxyz" + "0123456789";

		// create StringBuffer size of AlphaNumericString 
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 10; i++) {

			// generate a random number between 
			// 0 to AlphaNumericString variable length 
			int index = (int) (alphaNumericString.length() * Math.random());

			// add Character one by one in end of sb 
			sb.append(alphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	
}
