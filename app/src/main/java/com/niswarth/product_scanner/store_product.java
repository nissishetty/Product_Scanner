package com.niswarth.product_scanner;

public class store_product {
    String barcode; //id
    String prodname;
    String prodcat;
    String prodmrp;
    String prodexp;

    public store_product()
    {

    }

    public store_product(String barcode, String prodname, String prodcat, String prodmrp, String prodexp) {
        this.barcode = barcode;
        this.prodname = prodname;
        this.prodcat = prodcat;
        this.prodmrp = prodmrp;
        this.prodexp = prodexp;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getProdname() {
        return prodname;
    }

    public String getProdcat() {
        return prodcat;
    }

    public String getProdmrp() {
        return prodmrp;
    }

    public String getProdexp() {
        return prodexp;
    }
}
