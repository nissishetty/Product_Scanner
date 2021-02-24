package com.niswarth.product_scanner;

public class Retrivedata {
    String barcode; //id
    String prodname;
    String prodcat;
    String prodmrp;
    String prodexp;

    public Retrivedata(String barcode, String prodname, String prodcat, String prodmrp, String prodexp) {
        this.barcode = barcode;
        this.prodname = prodname;
        this.prodcat = prodcat;
        this.prodmrp = prodmrp;
        this.prodexp = prodexp;
    }

    public Retrivedata()
    {

    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public String getProdcat() {
        return prodcat;
    }

    public void setProdcat(String prodcat) {
        this.prodcat = prodcat;
    }

    public String getProdmrp() {
        return prodmrp;
    }

    public void setProdmrp(String prodmrp) {
        this.prodmrp = prodmrp;
    }

    public String getProdexp() {
        return prodexp;
    }

    public void setProdexp(String prodexp) {
        this.prodexp = prodexp;
    }
}
