package com.csm.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csm.model.BirthDetails;
import com.csm.model.Role;
import com.csm.model.User;
import com.csm.repository.BirthRepository;
import com.csm.repository.UserRepository;
import com.csm.service.BirthService;
import com.csm.service.RoleService;
import com.csm.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;
    
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    BirthService birthService;
    
    
    @Autowired
    BirthRepository birthRepository;
    
    @GetMapping("csp/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute("user") User user) {
        // You should encrypt the password here before saving
    	System.out.println("request coming to signup controller");
    	try {
        Role userRole = roleService.findByName("citizen");
        user.setRole(userRole);
        userService.saveUser(user);
        System.out.println("Phone Number: " + user.getPhonenumber());
        System.out.println("Date of Birth: " + user.getDateOfBirth());

        return "redirect:/csp";
    	}catch(Exception e)
    	{e.printStackTrace();
    	return "errorPage";
    		}
    }

    @GetMapping("/csp") 
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "loginCustom";
    }

    @PostMapping("/csp/ctz/applybirthcertificate")
    public String login(@ModelAttribute("user") User user, HttpSession session, Model model) {
        User foundUser = userService.findByUsername(user.getUsername());

        if (foundUser!=null && foundUser.getUsername().equals(user.getUsername())&& foundUser.getPassword().equals(user.getPassword())) {
            session.setAttribute("user", foundUser);
            
            Role role=foundUser.getRole();
            System.out.println(role.getName());
            
            model.addAttribute("userRole",role.getName());
            
            if(role.getName().equalsIgnoreCase("citizen"))
            {
            return "dashboard"; // Redirect to dashboard or any other page
            }
            else if(role.getName().equalsIgnoreCase("authority"))
            {
            	return "AuthorityDashboard";
            }
        }
        return "redirect:/errorDashboard";
    }
    
    @GetMapping("/errorDashboard")
    public String error()
    {
    	return "errorDashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            return "dashboard";
        }
        return "redirect:/csp";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/csp";
    }
    
    
    @GetMapping("/checkUsername")
    public ResponseEntity<String> checkUsername(@RequestParam String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            return ResponseEntity.ok("Username already exists");
        }
        return ResponseEntity.ok("Username is available");
    }
    
    
    @GetMapping("/checkEmail")
    public ResponseEntity<String> checkEmail(@RequestParam String email) {
        User user = userService.findByEmail(email);
        if (user != null) {
            return ResponseEntity.ok("Email already exists");
        }
        return ResponseEntity.ok("Email is available");
    }
    
    

    @GetMapping("/checkphoneNumber")
    public ResponseEntity<String> checkphoneNumber(@RequestParam String phonenumber) {
        User user = userService.findByPhonenumber(phonenumber);
        if (user != null) {
        	System.out.println("inside controller");
            return ResponseEntity.ok("PhoneNumber already exists!");
        }
        return ResponseEntity.ok("PhoneNumber is available");
    }
    
    
    @PostMapping("/csp/ctz/applyBirthCertificate")
    public String submitApplicationDetails(@ModelAttribute("birthDetails") BirthDetails birthDetails) {
       // System.out.println(user);
        //userRepository.save(user);
        //libraryRepository.save(library);
    	birthRepository.save(birthDetails);
        return "application_submit_success";
    }
    
    
    @GetMapping("/csp/ctz/applyBirthCertificate")
    public String showForm(Model model) {
    	BirthDetails birthDetails = new BirthDetails();
        model.addAttribute("birthDetails", birthDetails);

        //List<String> listProfession = Arrays.asList("Developer", "Tester", "Architect");
        //
        // model.addAttribute("listProfession", listProfession);

        //Library library=new Library();
        //model.addAttribute("library",library);

        //List<BirthDetails> libraries = libraryService.findAll();
       // model.addAttribute("libraries", libraries);


        //Double fee=libraryService.getFeeBySubscriptionType()

        return "application_form";
    }
    
    
    @GetMapping("/csp/ctz/viewApplicationStatus")
    public String showForm1(Model model)
    {
    	List<BirthDetails> birthDetails=birthService.findAll();
    	
   // String s=	birthDetails.getChildname();
   // System.out.println(s);
    model.addAttribute("birthDetails", birthDetails);
    	
    	
    	return "status_form";
    }
    
    
    @GetMapping("/csp/auth/approveapplication")
    public String showForm12(Model model)
    {
    	List<BirthDetails> birthDetails=birthService.findAll();
    	
   // String s=	birthDetails.getChildname();
   // System.out.println(s);
    model.addAttribute("birthDetails", birthDetails);
    	
    	
    	return "authority_application_view_form";
    }
    
    
    
    @PostMapping("/csp/auth/approveApplication/{id}")
    public String approveApplication(@PathVariable Long id) {
        Optional<BirthDetails> OptionalBirthDetails = birthRepository.findById(id);
        
        if (OptionalBirthDetails.isPresent())
        {
        BirthDetails birthDetails=OptionalBirthDetails.get();
        
       
            birthDetails.setStatus("Yes"); // Assuming "Yes" represents approval
            birthRepository.save(birthDetails);
        }
        return "redirect:/csp/ctz/viewApplicationStatus";
    }

    @PostMapping("/csp/auth/rejectApplication/{id}")
    public String rejectApplication(@PathVariable Long id) {
Optional<BirthDetails> OptionalBirthDetails = birthRepository.findById(id);
        
        if (OptionalBirthDetails.isPresent())
        {
        BirthDetails birthDetails=OptionalBirthDetails.get();
        
       
            birthDetails.setStatus("No"); // Assuming "Yes" represents approval
            birthRepository.save(birthDetails);
        }
        return "redirect:/csp/ctz/viewApplicationStatus";
    }
    
    
    @GetMapping("/csp/ctz/downloadPdf/{id}")
    public void downloadPdf(@PathVariable Long id, HttpServletResponse response) throws IOException {
        BirthDetails birthDetails = birthRepository.findById(id).orElse(null);
        if (birthDetails == null || !"Yes".equals(birthDetails.getStatus())) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=birthDetails.pdf");

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
        
        // Add title in center and bold
        contentStream.beginText();
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 16);
        contentStream.newLineAtOffset(200, 750);
        contentStream.showText("Birth Certificate");
        contentStream.endText();
        
        // Add certificate details
        contentStream.beginText();
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
     // Starting position
        float yPosition = 700;

        // First line
        contentStream.newLineAtOffset(50, yPosition);
        contentStream.showText("This is to certify that " + birthDetails.getChildname() + " was born to " + birthDetails.getMothername() + " and " + birthDetails.getFathername() + ".");
        contentStream.newLine();  // Move to the next line

        // Adjust the yPosition for the next line
        yPosition -= 20;

        // Second line
        contentStream.newLineAtOffset(50, yPosition);
        contentStream.newLine(); // Move to the next line

        contentStream.showText("On " + birthDetails.getDateOfBirth() + " at " + birthDetails.getPlaceofbirth() + ".");
        contentStream.newLine();  // Move to the next line

        contentStream.endText();
        
        contentStream.close();

        document.save(response.getOutputStream());
        document.close();
    }
    
}

