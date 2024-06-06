package CropShop.Support.controllers;

import CropShop.Support.model.request;
import CropShop.Support.repositories.archivedRequestRepository;
import CropShop.Support.repositories.requestRepository;
import CropShop.Support.services.emailService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import CropShop.Support.model.archivedRequest;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController //@Controller + @Responce Combined
public class supportController {
    @Autowired
    requestRepository RequestRepository;
    @Autowired
    archivedRequestRepository ArchivedRequestRepository;

    @Autowired
    emailService Emailservice;
    @Value("${app.name}")
    private String appName;
    @Value("${app.version}")
    private String appVersion;
    @Value("${app.author}")
    private String appAuthor;

    @GetMapping("/version")
    public String Version(){
        return "Project Name: " + appName + "\rVersion: " +appVersion + "\rAuthor: " + appName;
    }


    @GetMapping("/requests")
    public List<request> getRequests(){
        List<request> allRequest = RequestRepository.findAll();

        //Postman Tested (PASSED)

        return allRequest;
    }

    //localhost:8080/requests/{id}
    @GetMapping("/requests/{id}")
    public String getRequest(@PathVariable Long id){
        return "Fetching the Request Details for id " + id;
    }
    @Transactional
    @DeleteMapping("/requests")
    public String  deleteRequest(@RequestParam Long id, archivedRequest ArchivedRequest){
        //Logging values±
        Optional<request> optionalRequest = RequestRepository.findById(id);
        request Request = optionalRequest.get();

        ArchivedRequest.setId(Request.getId());
        ArchivedRequest.setFirstName(Request.getFirstName());
        ArchivedRequest.setLastName(Request.getLastName());
        ArchivedRequest.setEmail(Request.getEmail());
        ArchivedRequest.setTitle(Request.getTitle());
        ArchivedRequest.setQuery(Request.getQuery());

        ArchivedRequestRepository.save(ArchivedRequest);

        String toEmail = Request.getEmail();
        String subject = ("[Support] " + Request.getTitle());
        String body = "Dear " + Request.getFirstName() + ",\r\r"
                + "Thank you for choosing CropShop. I hope our support team have taken care of any issues you had.\r\r"
                + "We kindly ask that you leave any feedback you had about your support.\r\r"
                + "Kind regards\rSupport Team\rCropShop";
        Emailservice.sendEmail(toEmail, subject, body);



        RequestRepository.deleteById(id);
        return "redirect:/requests";
    }
    @PostMapping("/requests")
    public String saveRequest(@RequestBody request Request){
        RequestRepository.save(Request);
        //Postman Tested (PASSED)
        //Delete Return in Production.

        String toEmail = Request.getEmail();
        String subject = "[Support] " + Request.getTitle();
        String body = "Dear " + Request.getFirstName() + ",\r\rThank you for for contacting CropShop support."
                + " We are always here to help you and ensure you have a positive experience using our service."
                + "\r\rYour query has been sent to one of our support rep, who will reach out to you within 3 - 5 business days."
                + " In the mean time, you can attach any proof you want our staff to see in this email."
                + "\r\rYour Query: \r\r \""+Request.getQuery() +"\""
                + "\r\rWe look forward to improving your experience with CropShop."
                + "\r\rKind regards,\rSupport Team\rCropShop" ;
        Emailservice.sendEmail(toEmail, subject, body);

        return "Request saved to DB and Email Sent!\r\r Email: \r\r " + Request + "\rTo: " + toEmail + "\rSubject " + subject
                + "\rBody:\r" + body;
    }
    @PutMapping("requests/{id}")
    public request updateRequest(@PathVariable Long id,@RequestBody request Request){
        System.out.println("Updated Request");
        return Request;
    }
}
