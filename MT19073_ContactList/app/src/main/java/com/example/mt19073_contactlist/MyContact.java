package com.example.mt19073_contactlist;

public class MyContact   {

    private String name;
    private String phnNum;
    private int pic;

    public MyContact(String name, String phnNum, int pic) {
        this.name = name;
        this.phnNum = phnNum;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public String getPhnNum() {
        return phnNum;
    }

    public int getPic() {
        return pic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhnNum(String phnNum) {
        this.phnNum = phnNum;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
}
