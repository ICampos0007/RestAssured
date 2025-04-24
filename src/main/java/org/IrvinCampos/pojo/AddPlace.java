package org.IrvinCampos.pojo;

import java.util.List;

public class AddPlace {
    private int accuracy;
    private String name;
    private String phoneNumber;
    private String address;
    private String website;
    private String language;
    private Location location;
    private List<String> types;

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getWebsite() {
        return website;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<String> getTypes() {
        return types;
    }
}
