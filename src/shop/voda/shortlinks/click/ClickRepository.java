package shop.voda.shortlinks.click;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import shop.voda.common.IClickChart;
import shop.voda.shortlinks.entities.Click;

public interface ClickRepository extends JpaRepository<Click, Long> {
	/*
	 * @Query(value = "select DATE_FORMAT(date_clicked, '%d-%m-%Y') as day , " +
	 * " count(id) clicks " + " from click  clk " +
	 * " where date_clicked >= last_day(now()) + interval 1 day - interval 1 month "
	 * + " and url_id=?  " + " group by day " + " ORDER BY date_clicked asc " ,
	 * nativeQuery = true)
	 */

	@Query(value = "select to_char(TO_DATE(trunc(date_clicked),'dd/mm/yy'), 'DD-MM-YYYY')  as day " + 
			" , count(id) clicks  " + 
			" from click  clk  " + 
			" where date_clicked >= " + 
			" last_day(systimestamp) + interval '1' day - interval '1' month  " + 
			" and url_id=? " + 
			" group by to_char(TO_DATE(trunc(date_clicked),'dd/mm/yy'), 'DD-MM-YYYY') " + 
			" ORDER BY to_char(TO_DATE(trunc(date_clicked),'dd/mm/yy'), 'DD-MM-YYYY') asc" , nativeQuery = true)
	List<IClickChart> findClicksCountByUrl(Long urlId);
	
	 
	/*
	 * @Query(value = "select browser as day , " + " count(id) clicks " +
	 * " from click  clk " +
	 * "  where date_clicked >= last_day(now()) + interval 1 day - interval 1 month "
	 * + "  group by day " + " ORDER BY clicks desc LIMIT 5 " , nativeQuery = true)
	 */
	@Query(value = "select * from (select browser as day ,  count(id) clicks  " + 
			" from click  clk " + 
			" where date_clicked >= last_day(systimestamp) + interval '1' day - interval '1' month  " +
			" and url_id=? " +
			" group by browser " + 
			" ORDER BY clicks desc) where rownum <= 5 "  
			 , nativeQuery = true)
	List<IClickChart> findTopBrowsersByUrl(Long urlId);
	
	
	
	
	
	/*
	 * @Query(value = "select " + " case " +
	 * " when referrer is null then 'Native'  " + " else referrer " + " end " +
	 * " as day , " + " count(id) clicks " + " from click  clk " +
	 * "  where date_clicked >= last_day(now()) + interval 1 day - interval 1 month "
	 * + "  group by day " + " ORDER BY clicks desc LIMIT 5 " , nativeQuery = true)
	 */

	

	@Query(value = "select * from (select " + 
			" case " + 
			" when referrer is null then 'Native' " + 
			" else referrer " + 
			" end " + 
			" as day , " + 
			" count(id) clicks " + 
			" from click  clk " + 
			" where date_clicked >= last_day(systimestamp) + interval '1' day - interval '1' month " + 
			" and url_id=? " +
			" group by referrer " + 
			" ORDER BY clicks desc) where rownum <= 5 "
			 , nativeQuery = true)
	List<IClickChart> findTopReferresByUrl(Long urlId);
	
}
