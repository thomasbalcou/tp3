package sample.data.jpa.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import sample.data.jpa.domain.Article;
import sample.data.jpa.service.ArticleDao;

import java.security.Principal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;




@Controller
public class ViewController {
	
	@Autowired
	ArticleDao articledao;
	
	@GetMapping("/index")
	@PreAuthorize("hasRole('USER')")
	public ModelAndView index(Principal principal) {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("user", principal);
		List<Article> articles = articledao.findAll();
		modelAndView.addObject("articles", articles);
		return modelAndView;
	}
	
	@GetMapping("/")
	public ModelAndView main() {
		ModelAndView modelAndView = new ModelAndView("indexmain");
		return modelAndView;
	}
	
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public ModelAndView admin(Principal principal) {
		ModelAndView modelAndView = new ModelAndView("admin");
		modelAndView.addObject("user", principal);
		return modelAndView;
	}
	
	    /**
     * Makes SSO Logout.
     *
     * @param request the request
     * @return redirect to logout page
     * @throws ServletException if tomcat session logout throws exception
     */
    @GetMapping(path = "/logout")
    public ModelAndView logout(HttpServletRequest request, Principal principal) throws ServletException {
		ModelAndView modelAndView = new ModelAndView("logout");
  	   request.logout();;
        return modelAndView;
    }
	
}
