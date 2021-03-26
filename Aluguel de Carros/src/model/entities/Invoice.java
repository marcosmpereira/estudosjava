package model.entities;

public class Invoice {

    private Double basicPayment;
    private Double tax;

    public Invoice(){}

    public Invoice(Double basicPayment, Double tax) {
        this.basicPayment = basicPayment;
        this.tax = tax;
    }

    /**
     * Retorna o valor total a ser pago somando a taxa e o valor base
     * @return
     */
    public Double getTotalPayment(){
        return getBasicPayment() + getTax();
    }

    public Double getBasicPayment() {
        return basicPayment;
    }

    public void setBasicPayment(Double basicPayment) {
        this.basicPayment = basicPayment;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }
}
