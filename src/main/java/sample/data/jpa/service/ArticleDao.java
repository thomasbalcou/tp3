package sample.data.jpa.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import sample.data.jpa.domain.Article;

// Imports ...

@Transactional
public interface ArticleDao extends JpaRepository<Article, Long> {

  /**
   * This method will find an User instance in the database by its email.
   * Note that this method is not implemented and its working code will be
   * automagically generated from its signature by Spring Data JPA.
 * @param <S>
 * @return 
 * @return 
 * @return 
   */
	
	public Article findByReferenceArticle(String referenceArticle);
	public Article save(Article article);
	//public Article update(Article article);
	public List<Article> findAll();
	@Query("SELECT referenceArticle FROM Article")
	public List<String> findAllRef();
	void delete(Article article);
    boolean existsByReferenceArticle(String referenceArticle);

}