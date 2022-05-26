package com.example.mentalhealth.services;

import com.example.mentalhealth.models.Therapists;
import com.example.mentalhealth.repository.TherapistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TherapyServices {

    /**
     * Auto wiring used dependencies
     */
    @Autowired
    TherapistsRepository therapistsRepository;

    /**
     * update Therapy profile
     * @param therapy
     * @param firstname
     * @param lastname
     * @param image
     * @param country
     * @param specializedIn
     * @param experiences
     * @param numOfSessions
     */
    public void editTherapyProfile(Therapists therapy,
                                   String firstname,
                                   String lastname,
                                   String image,
                                   String country,
                                   String specializedIn,
                                   String experiences,
                                   Integer numOfSessions) {
        therapy.setFirstname(firstname);
        therapy.setLastname(lastname);
        therapy.setImage(image);
        therapy.setCountry(country);
        therapy.setSpecializedIn(specializedIn);
        therapy.setExperiences(experiences);
        therapy.setNumOfSessions(numOfSessions);
        therapistsRepository.save(therapy);
    }

}
