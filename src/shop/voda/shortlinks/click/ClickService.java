package shop.voda.shortlinks.click;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.voda.common.ClickChartDTO;
import shop.voda.common.IClickChart;
import shop.voda.shortlinks.entities.Click;

@Service
@Transactional
public class ClickService {

	@Autowired
	private ClickRepository clickRepository;
	
	public List<Click> listAll() {
		return clickRepository.findAll();
	}
	
	public void save(Click click) {
		clickRepository.save(click);
	}
	
	public Click get(long id) {
		return clickRepository.findById(id).get();
	}
	
	public void delete(long id) {
		clickRepository.deleteById(id);
	}
	
	
	public ClickChartDTO findClicksCountByUrl(Long urlId){
		List<IClickChart> data = clickRepository.findClicksCountByUrl(urlId);
		
		SortedSet<Integer>  days=new TreeSet<Integer>();
		ArrayList<Integer>  clicks=new ArrayList<Integer>();
		for (IClickChart clickChart : data) {
			days.add(Integer.valueOf(clickChart.getDay().split("-")[0]));
			clicks.add(Integer.valueOf(clickChart.getClicks()));
		}
		
		ClickChartDTO retVal = new ClickChartDTO(days,clicks);
		
		return retVal;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ClickChartDTO findTopBrowsersByUrl(Long urlId) {
		List<IClickChart> data = clickRepository.findTopBrowsersByUrl(urlId);
		ArrayList  browsers=new ArrayList<String>();
		ArrayList  clicks=new ArrayList<Integer>();
		for (IClickChart browser : data) {
			browsers.add(browser.getDay());
			clicks.add(Integer.valueOf(browser.getClicks()));
		}
		ClickChartDTO retVal = new ClickChartDTO(browsers,clicks);
		return retVal;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ClickChartDTO findTopReferresByUrl(Long urlId) {
		List<IClickChart> data = clickRepository.findTopReferresByUrl(urlId);
		ArrayList  referrers=new ArrayList<String>();
		ArrayList  clicks=new ArrayList<Integer>();
		for (IClickChart referrer : data) {
			referrers.add(referrer.getDay());
			clicks.add(Integer.valueOf(referrer.getClicks()));
		}
		ClickChartDTO retVal = new ClickChartDTO(referrers,clicks);
		return retVal;
	}
	
	
}
