package com.siso.entity.web.region;

public class town {
    private int id;
    private int area_id;
    private String town;

    public town(){
        super();
    }

    public town(int id, int area_id, String town) {
        this.id = id;
        this.area_id = area_id;
        this.town = town;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
