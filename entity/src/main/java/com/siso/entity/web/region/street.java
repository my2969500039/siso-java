package com.siso.entity.web.region;

public class street {
    public int id;
    public int town_id;
    public String street;

    public street(){
        super();
    }

    public street(int id, int town_id, String street) {
        this.id = id;
        this.town_id = town_id;
        this.street = street;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTown_id() {
        return town_id;
    }

    public void setTown_id(int town_id) {
        this.town_id = town_id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
