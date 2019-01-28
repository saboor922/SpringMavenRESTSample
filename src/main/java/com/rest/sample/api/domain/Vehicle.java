package com.rest.sample.api.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.List;

@Document(collection = "vehicle")
@ResponseBody
public class Vehicle {

    @Id
    private BigInteger _id;

    @Field("registration_number")
    private String registrationNumber;

    @Field("manufacturer")
    private String manufacturer;

    @Field("model")
    private String model;

    @Field("sub_model")
    private String subModel;

    @Field("year")
    private int year;

    @Field("category")
    private List<String> category;

    public Vehicle() {

    }

    public Vehicle(String registration_number, String manufacturer, int year) {
        this.registrationNumber = registrationNumber;
        this.manufacturer = manufacturer;
        this.year = year;
    }

    public BigInteger get_id() {
        return _id;
    }

    public void set_id(BigInteger _id) {
        this._id = _id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registration_number) {
        this.registrationNumber = registration_number;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSubModel() {
        return subModel;
    }

    public void setSubModel(String subModel) {
        this.subModel = subModel;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "_id=" + _id +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", subModel='" + subModel + '\'' +
                ", year=" + year +
                ", category=" + category +
                '}';
    }
}
