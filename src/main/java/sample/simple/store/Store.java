package sample.simple.store;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sample.data.jpa.domain.*;
import sample.data.jpa.service.ArticleDao;
import sample.data.jpa.service.PanierDao;
import sample.simple.bank.IBank;
import sample.simple.provider.IProvider;

@Component
public class Store implements IFastLane, IJustHaveALook, ILane, IStore{

	@Autowired
	IBank ibank;
	@Autowired
	IProvider provider;
	
	
	@Autowired
	ArticleDao articledao;
	
	@Autowired
	PanierDao panierdao;
	
	
	
	
	@Override
	public void addItemToCart(String referenceArticle, int quantite) {
		if(isAvailable(referenceArticle,quantite)){
			Article article = articledao.findByReferenceArticle(referenceArticle);
			Panier panier = new Panier(referenceArticle, quantite,article.getPrix());
			panierdao.save(panier);
		}
		else {System.out.println("article non reconnu");}
	}

	@Override
	public void pay(String numCompteBancaire,String adresse) {
		float prixTotal =0;
		for(Panier a: panierdao.findAll()) {
			prixTotal += a.getPrix()*a.getQuantite();
			
			Article article = articledao.findByReferenceArticle(a.getReferenceArticle());
			article.setQuantite(article.getQuantite()-a.getQuantite());//maj quantite
			articledao.save(article);
			if(article.getQuantite()==0) {//reaprovisionne si plus de qt
				provider.order(article.getReferenceArticle(), 1);
			}
		}
		ibank.transfert(numCompteBancaire, prixTotal, adresse);
		
		
	}

	@Override
	public float getPrice(String referenceArticle) {
		if(articledao.existsByReferenceArticle(referenceArticle)) {
		Article article = articledao.findByReferenceArticle(referenceArticle);
			return article.getPrix();}
		else { return 0;}
	}

	@Override
	public boolean isAvailable(String referenceArticle, int quantite) {
		if(articledao.existsByReferenceArticle(referenceArticle)) {
			Article article = articledao.findByReferenceArticle(referenceArticle);
			if(article.getQuantite()>=quantite) {
				System.out.println("article disponible");
				return true;
			}
		}
		return false;
	}

	@Override
	public void oneShotOrder(String referenceArticle, int quantite, String numCompteBancaire, String adresse) {
		if(articledao.existsByReferenceArticle(referenceArticle)) {
			Article article = articledao.findByReferenceArticle(referenceArticle);
			float prixTotal=article.getPrix() * quantite;
			ibank.transfert(numCompteBancaire,prixTotal, adresse);
				
				article.setQuantite(article.getQuantite()-quantite);//met à jour le stock
				articledao.save(article);
				
				if(article.getQuantite()==0) {//reaprovisionne si plus de qt
					provider.order(article.getReferenceArticle(), 1);
				}
		}
		else {System.out.println("Article non reconnu");
		}
		
	}
	

	
}
