package source;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrasilTaxService;
import model.services.RentalService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class ProgramJava {

    public static void main(String args[]) throws ParseException {

        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:ss");

        System.out.println("ENTRE COM OS DADOS DA NOTA");
        System.out.print("Modelo co Carro:");
        String carModel = scanner.nextLine();
        System.out.print("Horário de saída:");
        Date start = simpleDateFormat.parse(scanner.nextLine());
        System.out.print("Horário de Retorno:");
        Date finish = simpleDateFormat.parse(scanner.nextLine());

        CarRental carRental = new CarRental(start, finish, new Vehicle(carModel));

        System.out.print("Informe o valor por Hora:");
        double pricePerHour = scanner.nextDouble();
        System.out.print("Informe o valor por Dia:");
        double pricePerDay = scanner.nextDouble();

        RentalService rentalService = new RentalService(
                pricePerDay,
                pricePerHour,
                new BrasilTaxService()
        );

        rentalService.processInvoice(carRental);
        printResult(carRental);
        scanner.close();
    }

    public static void printResult(CarRental carRental) {
        System.out.println("RECIBO");
        System.out.println("Valor base:"+ carRental.getInvoice().getBasicPayment());
        System.out.println("Valor da taxa:"+ carRental.getInvoice().getTax());
        System.out.println("Valor total: "+ carRental.getInvoice().getTotalPayment());
    }
}
