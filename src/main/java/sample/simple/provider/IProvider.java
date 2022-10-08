package sample.simple.provider;

public interface IProvider {
	
	public float getPrice(String refArticle);
	public void order(String refArticle, int quantite);

}
