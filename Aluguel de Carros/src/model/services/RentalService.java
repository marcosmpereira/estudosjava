package model.services;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {

    private Double pricePerDay;
    private Double pricePerHour;
    private TaxService taxService;

    public RentalService(
            Double pricePerDay,
            Double pricePerHour,
            TaxService taxService
    ) {
        this.pricePerDay = pricePerDay;
        this.pricePerHour = pricePerHour;
        this.taxService = taxService;
    }

    public void processInvoice(CarRental carRental) {
        long timeStart = carRental.getStart().getTime();
        long timeFinish = carRental.getFinish().getTime();
        double hours = calcHours(timeStart, timeFinish);
        double basicPayment;

        if(hours <= 12.0)
            basicPayment = Math.ceil(hours) * pricePerHour;
        else
            basicPayment = Math.ceil(hours) * pricePerDay;

        double tax = taxService.tax(basicPayment);
        carRental.setInvoice(new Invoice(basicPayment, tax));
    }

    private double calcHours(long start, long finish) {
        return (double) (finish - start) / 1000 /60 / 60;
    }
}
