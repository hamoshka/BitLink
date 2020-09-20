package shop.voda.shortlinks.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.voda.shortlinks.entities.Domain;

public interface DomainRepository extends JpaRepository<Domain, Long> {

	Domain findByName(String name);

}
