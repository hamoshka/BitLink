package shop.voda.common;

import java.util.Collection;

@SuppressWarnings({"rawtypes"})
public class ClickChartDTO {

	
	
	private Collection days;
	private Collection clicks;

	public ClickChartDTO(Collection days, Collection clicks) {
	this.days=days;
	this.clicks=clicks;
	}

	public Collection getDays() {
		return days;
	}

	public void setDays(Collection days) {
		this.days = days;
	}

	public Collection getClicks() {
		return clicks;
	}

	public void setClicks(Collection clicks) {
		this.clicks = clicks;
	}
		
	
}
