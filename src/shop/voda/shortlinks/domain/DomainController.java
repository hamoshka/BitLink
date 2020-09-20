package shop.voda.shortlinks.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import shop.voda.shortlinks.entities.Domain;

@Controller
@RequestMapping("/domain")
public class DomainController {

	@Autowired
	private DomainService domainService;
	
	
	@RequestMapping("/list")
	public String listDomains(Model model) {
		List<Domain> listDomains = domainService.listAll();
		model.addAttribute("listDomains", listDomains);
		
		return "domain/domain_list";
	}
	
	  @RequestMapping("/new") 
	  public String newDomainPage(Model model) {
	  Domain domain = new Domain();
	  model.addAttribute("domain", domain);
	  
	  return "domain/domain_new";
	  }
	  
	  @RequestMapping(value = "/save", method = RequestMethod.POST) 
	  public String saveDomain( @ModelAttribute("domain") Domain domain) {
		boolean isExist =  domainService.checkDublicates(domain.getName());
		if (isExist) {
			return "redirect:/domain/new";
		} else {
			domainService.save(domain);
			return "redirect:/domain/list"; 
		}
	  }
	  
	  @RequestMapping("/edit/{id}") 
	  public ModelAndView editDomainPage(@PathVariable(name = "id") Long id) { 
		  ModelAndView mav = new ModelAndView("domain/domain_edit");
		  Domain domain = domainService.get(id);
	  mav.addObject("domain", domain);
	  return mav; 
	  }
	  
	 
	  @RequestMapping("/delete/{id}") 
	  public String deleteDomain(@PathVariable(name = "id") Long id) { 
		  domainService.delete(id);
		  return "redirect:/domain/list"; 
		  }
	 
}
