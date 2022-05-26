package com.example.mentalhealth.services;

import com.example.mentalhealth.models.ApplicationUser;
import com.example.mentalhealth.models.Therapists;
import com.example.mentalhealth.repository.ApplicationUserRepository;
import com.example.mentalhealth.repository.TherapistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.Principal;

@Service
public class ApplicationUserService {

    /**
     * Auto wiring used dependencies
     */
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    TherapistsRepository therapistsRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    /**
     * create a new Application user handler
     * @param user typo of Application user
     */
    public void addNewUser(ApplicationUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
    }

    /**
     * get current user consultations handler
     * @param p principle
     */
    public void getUserConsultations(Principal p, Model m) {
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());

        if (user == null) {
            Therapists therapists = therapistsRepository.findByUsername(p.getName());
            m.addAttribute("profileUser", therapists);
            m.addAttribute("Consultations", therapists.getConsultation());
            m.addAttribute("applicationUser", false);
        } else {
            m.addAttribute("profileUser", user);
            m.addAttribute("Consultations", user.getConsultation());
            m.addAttribute("applicationUser", true);

            // get all therapists for consultation adding
            Iterable allTherapists = therapistsRepository.findAllByIsEnabled(true);
            m.addAttribute("allTherapists", allTherapists);
        }
    }

    /**
     * update user profile
     * @param user
     * @param firstname
     * @param lastname
     * @param dateOfBirth
     * @param image
     * @param country
     */
    public void editUserProfile(ApplicationUser user, String firstname, String lastname, String dateOfBirth, String image, String country) {
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setDateOfBirth(dateOfBirth);
        user.setImage(image);
        user.setCountry(country);
        applicationUserRepository.save(user);
    }

}
