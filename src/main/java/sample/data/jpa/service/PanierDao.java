package sample.data.jpa.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import sample.data.jpa.domain.Panier;

// Imports ...

@Transactional
public interface PanierDao extends JpaRepository<Panier, Long> {

  /**
   * This method will find an User instance in the database by its email.
   * Note that this method is not implemented and its working code will be
   * automagically generated from its signature by Spring Data JPA.
 * @param <S>
 * @return 
 * @return 
 * @return 
   */
	
	public Panier findByReferenceArticle(String referenceArticle);
	public Panier save(Panier panier);
	//public Article update(Article article);
	public List<Panier> findAll();
	void delete(Panier panier);
	void deleteAll();
    boolean existsByReferenceArticle(String referenceArticle);

}