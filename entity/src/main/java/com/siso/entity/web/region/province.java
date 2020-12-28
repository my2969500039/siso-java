package com.siso.entity.web.region;

public class province {
    private int id;
    private String province;

    public province() {
        super();
    }

    public province(int id, String province) {
        this.id = id;
        this.province = province;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
