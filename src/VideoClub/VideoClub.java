package VideoClub;

import VideoClub.DataBase.GestorBD;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class VideoClub {

    public static void main(String[] args) {

        String option="";
        Scanner sc = new Scanner(System.in);
        GestorBD gestorBD = null;
        Customer customer;
        Movie movie;
        Rent rent;

        while (!option.equals("16")){

            System.out.println("""
                Bienvenido al VideoClub CHACHE-PANTO. En el siguiente menú
                aparecen todas las gestiones que se puede realizar en el videoClub.
                1. Dar de alta un cliente
                2. Modificar un dato de un cliente
                3. Eliminar un cliente
                4. Dar de alta una película
                5. Modificar un dato de una película
                6. Eliminar una película
                7. Realizar alquiler
                8. Consultar todos los alquileres
                9. consultar alquileres en vigor
                10. Películas a devolver HOY
                11. Devolver película
                12. Ingresos por mes del año en vigor
                13. Categoría más alquilada de socios menores de 30
                14. Categoría mas alquilada de socios entre 30 y 55 años
                15. Ver todos los socios
                16. Salir
                """);
            option = sc.nextLine();

                switch (option){

                    //ALTA DE SOCIO
                    case "1":
                        try {
                            gestorBD =  new GestorBD();
                            customer = Customer.createCustomer();
                            System.out.println(customer.getInfoCustomer());
                            gestorBD.insertCustomer(customer);
                        } catch (SQLException e){
                            e.fillInStackTrace();
                            System.out.println("El nuevo socio se ha creado pero no se ha añadido a la base de datos");
                        }
                        customer = null;
                        gestorBD.closeConnection();
                        break;



                    //MODIFICAR DATOS DE UN CLIENTE
                    case "2":
                        try {
                            gestorBD = new GestorBD();
                            customer = gestorBD.getCustomerData();
                            gestorBD.updateCustomer(customer);
                        } catch (SQLException e ){
                            e.fillInStackTrace();
                        }
                        customer = null;
                        gestorBD.closeConnection();
                        break;


                    //ELIMINAR UN CLIENTE
                    case "3":
                        try {
                            gestorBD = new GestorBD();
                            customer = gestorBD.getCustomerData();
                            gestorBD.checkPendingRentsFromMembers(customer);
                        } catch (SQLException e){
                            e.fillInStackTrace();
                        }
                        customer = null;
                        gestorBD.closeConnection();
                        break;



                    //DAR DE ALTA UNA PELÍCULA
                    case "4":
                        try {
                            gestorBD = new GestorBD();
                            movie = Movie.createMovie();
                            gestorBD.insertMovie(movie);
                        }catch (SQLException e){
                            e.fillInStackTrace();
                            System.out.println("La nueva película se ha creado pero no se ha añadido a la base de datos");
                        }
                        movie = null;
                        gestorBD.closeConnection();
                        break;


                    //MODIFICAR LOS DATOS DE UNA PELÍCULA
                    case "5":
                        try {
                            gestorBD = new GestorBD();
                            movie = gestorBD.getMovieData();
                            gestorBD.updateMovie(movie);
                        } catch (SQLException e ){
                            e.fillInStackTrace();
                            System.out.println("No se ha podido actualizar la película");
                        }
                        movie = null;
                        gestorBD.closeConnection();
                        break;


                    //ELIMINAR PELÍCULA DE LA BASE DE DATOS
                    case "6":
                        try {
                            gestorBD = new GestorBD();
                            movie = gestorBD.getMovieData();
                            gestorBD.checkPendingMovieRentals(movie);
                        } catch (SQLException e){
                            e.fillInStackTrace();
                            System.out.println("No se ha podido borrar la película");
                        }
                        movie = null;
                        gestorBD.closeConnection();
                        break;


                    //REALIZAR ALQUILER
                    case "7":
                        try {
                            System.out.println("Para hacer un alquiler elige el socio, la película y los días de alquiler");
                            gestorBD = new GestorBD();
                            customer = gestorBD.getCustomerData();
                            movie = gestorBD.getMovieData();
                            int rentDays = CheckEnteredData.enterRentalDays();
                            rent = new Rent(customer.getId_customer(), movie.getId_movie(), LocalDate.now(), rentDays);
                            gestorBD.insertRent(rent);
                            gestorBD.updateStatusMovieRent(movie);
                            rent.getInfoRent();
                        }catch (SQLException e){
                            e.fillInStackTrace();
                            System.out.println("No se ha podido realizar el alquiler");
                        }
                        customer = null;
                        movie = null;
                        rent = null;
                        gestorBD.closeConnection();


                    //CONSULTAR TODOS LOS ALQUILERES
                    case "8":
                        try {
                            gestorBD = new GestorBD();
                            gestorBD.checkAllRentals();
                        } catch (SQLException e ){
                            e.fillInStackTrace();
                            System.out.println("No se han podido con sultar los alquileres");
                        }
                        gestorBD.closeConnection();
                        break;


                    //CONSULTAR TODOS LOS ALQUILERES EN VIGOR
                    case "9":
                        try {
                            gestorBD = new GestorBD();
                            gestorBD.checkCurrentRent();
                        } catch (SQLException e ){
                            e.fillInStackTrace();
                            System.out.println("No se han podido consultar los alquileres en vigor");
                        }

                        gestorBD.closeConnection();
                        break;


                    //PELÍCULAS A DEVOLVER HOY
                    case "10":
                        gestorBD = new GestorBD();
                        gestorBD.checkMoviesReturnedToday();
                        gestorBD.closeConnection();
                        break;


                    //DEVOLVER PELÍCULA
                    case "11":
                        try {
                            gestorBD = new GestorBD();
                            movie = gestorBD.getMovieData();
                            gestorBD.updateStatusMovieFree(movie);
                        }catch (SQLException e){
                            e.fillInStackTrace();
                            System.out.println("Ha habido un problema al devolver la película");
                        }
                        movie = null;
                        gestorBD.closeConnection();



                    //INGRESOS POR MES DEL AÑO ACTUAL
                    case "12":
                        gestorBD = new GestorBD();
                        gestorBD.annualIncomePerMonth();
                        gestorBD.closeConnection();
                        break;


                    //CONSULTAR LAS CATEGORÍAS MÁS ALQUILDAS POR SOCIOS DE HASTA 30 AÑOS
                    case "13":
                        gestorBD = new GestorBD();
                        gestorBD.mostRentedCategoryUpToThirtyYears();
                        gestorBD.closeConnection();
                        break;


                    //CONSULTAR CATEGORÍA MÁS QUILADA POR SOCIOS ENTRE 30 Y 55
                    case "14":
                        gestorBD = new GestorBD();
                        gestorBD.mostRentedCategoryBetween30And55();
                        gestorBD.closeConnection();
                        break;


                    // CONSULTAR TODOS LOS SOCIOS
                    case "15":
                        try {
                            gestorBD = new GestorBD();
                            gestorBD.consultAllCustomers();
                        } catch (SQLException e){
                            e.fillInStackTrace();
                        }
                        gestorBD.closeConnection();


                    //SALIR DE LA APP
                    case "16":
                        System.out.println("Has salido de la APP. Hasta pronto");
                        break;
                    default:
                        System.out.println("Opción no contemplada");
                }
        }


    }
}
