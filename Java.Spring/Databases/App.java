package main;

import java.util.List;

import model.Offer;
import model.dao.OfferDAO;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public class App {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("main/beans/beans.xml");
		
		OfferDAO offerDAO = (OfferDAO)context.getBean("offerDAO");
		
		try {
			
			//offerDAO.delete(7);
			//offerDAO.create(new Offer("Seth", "unknown", "ohai!"));
			
			List<Offer> list = offerDAO.getOffers();
			
			for(Offer offer : list) {
				
				System.out.println(offer);
			}
			
			System.out.println(offerDAO.getOffer(2));
			
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("name", "liam");
			params.addValue("id", 3);
			
			List<Offer> paramList = offerDAO.getOffersWithParams(params, 
					"select * from offers where id = :id and name = :name");
			
			for(Offer offer : paramList) {
				
				System.out.println(offer);
			}
			
		} catch (DataAccessException e) {
			
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
		}
		
		((ClassPathXmlApplicationContext)context).close();
	}

}
