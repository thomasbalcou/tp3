package sample.simple.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sample.data.jpa.domain.Article;
import sample.data.jpa.service.ArticleDao;
import sample.data.jpa.service.PanierDao;
import sample.simple.store.IFastLane;
import sample.simple.store.IJustHaveALook;
import sample.simple.store.ILane;

@Component
public class Client implements IRun, ICLient{
	//adresse
	
	@Autowired
	IFastLane ifast;
	@Autowired
	ILane ilane;
	@Autowired
	IJustHaveALook haveALook;
	@Autowired
	PanierDao panierdao;
	@Autowired
	ArticleDao articledao;


	public void run() {
		System.out.println("####################################lezgo");
		panierdao.deleteAll();
		String refArticle ="1A";
		int qt=1;
		String numbanc="112211";
		String adresse="2 rue dubois";
		//scenario 1
		haveALook.isAvailable(refArticle, qt);//client fournit ref article, quantité, magasin verifie la disponibilité
		//client commande son article, le magasin fait le transfert, met à jour le stock,reaprovisionne si plus d'article
		ifast.oneShotOrder(refArticle, qt, numbanc, adresse);
		//scenario 2
		//ajout de 3 items dans le panier
		ilane.addItemToCart("1A", 1);
		ilane.addItemToCart("2A", 1);
		ilane.addItemToCart("3E", 2);
		//paiement
		ilane.pay(numbanc, adresse);
	}

}
