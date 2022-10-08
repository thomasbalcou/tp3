package sample.data.jpa.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.data.jpa.domain.Article;
import sample.data.jpa.service.ArticleDao;



@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	ArticleDao articledao;
	

  @RequestMapping("/create")
  @ResponseBody
  public String create(String reference, int quantite, float prix) {
	  String articleRef;
    try {
      Article article = new Article(reference, quantite, prix);
      articledao.save(article);
      articleRef = String.valueOf(article.getReferenceArticle());
    }
    catch (Exception ex) {
      return "Error creating the article: " + ex.toString();
    }
    return "article succesfully created with reference = " + articleRef;
  }
  

  @RequestMapping("/delete")
  @ResponseBody
  public String delete(String referenceClient) {
    try {
      Article article = new Article(referenceClient);
      articledao.delete(article);
    }
    catch (Exception ex) {
      return "Error deleting the article:" + ex.toString();
    }
    return "article succesfully deleted!";
  }
  

  @RequestMapping("/get-by-ref/{refArticle}")
  @ResponseBody
  public String getByRef(@PathVariable("refArticle") String refArticle) {
    String articleRef = "";
    try {
      Article article = articledao.findByReferenceArticle(refArticle);
      articleRef = String.valueOf(article.getReferenceArticle());
    }
    catch (Exception ex) {
      return "Article not found";
    }
    return "The Article ref is: " + articleRef;
  }
  

  @RequestMapping("/update")
  @ResponseBody
  public String updateArticle(String reference, int quantite, float prix) {
    try {
      Article article = articledao.findByReferenceArticle(reference);
      article.setQuantite(quantite);
      article.setPrix(prix);
      articledao.save(article);
      System.out.println(article.getReferenceArticle()+"qt:"+article.getQuantite()+"prix:"+article.getPrix());
    }
    catch (Exception ex) {
      return "Error updating the article: " + ex.toString();
    }
    	
    return "article succesfully updated!";
  }
  
  @RequestMapping("/all")
  @ResponseBody
  public String getAllArticle() {
	  String articleRef = "";
	  try {
	      List<Article> articles= articledao.findAll();
	      for(Article a: articles) {
	      articleRef += String.valueOf(a.getReferenceArticle());
	    }}
	   catch (Exception ex) {
	        return "Article not found";
	   }
	   return "Reference des articles:" + articleRef;
  }
}