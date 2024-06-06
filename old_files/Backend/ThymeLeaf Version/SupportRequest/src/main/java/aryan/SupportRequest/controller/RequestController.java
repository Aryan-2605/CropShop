package aryan.SupportRequest.controller;

import aryan.SupportRequest.EmailService.EmailService;
import aryan.SupportRequest.exceptions.exception.*;
import aryan.SupportRequest.model.request;
import aryan.SupportRequest.validation.*;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import aryan.SupportRequest.repositories.RequestRepository;

@Controller
public class RequestController {
	
	@Autowired
	RequestRepository requestRepository;
	
	@Autowired
	EmailService emailservice;
	
	@Autowired
	validation validation;
	
	String tempEmail;
	@GetMapping("/support")
	public String getSupportPage(@ModelAttribute("request") request Request) {
		return "support";
	}
	
	@PostMapping("/support")
	public String saveRequest(@ModelAttribute("request") request Request, Model model, RedirectAttributes redirectAttributes) {
		try {
		validation.validateFirstName(Request.getFirstName());
		validation.validateLastName(Request.getLastName());
		validation.validateEmail(Request.getEmail());
		validation.validateTitle(Request.getTitle());
		validation.validateQuery(Request.getQuery());
		requestRepository.save(Request);
		//Email Sender
        String toEmail = Request.getEmail();
        String subject = "[Support] " + Request.getTitle();
        String body = "Dear " + Request.getFirstName() + ",\r\rThank you for for contacting CropShop support."
        		+ " We are always here to help you and ensure you have a positive experience using our service."
        		+ "\r\rYour query has been sent to one of our support rep, who will reach out to you within 3 - 5 business days."
        		+ " In the mean time, you can attach any proof you want our staff to see in this email."
        		+ "\r\rYour Query: \r\r \""+Request.getQuery() +"\""
        		+ "\r\rWe look forward to improving your experience with CropShop."
        		+ "\r\rKind regards,\rSupport Team\rCropShop" ;
        emailservice.sendEmail(toEmail, subject, body);
        
        redirectAttributes.addFlashAttribute("message", "We have received your message. Please check your Email.");
		
		return "redirect:/support";
		}catch(FirstNameTooLong e) {
			model.addAttribute("fErrorOB", e.getMessage());
		}catch(LastNameTooLong e) {
			model.addAttribute("lErrorOB", e.getMessage());
		}catch(EmailTooLong e) {
			model.addAttribute("eErrorOB", e.getMessage());
		}catch(TitleTooLong e) {
			model.addAttribute("tErrorOB", e.getMessage());
		}catch(QueryTooLong e) {
			model.addAttribute("qErrorOB", e.getMessage());
		}
		return "support";
	}
	
	@GetMapping("/requests")
	public String viewRequests(Model model) {
		List<request> allRequest = requestRepository.findAll();
		model.addAttribute("requests", allRequest);
		return "request";
	}
	
	
	//Deleted the requests still working on it {Completed}
	@Transactional
    @PostMapping("/delete")
    public String deleteRequest(@RequestParam Long id) {
		
		//Logging values
	    Optional<request> optionalRequest = requestRepository.findById(id);
	    request Request = optionalRequest.get();

    	
    	//Email Reply Sender
    	String toEmail = Request.getEmail();
    	String subject = ("[Support] " + Request.getTitle());
    	String body = "Dear " + Request.getFirstName() + ",\r\r"
    			+ "Thank you for chosing CropShop. I hope our support team have taken care of any issues you had.\r\r"
    			+ "We kindly ask that you leave any feedback you had about your support.\r\r"
    			+ "Kind regards\rSupport Team\rCropShop";
    	emailservice.sendEmail(toEmail, subject, body);
    			
    	
    	
        requestRepository.deleteById(id);
        return "redirect:/requests";
      //  return request.get
    }
    
 /*   @RequestMapping("/errors")
    public String error() {
    	
    }
   */
   
		
	
}
