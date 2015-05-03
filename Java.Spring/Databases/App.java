package main;

import java.util.List;

import model.Offer;
import model.dao.OfferDAO;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("main/beans/beans.xml");
		
		OfferDAO offerDAO = (OfferDAO)context.getBean("offerDAO");
		
		List<Offer> list = offerDAO.getOffers();
		
		for(Offer offer : list) {
			
			System.out.println(offer);
		}
		
		((ClassPathXmlApplicationContext)context).close();
	}

}
