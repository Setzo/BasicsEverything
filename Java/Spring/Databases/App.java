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

        OfferDAO offerDAO = (OfferDAO) context.getBean("offerDAO");

        try {

			/*List<Offer> al = new ArrayList<Offer>();
			
			al.add(new Offer("k", "d", "a"));
			al.add(new Offer("a", "a", "a"));
			al.add(new Offer("c", "c", "c"));
			
			offerDAO.create(al);*/
			
			
			/*al.add(new Offer(8, "r", "r", "r"));
			al.add(new Offer(9, "e", "e", "e"));
			al.add(new Offer(10, "f", "f", "f"));
			
			offerDAO.update(al);*/

            //int[] iny = {11, 12, 13};

            //offerDAO.delete(iny);
			
			/*
			Offer off = offerDAO.getOffer(7);
			System.out.println(off);
			
			off.setEmail("email");
			off.setName("name");
			off.setText("text");
			
			offerDAO.update(off);
			*/

            //offerDAO.delete(7);
            //offerDAO.create(new Offer("Seth", "unknown", "ohai!"));

            List<Offer> list = offerDAO.getOffers();

            for (Offer offer : list) {

                System.out.println(offer);
            }

            System.out.println(offerDAO.getOffer(2));

            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("name", "liam");
            params.addValue("id", 3);

            List<Offer> paramList = offerDAO.getOffersWithParams(params,
                    "select * from offers where id = :id and name = :name");

            for (Offer offer : paramList) {

                System.out.println(offer);
            }

        } catch (DataAccessException e) {

            System.out.println(e.getMessage());
            System.out.println(e.getClass());
        }

        ((ClassPathXmlApplicationContext) context).close();
    }

}
