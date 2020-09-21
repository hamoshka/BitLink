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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(indexes = {@Index(name ="shortUrl_idx",unique = true,columnList = "shortUrl" )})
public class Url implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String longUrl;
	
	@Transient
	private String customUrl;

	@Column(unique = true,nullable = false)
	private String shortUrl;

	@Column
	private Date expiryDate;

	@Column
	private long clicksCount;
	
	@Column
	private boolean active=true;
	
	@Column
	private boolean dynamicParams;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Domain domain;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User createdBy;
	
	@Column
	private Date creationDate;
	

	
	public Url() {
		
	}
	

	public Url(String longUrl, String shortUrl, Date dateCreated) {
		super();
		this.longUrl = longUrl;
		this.shortUrl = shortUrl;
		this.creationDate = dateCreated;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public String getShortUrl() {
		return  shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-MM-yyyy",timezone="CET")







	public long getClicksCount() {
		return clicksCount;
	}


	public void setClicksCount(long clicksCount) {
		this.clicksCount = clicksCount;
	}


	public User getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}


	public Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	public Date getExpiryDate() {
		return expiryDate;
	}


	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	



	public boolean getActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public boolean getDynamicParams() {
		return dynamicParams;
	}


	public void setDynamicParams(boolean dynamicParams) {
		this.dynamicParams = dynamicParams;
	}


	public Domain getDomain() {
		return domain;
	}


	public void setDomain(Domain domain) {
		this.domain = domain;
	}


	public void addClickCount() {
clicksCount+=1;		
	}




	public String getCustomUrl() {
		return customUrl;
	}


	public void setCustomUrl(String customUrl) {
		this.customUrl = customUrl;
	}


	

}
