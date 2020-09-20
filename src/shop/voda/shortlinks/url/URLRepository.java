package shop.voda.shortlinks.url;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.voda.shortlinks.entities.Url;

public interface URLRepository extends JpaRepository<Url, Long> {

	Url findByShortUrl(String shortUrl);
}	
