package sample.simple.provider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sample.data.jpa.domain.Article;
import sample.data.jpa.service.ArticleDao;

@Component
public class Provider implements IProvider{
	
	@Autowired
	ArticleDao articledao;
	
	
	

	@Override
	public float getPrice(String refArticle) {
	ArrayList<Article> articles = new ArrayList<Article>();//liste des articles du provider non présente dans la db du site
	articles.add(new Article("1A",2,30));
	articles.add(new Article("2A",2,50));
	articles.add(new Article("4A",2,30));
		for(Article a: articles) {
			if(a.getReferenceArticle().equals(refArticle)) {
			return a.getPrix();}
		}
		System.out.println("Article non disponible");
		return 0;
		
	}

	@Override
	public void order(String refArticle, int quantite) {
		ArrayList<Article> articles = new ArrayList<Article>();//liste des articles du provider non présente dans la db du site
		articles.add(new Article("1A",2,30));
		articles.add(new Article("2A",2,50));
		articles.add(new Article("4A",2,30));
		for(Article a: articles) {
			if(a.getReferenceArticle().equals(refArticle)) {
				if(a.getQuantite()>=quantite) {
					Article article = a;
					article.setQuantite(quantite);
					articledao.save(article);
					a.setQuantite(a.getQuantite()-quantite);
				}
				else {System.out.println("quantité du fournisseur insuffisante");
			}
		}else {System.out.println("Article non disponible");}
			}
			
	}

}
