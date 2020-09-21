
package shop.voda.shortlinks.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(indexes = {
		@Index(name ="browser_idx",unique = false,columnList = "browser" ),
		@Index(name ="os_idx",unique = false,columnList = "os" ),
		@Index(name ="referrer_idx",unique = false,columnList = "referrer" )
		})
public class Click implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Date dateClicked;

	@Column
	private String browser;

	@Column
	private String os;
	
	@Column
	private String referrer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Url url;

	public Click() {

	}


	public Click(String browser, String os, String referrer, Url url) {
		super();
		this.browser = browser;
		this.os = os;
		this.referrer = referrer;
		this.url = url;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-MM-yyyy",timezone="CET")
	public Date getDateClicked() {
		return dateClicked;
	}


	public void setDateClicked(Date dateClicked) {
		this.dateClicked = dateClicked;
	}

	public Url getUrl() {
		return url;
	}


	public void setUrl(Url url) {
		this.url = url;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	

}
