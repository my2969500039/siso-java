package com.siso.entity.web.region;

public class city {
    private int id;
    private int pro_id;
    private String  city;
    public city(){
        super();
    }

    public city(int id, int pro_id, String city) {
        this.id = id;
        this.pro_id = pro_id;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
