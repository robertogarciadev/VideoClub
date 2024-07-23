package VideoClub;

import java.time.LocalDate;


public class Rent {

    private String id_customer, id_movie;
    private LocalDate rentalDate, returnDate;
    private int rentalDays;
    private double totalRentalPrice;
    final double RENT_PER_DAY = 3.50;

    public Rent(String id_customer, String id_movie, LocalDate rentalDate, int rentalDays) {
        this.id_customer=id_customer;
        this.id_movie = id_movie;
        this.rentalDate = rentalDate;
        this.rentalDays = rentalDays;
        //AUTOCALCULADO. Uso del método .plus para sumar a la fecha de inicio los días de alquiler
        this.returnDate = rentalDate.plusDays(rentalDays);
        //AUTOCALCULADO
        this.totalRentalPrice = rentalDays * RENT_PER_DAY;
    }


    public String getId_customer() {
        return id_customer;
    }

    public void setId_customer(String id_customer) {
        this.id_customer = id_customer;
    }

    public String getId_movie() {
        return id_movie;
    }

    public void setId_movie(String id_movie) {
        this.id_movie = id_movie;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public double getTotalRentalPrice() {
        return totalRentalPrice;
    }

    public void setTotalRentalPrice(double totalRentalPrice) {
        this.totalRentalPrice = totalRentalPrice;
    }

    public String getInfoRent(){

        return "Id socio: " + id_customer + '\n'+
                "Id película: " + id_movie + '\n'+
                "Día de alquiler: " + rentalDate + '\n'+
                "Día de dovolución: " + returnDate+ '\n'+
                "Días alquilados: " +rentalDays+'\n'+
                "Total precio alquiler: "+ totalRentalPrice;

    }
}
