package com.firstproject.demo.model;

import jakarta.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    private String city;
    private String country;
    private Long zipCode;

    @OneToOne(mappedBy = "address")
    private Student student;

    public Address() {
    }

    public Address(Long addressId, String city, String country, Long zipCode) {
        this.addressId = addressId;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getZipCode() {
        return zipCode;
    }

    public void setZipCode(Long zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", zipCode=" + zipCode +
                '}';
    }
}
