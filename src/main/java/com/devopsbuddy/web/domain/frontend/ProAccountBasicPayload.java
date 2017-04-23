package com.devopsbuddy.web.domain.frontend;

/**
 * Created by Carren.Dsouza on 23/04/2017.
 */
public class ProAccountBasicPayload extends  BasicAccountPayload {

    private String cardNumber;
    private String cardCode;
    private String cardMonth;
    private String cardYear;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getCardMonth() {
        return cardMonth;
    }

    public void setCardMonth(String cardMonth) {
        this.cardMonth = cardMonth;
    }

    public String getCardYear() {
        return cardYear;
    }

    public void setCardYear(String cardYear) {
        this.cardYear = cardYear;
    }
}
