import VideoClub.CheckEnteredData;
import VideoClub.Customer;
import VideoClub.DataBase.GestorBD;
import VideoClub.Movie;
import VideoClub.Rent;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAmount;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {


    public static void main(String[] args) {

        GestorBD gestorBD = new GestorBD();
        //gestorBD.closeConecction();
        //gestorBD.mostRentedCategoryBetween30And55();

        //LocalDate localDate = LocalDate.of(1984, 8, 19);
        //LocalDate checkAge = LocalDate.now().minusYears(localDate.getYear()).minusMonths(localDate.getMonthValue()).minusDays(localDate.getDayOfMonth());

        try {
            Movie movie = gestorBD.getMovieData();
            gestorBD.updateStatusMovieFree(movie);
        } catch (SQLException e){

        }



    }
}