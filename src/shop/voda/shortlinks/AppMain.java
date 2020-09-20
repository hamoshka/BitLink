package shop.voda.shortlinks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableAutoConfiguration
public class AppMain extends SpringBootServletInitializer{
	 
	@Override
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){

	    return builder.sources(AppMain.class);

	  }
	
	public static void main(String[] args) {
		SpringApplication.run(AppMain.class, args);
	}
}
