package springify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OffersController {

	/*@RequestMapping("/")
	public String showHome(HttpSession session) {
		
		// SESSION SCOPE
		session.setAttribute("title", "Title");
		session.setAttribute("text", "txt");
		
		return "home";
	}*/
	
	/*@RequestMapping("/")
	public ModelAndView showHome() {
		
		ModelAndView mv = new ModelAndView("home");
		
		Map<String, Object> model = mv.getModel();
		
		// REQUEST SCOPE
		model.put("title", "title");
		model.put("text", "text");
		
		return mv;
	}*/
	
	@RequestMapping("/")
	public String showHome(Model model) {

		model.addAttribute("title", "title");
		model.addAttribute("text", "sth");
		
		return "home";
	}
}
