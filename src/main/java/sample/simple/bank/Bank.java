package sample.simple.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sample.data.jpa.service.PanierDao;

@Component
public class Bank implements IBank{
	
	@Autowired
	PanierDao panierdao;

	@Override
	public boolean transfert(String numCompteBancaire,  float prixTotal, String adresse) {
		System.out.println("#####################Transaction de " + prixTotal + "effectué depuis le compte bancaire " + numCompteBancaire +"livraison à l'adresse: " +adresse);
		//transfert ok
		panierdao.deleteAll();
		return true;
	}

}
