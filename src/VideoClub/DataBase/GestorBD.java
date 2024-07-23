package VideoClub.DataBase;

import VideoClub.CheckEnteredData;
import VideoClub.Customer;
import VideoClub.Enum.Gender;
import VideoClub.Enum.State;
import VideoClub.Movie;
import VideoClub.Rent;

import java.sql.*;
import java.time.LocalDate;


public class GestorBD {

    private Connection connection;


    private void makeConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:8889/VideoClub";
            String user = "root";
            String password = "root";
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
        } catch (ClassNotFoundException e){
            e.fillInStackTrace();
            System.out.println("Clase no encontrada");
        }

    }
    public void closeConnection(){

        try {
            if (!connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e){
            e.fillInStackTrace();
            System.out.println("No se ha podido cerrar la conexión");
        }

    }
    public GestorBD() {
        try {
            makeConnection();
        } catch (SQLException e){
            e.fillInStackTrace();
            System.out.println("No se ha podido hacer la conexión");
        }

    }

    //MÉTODOS/CONSULTAS PARA SOCIOS
    public void insertCustomer(Customer customer) throws SQLException{

        String query = "INSERT INTO Socios (id_socio, nombre, apellido, nº_teléfono, fecha_nacimiento)  VALUES(?,?,?,?,?)";
        PreparedStatement preparedStatement;

        try {
            if (!connection.isClosed()){
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, customer.getId_customer());
                preparedStatement.setString(2, customer.getName());
                preparedStatement.setString(3, customer.getSecondName());
                preparedStatement.setLong(4, customer.getPhoneNumber());
                preparedStatement.setDate(5, Date.valueOf(customer.getBirthDate()));

                int num=preparedStatement.executeUpdate();
                System.out.printf("Se han añadido %d socio nuevo a la base de datos", num);
                connection.commit();
                preparedStatement.close();
            }
        }catch (SQLException e){
            connection.rollback();
            e.fillInStackTrace();
        }
    }
    public void deleteCustomer(Customer customer) throws SQLException{

        String query = "DELETE FROM Socios WHERE id_socio = ?";
        PreparedStatement preparedStatement;

        try {
            if (!connection.isClosed()){
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, customer.getId_customer());
                preparedStatement.executeUpdate();
                System.out.printf("Se ha borrado al socio %s %s de la base de datos", customer.getName(), customer.getSecondName());
                connection.commit();
                preparedStatement.close();
            }
        }catch (SQLException e){
            connection.rollback();
            e.fillInStackTrace();
        }
    }
    public void updateCustomer(Customer customer) throws SQLException{

        System.out.println(customer.getInfoCustomer());
        System.out.println("ACTUALIZA LOS DATOS DEL USUARIO");
        customer.setName(CheckEnteredData.customerCheckName());
        customer.setSecondName(CheckEnteredData.customerCheckSecondName());
        customer.setPhoneNumber(CheckEnteredData.customerCheckPhoneNumber());
        customer.setBirthDate(CheckEnteredData.customerCheckBirthDate());

        String query = "UPDATE Socios SET nombre=?, apellido=?, nº_teléfono=?, fecha_nacimiento=? WHERE id_socio = ?";
        PreparedStatement preparedStatement;

        try {
            if (!connection.isClosed()){
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, customer.getName());
                preparedStatement.setString(2, customer.getSecondName());
                preparedStatement.setLong(3, customer.getPhoneNumber());
                preparedStatement.setDate(4, Date.valueOf(customer.getBirthDate()));
                preparedStatement.setString(5, customer.getId_customer());

                int num = preparedStatement.executeUpdate();
                System.out.printf("Se ha actualizado %d socio", num);
                connection.commit();
                preparedStatement.close();
                preparedStatement.getConnection().close();
            }
        }catch (SQLException e){
            connection.rollback();
            e.fillInStackTrace();
        }
    }
    public void consultAllCustomers() throws SQLException{

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            if (!connection.isClosed()){
                String query = " SELECT * FROM Socios";
                preparedStatement = connection.prepareStatement(query);
                 resultSet = preparedStatement.executeQuery();
                if(resultSet != null){
                    while (resultSet.next()){
                        String id_customer = resultSet.getString("id_socio");
                        String name = resultSet.getString("nombre");
                        String surName = resultSet.getString("apellido");
                        long phoneNumber = resultSet.getLong("nº_teléfono");
                        Date date = resultSet.getDate("fecha_nacimiento");
                        LocalDate birthDate = date.toLocalDate();
                        System.out.printf("%d. %s %s %s %d %s", resultSet.getRow(), id_customer, name, surName, phoneNumber, birthDate.toString());
                        System.out.println();
                    }

                }
                assert resultSet != null;
                resultSet.close();
                preparedStatement.close();
            }
        }catch (SQLException e){
            e.fillInStackTrace();
        }


    }
    public Customer getCustomerData() throws  SQLException{

        String nameConsole = CheckEnteredData.searchByName().toLowerCase();
        String surnameConsole = CheckEnteredData.searchBySurname().toLowerCase();
        String id_customerDb = null;
        String name = null;
        String secondName = null;
        long phoneNumber = 0;
        LocalDate birthDate = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            String query = "SELECT id_socio, nombre, apellido, nº_teléfono, fecha_nacimiento " +
                    "FROM Socios " +
                    "WHERE nombre LIKE ? AND apellido LIKE ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,"%"+nameConsole+"%" );
            preparedStatement.setString(2, "%"+surnameConsole+"%");
            resultSet = preparedStatement.executeQuery();
            //uso de if para comprobar que el resultSet tenga valor != null
            if (resultSet != null){
                //Uso del while para pasar de un registro a otro y los va cotejando con la ID introducida por parámetros
                while (resultSet.next()){
                    String nameDB = resultSet.getString("nombre").toLowerCase();
                    String surnameDB = resultSet.getString("apellido").toLowerCase();
                    boolean match = nameDB.contains(nameConsole) && surnameDB.contains(surnameConsole);

                        if (match){
                            break;
                        } else if (resultSet.isAfterLast()) {
                            System.out.println("No exite el socio");
                           break;
                        }

                }
                id_customerDb = resultSet.getString("id_socio");
                name = resultSet.getString("nombre");
                secondName = resultSet.getString("apellido");
                phoneNumber = resultSet.getLong("nº_teléfono");
                birthDate = resultSet.getDate("fecha_nacimiento").toLocalDate();
            }

        }catch (SQLException e){
            e.fillInStackTrace();
            System.out.println("No se ha podido realizar la consulta");
        }
        return new Customer(id_customerDb, name, secondName, phoneNumber, birthDate) ;
    }
    public void checkPendingRentsFromMembers(Customer customer) throws SQLException {
        String query = "SELECT  Socios.id_socio, Socios.nombre, Socios.apellido, Alquileres.id_pelicula, Alquileres.fecha_alquiler, Alquileres.fecha_devolucion " +
                "FROM Socios " +
                "INNER JOIN  Alquileres ON Socios.id_socio = Alquileres.id_socio " +
                "WHERE Socios.id_socio = ?";

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        boolean rentPeding =false;

        try {
            if (!connection.isClosed()){
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,customer.getId_customer());
                resultSet = preparedStatement.executeQuery();

                if (resultSet != null){
                    while (resultSet.next()){
                        LocalDate returnDay = resultSet.getDate("fecha_devolucion").toLocalDate();
                        if (returnDay.isAfter(LocalDate.now())){
                            String name = resultSet.getString("nombre");
                            String id_movie = resultSet.getString("id_pelicula");
                            Date rentDate = resultSet.getDate("fecha_alquiler");
                            System.out.printf("""
                                                  NO SE PUEDE BORRAR EL CLIENTE PORQUE TIENE ALQUILERES PENDIENTES.
                                                  Nombre socio: %s
                                                  Película en alquiler: %s
                                                  Fecha de alquiler: %s
                                                  Fecha de devolución: %s
                                                    """,name, id_movie, rentDate.toString(),returnDay);
                            rentPeding=true;
                            break;
                        }
                    }
                    if (!rentPeding){
                        deleteCustomer(customer);
                        System.out.printf("Se ha borrado al socio %s %s de la base de datos",
                                customer.getName(), customer.getSecondName());
                    }
                }

                assert resultSet != null;
                resultSet.close();
                preparedStatement.close();

            }
        }catch (SQLException e ){
            e.fillInStackTrace();
            System.out.println("No se han podido comprobar los alquileres pendientes del socio");
        }

    }

    //MÉTODOS/CONSULTAS PARA PELÍCULAS
    public void insertMovie(Movie movie) throws SQLException{

        String query = "INSERT INTO Películas (id_pelicula, titulo, año, duracion, genero, estado)  VALUES(?,?,?,?,?,?)";
        PreparedStatement preparedStatement;

        try {
            if (!connection.isClosed()){
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, movie.getId_movie());
                preparedStatement.setString(2, movie.getTitle());
                preparedStatement.setInt(3, movie.getYear());
                preparedStatement.setInt(4, movie.getDuration());
                preparedStatement.setString(5, movie.getGender().toString());
                preparedStatement.setString(6, movie.getState().toString());

                int num=preparedStatement.executeUpdate();
                System.out.printf("Se han añadido %d película nueva", num);
                connection.commit();
                preparedStatement.close();

            }

        }catch (SQLException e){
            connection.rollback();
            e.fillInStackTrace();
        }

    }
    public void deleteMovie(Movie movie) throws  SQLException{

        String query = "DELETE FROM Películas WHERE id_pelicula = ?";
        PreparedStatement preparedStatement;

            try {
                if (!connection.isClosed()){
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, movie.getId_movie());
                    preparedStatement.executeUpdate();
                    System.out.printf("Se ha borrado la película %s de la base de datos", movie.getTitle());
                    connection.commit();
                    preparedStatement.close();
                }

            }catch (SQLException e){
                connection.rollback();
                e.fillInStackTrace();
            }
    }
    public void updateMovie(Movie movie) throws SQLException{
        System.out.println("Actualiza los datos de la película");
        movie.setTitle(CheckEnteredData.movieCheckTitle());
        movie.setYear(CheckEnteredData.movieCheckYear());
        movie.setDuration(CheckEnteredData.movieCheckDuration());
        movie.setGender(CheckEnteredData.movieCheckGender());
        movie.setState(CheckEnteredData.stateMovie());
        String query = "UPDATE Películas SET titulo=?, año=?, duracion=?, genero=?, estado=? WHERE id_pelicula=?";
        PreparedStatement preparedStatement;

        try {
            if(!connection.isClosed()){
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, movie.getTitle());
                preparedStatement.setInt(2, movie.getYear());
                preparedStatement.setInt(3, movie.getDuration());
                preparedStatement.setString(4, movie.getGender().toString());
                preparedStatement.setString(5, movie.getState().toString());
                preparedStatement.setString(6,movie.getId_movie());

                int num = preparedStatement.executeUpdate();
                System.out.println(String.format("Se han actualizado %d película"));
                connection.commit();
                preparedStatement.close();
            }
        }catch (SQLException e ){
            connection.rollback();
            e.fillInStackTrace();
        }
    }
    public Movie getMovieData()throws SQLException{

        String searchTitle = CheckEnteredData.searchByTitle().toLowerCase();
        String id_movie = null;
        String title = null;
        int year = 0;
        int duration = 0;
        Gender gender = null;
        State state = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            String query = "SELECT id_pelicula, titulo, año, duracion, genero, estado " +
                    "FROM Películas " +
                    "WHERE titulo LIKE ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%"+searchTitle+"%");
            resultSet = preparedStatement.executeQuery();
            //uso de if para comprobar que el resultSet tenga valor != null
            if (resultSet != null){
                //Uso del while para pasar de un registro a otro y los va cotejando
                // con el título introducido con el método searchByTitle()
                while (resultSet.next()){
                    title = resultSet.getString("titulo").toLowerCase();
                    boolean matchTitle = title.contains(searchTitle);
                   if (matchTitle){
                       break;
                   } else if (resultSet.isAfterLast()) {
                       System.out.println("No existe la película");
                   }

                }
                id_movie = resultSet.getString("id_pelicula");
                title = resultSet.getString("titulo");
                year = resultSet.getInt("año");
                duration = resultSet.getInt("duracion");
                gender = Gender.valueOf(resultSet.getString("genero"));
                state = State.valueOf(resultSet.getString("estado"));
                resultSet.close();
                preparedStatement.close();
            }

        }catch (SQLException e){
            e.fillInStackTrace();
            System.out.println("No se ha podido realizar la consulta");
        }

        return new Movie(id_movie, title, year, duration, gender, state) ;
    }
    public void checkPendingMovieRentals(Movie movie){
        String query ="SELECT Películas.id_pelicula, Películas.titulo, Alquileres.id_socio, Alquileres.fecha_alquiler, Alquileres.fecha_devolucion " +
                "FROM Películas " +
                "INNER JOIN  Alquileres ON Películas.id_pelicula = Alquileres.id_pelicula " +
                "WHERE Películas.id_pelicula = ?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        boolean rentPending= false;

        try {
            if (!connection.isClosed()){
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, movie.getId_movie());
                resultSet = preparedStatement.executeQuery();
                if (resultSet!= null){
                    while (resultSet.next()){

                        LocalDate returnDate = resultSet.getDate("fecha_devolucion").toLocalDate();
                        if (returnDate.isAfter(LocalDate.now())){
                            String id_movie = resultSet.getString("id_pelicula");
                            String titleMovie = resultSet.getString("titulo");
                            String idCustomer = resultSet.getString("id_socio");
                            Date rentDay = resultSet.getDate("fecha_alquiler");
                            System.out.printf("""
                                    NO SE PUEDE BORRAR LA PELÍCULA PORQUE TIENE ALQUILERES PENDIENTES:
                                    Id película: %s
                                    Título: %s
                                    Alquilada por el socio: %s
                                    Fecha alquiler: %s
                                    Fecha devolución: %s
                                    """, id_movie, titleMovie,idCustomer,rentDay.toString(), returnDate );
                            rentPending= true;
                            break;
                        }
                    }

                    if (!rentPending){
                        deleteMovie(movie);
                    }
                }
            }
        } catch (SQLException e){
            e.fillInStackTrace();
            System.out.println("No se ha podido consultar los alquileres de película pendientes");
        }
    }
    public void updateStatusMovieFree(Movie movie) throws SQLException{

        String query ="UPDATE Películas SET estado = ? WHERE id_pelicula = ? ";
        PreparedStatement preparedStatement;

        try {
            if (!connection.isClosed()){
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, State.LIBRE.toString() );
                preparedStatement.setString(2, movie.getId_movie());
                preparedStatement.executeUpdate();
                connection.commit();
                preparedStatement.close();

            }
        } catch (SQLException e){
            connection.rollback();
            e.fillInStackTrace();
            System.out.println("No se ha podido cambiar el estado");
        }



    }
    public void updateStatusMovieRent(Movie movie) throws SQLException{

        String query ="UPDATE Películas SET estado = ? WHERE id_pelicula = ? ";
        PreparedStatement preparedStatement;

        try {
            if (!connection.isClosed()){
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, State.ALQUILADA.toString() );
                preparedStatement.setString(2, movie.getId_movie());
                preparedStatement.executeUpdate();
                connection.commit();
                preparedStatement.close();

            }
        } catch (SQLException e){
            connection.rollback();
            e.fillInStackTrace();
            System.out.println("No se ha podido cambiar el estado");
        }



    }


    //METODOS PARA ALQUILERES
    public void insertRent(Rent rent) throws SQLException{

        String query = "INSERT INTO Alquileres (id_socio, id_pelicula, fecha_alquiler, dias_alquiler, fecha_devolucion, precio_total_alquiler)" +
                " VALUES(?,?,?,?,?,?)";
        PreparedStatement preparedStatement;

        try {
            if (!connection.isClosed()){
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, rent.getId_customer());
                preparedStatement.setString(2, rent.getId_movie());
                preparedStatement.setDate(3, Date.valueOf(rent.getRentalDate())  );
                preparedStatement.setInt(4, rent.getRentalDays());
                preparedStatement.setDate(5, Date.valueOf(rent.getReturnDate()));
                preparedStatement.setDouble(6, rent.getTotalRentalPrice());

                int num = preparedStatement.executeUpdate();
                System.out.printf("Se ha añadido %d un alquiler", num);
                connection.commit();
                preparedStatement.close();
            }
        } catch (SQLException e){
            connection.rollback();
            e.fillInStackTrace();
        }

    }
    public void checkAllRentals() throws SQLException{

        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            String query = "SELECT * FROM Alquileres";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (!connection.isClosed()){
                if (resultSet!=null){
                    while (resultSet.next()){
                        String id_customer = resultSet.getString("id_socio");
                        String id_movie = resultSet.getString("id_pelicula");
                        LocalDate rentalDate = resultSet.getDate("fecha_alquiler").toLocalDate();
                        int rentalDays= resultSet.getInt("dias_alquiler");
                        LocalDate rentalReturns = resultSet.getDate("fecha_devolucion").toLocalDate();
                        double totalRentalPrice = resultSet.getDouble("precio_total_alquiler");
                        System.out.println(String.format("%d. %s %s %s %d %s %.2f", resultSet.getRow(), id_customer,
                                id_movie, rentalDate, rentalDays, rentalReturns, totalRentalPrice));
                    }
                    resultSet.close();
                    preparedStatement.close();
                }
            }
        } catch (SQLException e){
            e.fillInStackTrace();
        }
    }
    public void checkCurrentRent() throws SQLException{

        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            String query = "SELECT * FROM Alquileres WHERE fecha_devolucion >= ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
            resultSet = preparedStatement.executeQuery();
            if (!connection.isClosed()){
                if (resultSet!=null){
                    while (resultSet.next()){
                        String id_customer = resultSet.getString("id_socio");
                        String id_movie = resultSet.getString("id_pelicula");
                        LocalDate rentalDate = resultSet.getDate("fecha_alquiler").toLocalDate();
                        int rentalDays= resultSet.getInt("dias_alquiler");
                        LocalDate rentalReturns = resultSet.getDate("fecha_devolucion").toLocalDate();
                        double totalRentalPrice = resultSet.getDouble("precio_total_alquiler");
                        System.out.println(String.format("%d. %s %s %s %d %s %.2f", resultSet.getRow(), id_customer,
                                id_movie, rentalDate, rentalDays, rentalReturns, totalRentalPrice));
                    }
                    resultSet.close();
                    preparedStatement.close();
                }
            }
        } catch (SQLException e){
            e.fillInStackTrace();
        }
    }
    public void annualIncomePerMonth(){
        Income income = new Income();

        try {
            income.january(connection);
            income.february(connection);
            income.march(connection);
            income.april(connection);
            income.may(connection);
            income.june(connection);
            income.july(connection);
            income.august(connection);
            income.september(connection);
            income.october(connection);
            income.november(connection);
            income.december(connection);
        }catch (SQLException e){
            e.fillInStackTrace();
            System.out.println("Algo ha falado en la consulta de la caja");
        }
    }
    public void checkMoviesReturnedToday(){
        String query ="SELECT Películas.id_pelicula, Películas.titulo, Alquileres.id_socio, Alquileres.fecha_alquiler, Alquileres.fecha_devolucion " +
                "FROM Películas " +
                "INNER JOIN Alquileres ON Películas.id_pelicula = Alquileres.id_pelicula " +
                "WHERE fecha_devolucion = ?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {

            if (!connection.isClosed()){
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
                resultSet = preparedStatement.executeQuery();

                if (resultSet != null){
                    System.out.println("---PELÍCULAS QUE TIENEN QUE DEVOLVERSE HOY---");
                    while (resultSet.next()){
                        String idMovie = resultSet.getString("id_pelicula");
                        String titleMovie = resultSet.getString("titulo");
                        String idCustomer = resultSet.getString("id_socio");
                        Date rentDay = resultSet.getDate("fecha_alquiler");
                        Date returnDay = resultSet.getDate("fecha_devolucion");

                        System.out.printf("""
                                Id película: %s
                                Título: %s
                                Id socio: %s
                                Día alquiler: %s
                                Día devolución: %s
                                ****************************
                                """, idMovie, titleMovie, idCustomer, rentDay.toString(), returnDay.toString());
                    }
                }

                assert resultSet != null;
                resultSet.close();
                preparedStatement.close();
            }
        } catch (SQLException e){
            e.fillInStackTrace();
            System.out.println("Algo ha fallado en la consulta");
        }

    }
    public void mostRentedCategoryUpToThirtyYears(){

        String queryBirthDate = "SELECT s.fecha_nacimiento, a.id_socio " +
                "FROM Alquileres a " +
                "LEFT JOIN Socios s ON s.id_socio=a.id_socio";


        String queryGender = "SELECT  p.genero, a.id_socio " +
                "FROM Alquileres a " +
                "LEFT JOIN  Películas p ON p.id_pelicula=a.id_pelicula;";

        PreparedStatement preparedStatement;
        ResultSet resultSetToObtainsBirthdate;
        ResultSet resultSetToObtainsGender;
        String id_customer;
        Gender gender;
        LocalDate birthCustomer;
        final int AGE_TO_CONSULT=30;
        int comedy = 0, drama=0, scienceFiction=0, terror=0, thiller=0, othersGenres=0;

        try {
            if (!connection.isClosed()){
                preparedStatement = connection.prepareStatement(queryBirthDate);
                resultSetToObtainsBirthdate = preparedStatement.executeQuery();

                if (resultSetToObtainsBirthdate != null){
                    while (resultSetToObtainsBirthdate.next()){

                        id_customer = resultSetToObtainsBirthdate.getString("id_socio");

                         //Obtengo la edad de los socios que alquilan la película y se la resto a la fecha actual
                         //para obtener la diferencia de años
                        birthCustomer = resultSetToObtainsBirthdate.getDate("fecha_nacimiento").toLocalDate();
                        LocalDate checkAge = LocalDate.now().minusYears(birthCustomer.getYear())
                                .minusMonths(birthCustomer.getMonthValue()).minusDays(birthCustomer.getDayOfMonth());

                        //Estraigo el año con el método .getYear de la resta entre el año actual y la fecha de nacimiento del socio
                        if (checkAge.getYear()<AGE_TO_CONSULT){
                            //Vuelvo a usar el prepareStament para ejecutar la segunda query.
                            //Uso de dos ResultSet diferentes
                            preparedStatement = connection.prepareStatement(queryGender);
                            resultSetToObtainsGender = preparedStatement.executeQuery();

                            if (resultSetToObtainsGender!= null){
                                while (resultSetToObtainsGender.next()){
                                    //Uso el id del socio para hacer
                                    if (resultSetToObtainsGender.getString("id_socio").equals(id_customer)){
                                        gender = Gender.valueOf(resultSetToObtainsGender.getString("genero"));

                                        switch (gender){
                                            case Gender.COMEDY -> comedy++;
                                            case Gender.DRAMA -> drama++;
                                            case Gender.SCIENCE_FICTION -> scienceFiction++;
                                            case Gender.TERROR -> terror++;
                                            case Gender.THRILLER -> thiller++;
                                            case Gender.OTHER_GENRES -> othersGenres++;
                                        }
                                        break;
                                    }
                                }
                            }

                        }
                    }
                }

                System.out.printf("""
                        Comedia %d
                        Drama %d
                        Ciencia Ficción %d
                        Terror %d
                        Thiller %d
                        Otros géneros %d
                        """, comedy, drama, scienceFiction, terror, thiller, othersGenres);
            }
        }catch (SQLException e){
            e.fillInStackTrace();
            System.out.println("No se ha podido consultar los generos más alquilados");
        }
    }
    public void mostRentedCategoryBetween30And55(){

        String queryBirthDate = "SELECT s.fecha_nacimiento, a.id_socio " +
                "FROM Alquileres a " +
                "LEFT JOIN Socios s ON s.id_socio=a.id_socio";


        String queryGender = "SELECT  p.genero, a.id_socio " +
                "FROM Alquileres a " +
                "LEFT JOIN  Películas p ON p.id_pelicula=a.id_pelicula;";

        PreparedStatement preparedStatement;
        ResultSet resultSetToObtainsBirthdate;
        ResultSet resultSetToObtainsGender;
        String id_customer;
        Gender gender;
        LocalDate birthCustomer;
        final int AGE_MIN=30;
        final int AGE_MAX=55;
        int comedy = 0, drama=0, scienceFiction=0, terror=0, thiller=0, othersGenres=0;

        try {
            if (!connection.isClosed()){
                preparedStatement = connection.prepareStatement(queryBirthDate);
                resultSetToObtainsBirthdate = preparedStatement.executeQuery();

                if (resultSetToObtainsBirthdate != null){
                    while (resultSetToObtainsBirthdate.next()){

                        id_customer = resultSetToObtainsBirthdate.getString("id_socio");

                        //Obtengo la edad de los socios que alquilan la película y se la resto a la fecha actual
                        //para obtener la diferencia de años
                        birthCustomer = resultSetToObtainsBirthdate.getDate("fecha_nacimiento").toLocalDate();
                        LocalDate checkAge = LocalDate.now().minusYears(birthCustomer.getYear())
                                .minusMonths(birthCustomer.getMonthValue()).minusDays(birthCustomer.getDayOfMonth());

                        //Estraigo el año con el método .getYear de la resta entre el año actual y la fecha de nacimiento del socio
                        if (checkAge.getYear()>=AGE_MIN && checkAge.getYear()<=AGE_MAX){
                            //Vuelvo a usar el prepareStament para ejecutar la segunda query.
                            //Uso de dos ResultSet diferentes
                            preparedStatement = connection.prepareStatement(queryGender);
                            resultSetToObtainsGender = preparedStatement.executeQuery();

                            if (resultSetToObtainsGender!= null){
                                while (resultSetToObtainsGender.next()){
                                    //Uso el id del socio para hacer
                                    if (resultSetToObtainsGender.getString("id_socio").equals(id_customer)){
                                        gender = Gender.valueOf(resultSetToObtainsGender.getString("genero"));

                                        switch (gender){
                                            case Gender.COMEDY -> comedy++;
                                            case Gender.DRAMA -> drama++;
                                            case Gender.SCIENCE_FICTION -> scienceFiction++;
                                            case Gender.TERROR -> terror++;
                                            case Gender.THRILLER -> thiller++;
                                            case Gender.OTHER_GENRES -> othersGenres++;
                                        }
                                        break;
                                    }
                                }
                            }

                        }
                    }
                }

                System.out.printf("""
                        Comedia %d
                        Drama %d
                        Ciencia Ficción %d
                        Terror %d
                        Thiller %d
                        Otros géneros %d
                        """, comedy, drama, scienceFiction, terror, thiller, othersGenres);
            }
        }catch (SQLException e){
            e.fillInStackTrace();
            System.out.println("No se ha podido consultar los generos más alquilados");
        }
    }
}
