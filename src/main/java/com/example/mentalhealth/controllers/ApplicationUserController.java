package com.example.mentalhealth.controllers;

import com.example.mentalhealth.models.ApplicationUser;
import com.example.mentalhealth.models.Therapists;
import com.example.mentalhealth.repository.ApplicationUserRepository;
import com.example.mentalhealth.repository.TherapistsRepository;
import com.example.mentalhealth.services.ApplicationUserService;
import com.example.mentalhealth.services.TherapyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    TherapistsRepository therapistsRepository;
    @Autowired
    ApplicationUserService applicationUserService;
    @Autowired
    TherapyServices therapyServices;


    /**
     * this route used to app a new app user
     * @param user type of ApplicationUser
     * @return "/signupUser"
     */
    @PostMapping("/signupUser")
    public RedirectView addNewUser(@ModelAttribute ApplicationUser user) {
        Therapists existUserName = therapistsRepository.findByUsername(user.getUsername());
        if (existUserName == null) {
            applicationUserService.addNewUser(user);
        } else {
            System.out.println("Exist username");
        }
        return new RedirectView("/login");
    }


    /**
     * this route used to get the logged-in user consultations
     * @param p principle
     * @param m model
     * @return "/myProfile"
     */
    @GetMapping("/myProfile")
    public String getUserConsultation(Principal p, Model m) {
        applicationUserService.getUserConsultations(p, m);
        return "myProfile";
    }


    /**
     * this route used to edit current user profile
     * @return "/editProfile"
     */
    @PutMapping("/editProfile")
    public RedirectView editProfile(@RequestParam("firstname") String firstname,
                                    @RequestParam("lastname") String lastname,
                                    @RequestParam("dateOfBirth") String dateOfBirth,
                                    @RequestParam("image") String image,
                                    @RequestParam("country") String country,
                                    @RequestParam("specializedIn") String specializedIn,
                                    @RequestParam("experiences") String experiences,
                                    @RequestParam("numOfSessions") Integer numOfSessions,
                                    Principal principal, Model model) {
        ApplicationUser user = applicationUserRepository.findByUsername(principal.getName());
        if (user == null) {
            Therapists therapy = therapistsRepository.findByUsername(principal.getName());
            therapyServices.editTherapyProfile(therapy ,firstname, lastname, image, country, specializedIn, experiences, numOfSessions);
        } else {
            applicationUserService.editUserProfile(user, firstname, lastname, dateOfBirth, image, country);
        }
        return new RedirectView("/myProfile");
    }
}

