package shop.voda.shortlinks.url;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import shop.voda.common.ClickChartDTO;
import shop.voda.shortlinks.click.ClickService;
import shop.voda.shortlinks.domain.DomainService;
import shop.voda.shortlinks.entities.Domain;
import shop.voda.shortlinks.entities.Url;

@Controller
@RequestMapping("/url")
public class URLController {

	@Autowired
	private DomainService domainService;
	
	@Autowired
	private URLService urlService;
	
	@Autowired
	private ClickService clickService;

	@RequestMapping("/list")
	public String listURLs(Model model) {
		List<Url> listURLs = urlService.listAll();
		model.addAttribute("listURLs", listURLs);
		
		return "url/url_list";
	}
	
	  @RequestMapping("/new") 
	  public String newURLPage(Model model) {
		  Url url = new Url();
	  model.addAttribute("url", url);
	  List<Domain> domains = domainService.listAll();
	  model.addAttribute("domains", domains);
	  return "url/url_new";
	  }
	  
	  @RequestMapping(value = "/save", method = RequestMethod.POST) 
	  public String saveUrl(@ModelAttribute("url") Url url) {
		  if (!StringUtils.isEmpty(url.getCustomUrl())) {
			  url.setShortUrl(url.getCustomUrl().trim());
		}else {
			if (url.getId()==null) {
				url.setShortUrl(urlService.generateShortUrl());
			}
		}
		  urlService.save(url);
	  return "redirect:/url/list"; 
	  }
	  
	  @RequestMapping("/edit/{id}") 
	  public ModelAndView editURLPage(@PathVariable(name = "id") Long id) { 
		  ModelAndView mav = new ModelAndView("url/url_edit");
		  Url url = urlService.get(id);
		  List<Domain> domains = domainService.listAll();
		  mav.addObject("domains", domains);
	  mav.addObject("url", url);
	  return mav; 
	  }
	  
	 
	  @RequestMapping("/delete/{id}") 
	  public String deleteURL(@PathVariable(name = "id") Long id) { 
		  urlService.delete(id);
		  return "redirect:/url/list"; 
		  }
	  

	  
	  @RequestMapping("/report/{id}") 
	  public ModelAndView urlReportPage(@PathVariable(name = "id") Long id) { 
		  ModelAndView mav = new ModelAndView("url/url_report");
		 Url url= urlService.findById(id);
		 
		  mav.addObject("fullShortURL", url.getDomain().getName().concat("/").concat(url.getShortUrl()));
		  mav.addObject("clicksCount",url.getClicksCount());
		  ClickChartDTO clicksData = clickService.findClicksCountByUrl(id);
		  mav.addObject("clickLabels",new Gson().toJson(clicksData.getDays()) );
		  mav.addObject("clickData",new Gson().toJson(clicksData.getClicks()));
		  
		  ClickChartDTO browserData = clickService.findTopBrowsersByUrl(id);
		  mav.addObject("browsers",browserData.getDays()) ;
		  mav.addObject("brwsrLabels",new Gson().toJson(browserData.getDays()) );
		  mav.addObject("brwsrData",new Gson().toJson(browserData.getClicks()));
		  
		  

		  ClickChartDTO referrerData = clickService.findTopReferresByUrl(id);
		  mav.addObject("referrerLabels",new Gson().toJson(referrerData.getDays()) );
		  mav.addObject("referrerData",new Gson().toJson(referrerData.getClicks()));
		  
		  return mav; 
		  }
	  
	  
	  
	 
}
