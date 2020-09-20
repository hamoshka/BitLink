package shop.voda.shortlinks;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import shop.voda.common.ClientInfo;
import shop.voda.shortlinks.click.ClickService;
import shop.voda.shortlinks.entities.Click;
import shop.voda.shortlinks.entities.Url;
import shop.voda.shortlinks.url.URLService;

@Controller
public class AppController {

	
	@Autowired
	private URLService urlService;

	@Autowired
	private ClickService clickService;

	  @RequestMapping("/redirector/{shortUrl}") 
	  public String editURLPage(@PathVariable(name = "shortUrl") String shortUrl,
			  HttpServletRequest request) { 
		  Url url = urlService.findByShortUrl(shortUrl);
		  if (url.getActive()) {

			  url.addClickCount();
			  ClientInfo clientInfo=new ClientInfo(request);
			  Click click = new Click(clientInfo.getClientBrowser(),clientInfo.getClientOS(),clientInfo.getReferer(),url);
			  click.setDateClicked(new Date());
			  clickService.save(click);
			  
			  if (url.getDynamicParams()) {

				  int ch = url.getLongUrl().indexOf("?",10);
				  if (ch==-1) {
					 String queryString = request.getQueryString();
					 if (StringUtils.isEmpty(queryString)) {
						 return "redirect:"+url.getLongUrl();
					} else {
						return "redirect:"+url.getLongUrl().concat("?").concat(request.getQueryString());
					}
				}else {
					String path = url.getLongUrl().substring(0,url.getLongUrl().indexOf("?",10));
					return "redirect:"+path.concat("?").concat(request.getQueryString());
				}
			  
			} else {
				return "redirect:"+url.getLongUrl(); 
			}
		  }else {
			  int ch = url.getLongUrl().indexOf("/",10);
			  if (ch==-1) {
				return "redirect:"+url.getLongUrl();
			}else {
				return "redirect:"+url.getLongUrl().substring(0,url.getLongUrl().indexOf("/",10));
			}
			  
		  }
			
	  }
	
	 
}
