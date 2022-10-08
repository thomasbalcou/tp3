package sample.simple.store;

public interface ILane {
	
	public void addItemToCart(String referenceArticle, int quantite);
	
	public void pay(String numCompteBancaire, String adresse);
}
