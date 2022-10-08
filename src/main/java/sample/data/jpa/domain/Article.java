package sample.data.jpa.domain;
import javax.persistence.*;

@Entity
@Table(name = "articles")
public class Article {
	
	private String referenceArticle;
	private int quantite;
	private float prix;
	
	
	
	public Article() {
	}
	
	
	public Article(String referenceArticle) {
		super();
		this.referenceArticle = referenceArticle;
	}


	public Article(String referenceArticle, int quantite, float prix) {
		super();
		this.referenceArticle = referenceArticle;
		this.quantite = quantite;
		this.prix = prix;
	}
	@Id
	public String getReferenceArticle() {
		return referenceArticle;
	}
	public void setReferenceArticle(String referenceArticle) {
		this.referenceArticle = referenceArticle;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}

}
