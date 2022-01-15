package com.zavaly.models.pricemodel;

public class PriceDetailsObject {

    private String quantityRange;
    private String quantityDisPrice;
    private String quantityPrice;


    public PriceDetailsObject(String quantityRange, String quantityDisPrice, String quantityPrice) {
        this.quantityRange = quantityRange;
        this.quantityDisPrice = quantityDisPrice;
        this.quantityPrice = quantityPrice;
    }

    public String getQuantityRange() {
        return quantityRange;
    }

    public void setQuantityRange(String quantityRange) {
        this.quantityRange = quantityRange;
    }

    public String getQuantityDisPrice() {
        return quantityDisPrice;
    }

    public void setQuantityDisPrice(String quantityDisPrice) {
        this.quantityDisPrice = quantityDisPrice;
    }

    public String getQuantityPrice() {
        return quantityPrice;
    }

    public void setQuantityPrice(String quantityPrice) {
        this.quantityPrice = quantityPrice;
    }


    @Override
    public String toString() {
        return "PriceDetailsObject{" +
                "quantityRange='" + quantityRange + '\'' +
                ", quantityDisPrice='" + quantityDisPrice + '\'' +
                ", quantityPrice='" + quantityPrice + '\'' +
                '}';
    }
}
