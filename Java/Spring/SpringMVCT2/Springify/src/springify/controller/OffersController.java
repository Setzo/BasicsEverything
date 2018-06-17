package springify.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springify.model.Offer;
import springify.service.OfferService;

@Controller( "offersController" )
public class OffersController {

    private OfferService offerService;

    @Autowired
    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }

    @RequestMapping( value = "/test", method = RequestMethod.GET )
    public String showTest(Model model, @RequestParam( "text" ) String text) {

        System.out.println(text);
        return "home";
    }

    @RequestMapping( "/offers" )
    public String showOffers(Model model) {

        List<Offer> offerList = offerService.getCurrent();

        model.addAttribute("offerList", offerList);
        model.addAttribute("title", "Database Content");
        model.addAttribute("text", "Database Content:");

        return "offers";
    }

    @RequestMapping( "/createoffer" )
    public String createOffer(Model model) {

        model.addAttribute("title", "Create Offer");
        model.addAttribute("offer", new Offer());

        return "createoffer";
    }

    @RequestMapping( value = "/docreate", method = RequestMethod.POST )
    public String doCreate(Model model, @Valid() Offer offer, BindingResult result) {

        if (result.hasErrors()) {

            model.addAttribute("title", "Create Offer");
            return "createoffer";
        }

        offerService.create(offer);

        model.addAttribute("title", "Offer Created");

        return "offercreated";
    }
}
