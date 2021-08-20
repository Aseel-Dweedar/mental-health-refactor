package com.example.mentalhealth.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Entity
public class Therapists implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
//    @UniqueConstraint(columnNames={"username"})   // for check username if it's in the application_user table or not
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String image;
    private String country;
    private String SpecializedIn ;
    private String experiences;
    private Integer numOfSessions;

    @OneToMany (mappedBy = "therapists")
    private List<Consultation> Consultation ;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    //default constructor
    public Therapists(){}

    //constructor
    public Therapists(String username, String password, String firstname, String lastname, String image, String country, String specializedIn, String experiences, Integer numOfSessions) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.image = image;
        this.country = country;
        SpecializedIn = specializedIn;
        this.experiences = experiences;
        this.numOfSessions = numOfSessions;
    }


    ///////Getter and Setter For RelationShip ///////
    public List<Consultation> getConsultation() {
        return Consultation;
    }
    public void setConsultation(List<Consultation> consultation) {
        Consultation = consultation;
    }


    // getter for id
    public Integer getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    //setter and getter
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSpecializedIn() {
        return SpecializedIn;
    }

    public void setSpecializedIn(String specializedIn) {
        SpecializedIn = specializedIn;
    }

    public String getExperiences() {
        return experiences;
    }

    public void setExperiences(String experiences) {
        this.experiences = experiences;
    }

    public Integer getNumOfSessions() {
        return numOfSessions;
    }

    public void setNumOfSessions(Integer numOfSessions) {
        this.numOfSessions = numOfSessions;
    }

}
