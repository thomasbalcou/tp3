package sample.data.jpa.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.data.jpa.domain.Panier;
import sample.data.jpa.service.PanierDao;



@Controller
@RequestMapping("/panier")
public class PanierController {
	
	@Autowired
	PanierDao panierdao;
	

  @RequestMapping("/create")
  @ResponseBody
  public String create(String reference, int quantite, float prix) {
	  String articleRef;
    try {
      Panier panier = new Panier(reference, quantite, prix);
      panierdao.save(panier);
      articleRef = String.valueOf(panier.getReferenceArticle());
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
      Panier panier = new Panier(referenceClient);
      panierdao.delete(panier);
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
      Panier panier = panierdao.findByReferenceArticle(refArticle);
      articleRef = String.valueOf(panier.getReferenceArticle());
    }
    catch (Exception ex) {
      return "Article not found";
    }
    return "The Article ref is: " + articleRef;
  }
  

  @RequestMapping("/update")
  @ResponseBody
  public String updatePanier(String reference, int quantite, float prix) {
    try {
      Panier panier = panierdao.findByReferenceArticle(reference);
      panier.setQuantite(quantite);
      panier.setPrix(prix);
      panierdao.save(panier);
    }
    catch (Exception ex) {
      return "Error updating the article: " + ex.toString();
    }
    return "article succesfully updated!";
  }
  
  @RequestMapping("/all")
  @ResponseBody
  public String getAllPaniere() {
	  String articleRef = "";
	  try {
	      List<Panier> panier= panierdao.findAll();
	      for(Panier a: panier) {
	      articleRef += String.valueOf(a.getReferenceArticle());
	    }}
	   catch (Exception ex) {
	        return "Article not found";
	   }
	   return "Reference des articles:" + articleRef;
  }
}
