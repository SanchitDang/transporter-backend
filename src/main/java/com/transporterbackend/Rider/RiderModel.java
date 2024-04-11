package com.transporterbackend.Rider;

import jakarta.persistence.*;

@Entity
@Table(name = "riders")
public class RiderModel {
    @Id
    @SequenceGenerator(
            name= "rider_sequence",
            sequenceName = "rider_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rider_sequence"
    )
    private Long id;
    private String email;
    private String password;
    private String phone;
    private String firstName;
    private String lastName;

    // use @Transient if we want any value that we do not want to put inside as a column

    public RiderModel() {
    }

    public RiderModel(Long id, String email, String password, String phone, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public RiderModel(String email, String password, String phone, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "RiderModel{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
