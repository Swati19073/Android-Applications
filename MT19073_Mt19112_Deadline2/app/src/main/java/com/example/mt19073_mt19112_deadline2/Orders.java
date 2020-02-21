package com.example.mt19073_mt19112_deadline2;

public class Orders {

    private String oemail;
    private String oitemName;
    private Float ocost;
    private Float oquant;
    private Float ototalCost;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public  Orders(){}
    public String getOemail() {
        return oemail;
    }

    public void setOemail(String oemail) {
        this.oemail = oemail;
    }

    public String getOitemName() {
        return oitemName;
    }

    public void setOitemName(String oitemName) {
        this.oitemName = oitemName;
    }

    public Float getOcost() {
        return ocost;
    }

    public void setOcost(Float ocost) {
        this.ocost = ocost;
    }

    public Float getOquant() {
        return oquant;
    }

    public void setOquant(Float oquant) {
        this.oquant = oquant;
    }

    public Float getOtotalCost() {
        return ototalCost;
    }

    public void setOtotalCost(Float ototalCost) {
        this.ototalCost = ototalCost;
    }
}

