package springify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springify.model.Offer;
import springify.model.dao.OfferDAO;

@Service("offerService")
public class OfferService {

	private OfferDAO offerDAO;

	@Autowired
	public void setOfferDAO(OfferDAO offerDAO) {
		this.offerDAO = offerDAO;
	}

	public List<Offer> getCurrent() {
		return offerDAO.getOffers();
	}
	
	public void create(Offer offer) {
		
		this.offerDAO.create(offer);
	}
}
