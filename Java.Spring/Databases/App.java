package main;

import java.util.List;

import model.Offer;
import model.dao.OfferDAO;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

public class App {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("main/beans/beans.xml");
		
		OfferDAO offerDAO = (OfferDAO)context.getBean("offerDAO");
		
		try {
			
			List<Offer> list = offerDAO.getOffers();
			
			for(Offer offer : list) {
				
				System.out.println(offer);
			}
			
			System.out.println(offerDAO.getOffer(2));
			
		} catch (DataAccessException e) {
			
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
		}
		
		((ClassPathXmlApplicationContext)context).close();
	}

}
