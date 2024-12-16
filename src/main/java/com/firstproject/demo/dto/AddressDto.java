package com.firstproject.demo.dto;

public class AddressDto {

    private String city;
    private String country;
    private Long zipCode;
    private Long studentId;

    public AddressDto() {
    }

    public AddressDto(String city, String country, Long zipCode, Long studentId) {
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
        this.studentId = studentId;
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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "AddressDto{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", zipCode=" + zipCode +
                ", studentId=" + studentId +
                '}';
    }
}
