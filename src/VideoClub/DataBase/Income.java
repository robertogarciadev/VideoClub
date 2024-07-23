package VideoClub.DataBase;

import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

public class Income {
    private final int FIRST_DAY_MONTH =1;
    public void january(Connection connection) throws SQLException{

        String query = "SELECT precio_total_alquiler FROM Alquileres WHERE fecha_alquiler>=? AND fecha_alquiler <=? ";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        double total_rents=0;

        try {
            if (!connection.isClosed()){
                preparedStatement = connection.prepareStatement(query);
                LocalDate firstDayMonth = LocalDate.of(Integer.parseInt(String.valueOf(Year.now())), Month.JANUARY, FIRST_DAY_MONTH);
                LocalDate lastDayMonth = LocalDate.of(Integer.parseInt(String.valueOf(Year.now())), Month.JANUARY, Month.JANUARY.maxLength());
                preparedStatement.setDate(1, Date.valueOf(firstDayMonth));
                preparedStatement.setDate(2, Date.valueOf(lastDayMonth));
                resultSet = preparedStatement.executeQuery();
                if (resultSet!= null){
                    while (resultSet.next()){
                        total_rents += resultSet.getDouble("precio_total_alquiler");
                    }
                }
                assert resultSet != null;
                resultSet.close();
                preparedStatement.close();
            }

        } catch (SQLException e ){
            e.fillInStackTrace();
        }finally {
            System.out.printf("La caja del mes de %s es de %.2f"+'\n',Month.JANUARY, total_rents);
        }
    }
    public void february(Connection connection) throws SQLException{

        String query = "SELECT precio_total_alquiler FROM Alquileres WHERE fecha_alquiler>=? AND fecha_alquiler <=? ";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        double total_rents=0;

        try {
            if (!connection.isClosed()){
                preparedStatement = connection.prepareStatement(query);
                LocalDate firstDayMonth = LocalDate.of(Integer.parseInt(String.valueOf(Year.now())), Month.FEBRUARY, FIRST_DAY_MONTH);
                LocalDate lastDayMonth = LocalDate.of(Integer.parseInt(String.valueOf(Year.now())), Month.FEBRUARY, Month.FEBRUARY.maxLength());
                preparedStatement.setDate(1, Date.valueOf(firstDayMonth));
                preparedStatement.setDate(2, Date.valueOf(lastDayMonth));
                resultSet = preparedStatement.executeQuery();
                if (resultSet!= null){
                    while (resultSet.next()){
                        total_rents += resultSet.getDouble("precio_total_alquiler");
                    }
                }
                assert resultSet != null;
                resultSet.close();
                preparedStatement.close();
            }

        } catch (SQLException e ){
            e.fillInStackTrace();
        }finally {
            System.out.printf("La caja del mes de %s es de %.2f"+'\n',Month.FEBRUARY, total_rents);
        }
    }
    public void march(Connection connection) throws SQLException{

        String query = "SELECT precio_total_alquiler FROM Alquileres WHERE fecha_alquiler>=? AND fecha_alquiler <=? ";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        double total_rents=0;

        try {
            if (!connection.isClosed()){
                preparedStatement = connection.prepareStatement(query);
                LocalDate firstDayMonth = LocalDate.of(Integer.parseInt(String.valueOf(Year.now())), Month.MARCH, FIRST_DAY_MONTH);
                LocalDate lastDayMonth = LocalDate.of(Integer.parseInt(String.valueOf(Year.now())), Month.MARCH, Month.MARCH.maxLength());

                preparedStatement.setDate(1, Date.valueOf(firstDayMonth));
                preparedStatement.setDate(2, Date.valueOf(lastDayMonth));
                resultSet = preparedStatement.executeQuery();
                if (resultSet!= null){
                    while (resultSet.next()){
                        total_rents += resultSet.getDouble("precio_total_alquiler");
                    }
                }
                assert resultSet != null;
                resultSet.close();
                preparedStatement.close();
            }

        } catch (SQLException e ){
            e.fillInStackTrace();
        }finally {
            System.out.printf("La caja del mes de %s es de %.2f"+'\n',Month.MARCH, total_rents);
        }
    }
    public void april(Connection connection) throws SQLException{

        String query = "SELECT precio_total_alquiler FROM Alquileres WHERE fecha_alquiler>=? AND fecha_alquiler <=? ";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        double total_rents=0;

        try {
            if (!connection.isClosed()){
                preparedStatement = connection.prepareStatement(query);
                LocalDate firstDayMonth = LocalDate.of(Integer.parseInt(String.valueOf(Year.now())), Month.APRIL, FIRST_DAY_MONTH);
                LocalDate lastDayMonth = LocalDate.of(Integer.parseInt(String.valueOf(Year.now())), Month.APRIL, Month.APRIL.maxLength());

                preparedStatement.setDate(1, Date.valueOf(firstDayMonth));
                preparedStatement.setDate(2, Date.valueOf(lastDayMonth));
                resultSet = preparedStatement.executeQuery();
                if (resultSet!= null){
                    while (resultSet.next()){
                        total_rents += resultSet.getDouble("precio_total_alquiler");
                    }
                }
                assert resultSet != null;
                resultSet.close();
                preparedStatement.close();
            }

        } catch (SQLException e ){
            e.fillInStackTrace();
        }finally {
            System.out.printf("La caja del mes de %s es de %.2f"+'\n',Month.APRIL, total_rents);
        }
    }
    public void may(Connection connection) throws SQLException{

        String query = "SELECT precio_total_alquiler FROM Alquileres WHERE fecha_alquiler>=? AND fecha_alquiler <=? ";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        double total_rents=0;

        try {
            if (!connection.isClosed()){
                preparedStatement = connection.prepareStatement(query);
                LocalDate firstDayMonth = LocalDate.of(Integer.parseInt(String.valueOf(Year.now())), Month.MAY, FIRST_DAY_MONTH);
                LocalDate lastDayMonth = LocalDate.of(Integer.parseInt(String.valueOf(Year.now())), Month.MAY, Month.MAY.maxLength());

                preparedStatement.setDate(1, Date.valueOf(firstDayMonth));
                preparedStatement.setDate(2, Date.valueOf(lastDayMonth));
                resultSet = preparedStatement.executeQuery();
                if (resultSet!= null){
                    while (resultSet.next()){
                        total_rents += resultSet.getDouble("precio_total_alquiler");
                    }
                }
                assert resultSet != null;
                resultSet.close();
                preparedStatement.close();
            }

        } catch (SQLException e ){
            e.fillInStackTrace();
        }finally {
            System.out.printf("La caja del mes de %s es de %.2f"+'\n',Month.MAY, total_rents);
        }
    }
    public void june(Connection connection) throws SQLException{

        String query = "SELECT precio_total_alquiler FROM Alquileres WHERE fecha_alquiler>=? AND fecha_alquiler <=? ";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        double total_rents=0;

        try {
            if (!connection.isClosed()){
                preparedStatement = connection.prepareStatement(query);
                LocalDate firstDayMonth = LocalDate.of(Integer.parseInt(String.valueOf(Year.now())), Month.JUNE, FIRST_DAY_MONTH);
                LocalDate lastDayMonth = LocalDate.of(Integer.parseInt(String.valueOf(Year.now())), Month.JUNE, Month.JUNE.maxLength());

                preparedStatement.setDate(1, Date.valueOf(firstDayMonth));
                preparedStatement.setDate(2, Date.valueOf(lastDayMonth));
                resultSet = preparedStatement.executeQuery();
                if (resultSet!= null){
                    while (resultSet.next()){
                        total_rents += resultSet.getDouble("precio_total_alquiler");
                    }
                }
                assert resultSet != null;
                resultSet.close();
                preparedStatement.close();
            }

        } catch (SQLException e ){
            e.fillInStackTrace();
        }finally {
            System.out.printf("La caja del mes de %s es de %.2f"+'\n',Month.JUNE, total_rents);
        }
    }
    public void july(Connection connection) throws SQLException{

        String query = "SELECT precio_total_alquiler FROM Alquileres WHERE fecha_alquiler>=? AND fecha_alquiler <=? ";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        double total_rents=0;

        try {
            if (!connection.isClosed()){
                preparedStatement = connection.prepareStatement(query);
                LocalDate firstDayMonth = LocalDate.of(Integer.parseInt(String.valueOf(Year.now())), Month.JULY, FIRST_DAY_MONTH);
                LocalDate lastDayMonth = LocalDate.of(Integer.parseInt(String.valueOf(Year.now())), Month.JULY, Month.JULY.maxLength());

                preparedStatement.setDate(1, Date.valueOf(firstDayMonth));
                preparedStatement.setDate(2, Date.valueOf(lastDayMonth));
                resultSet = preparedStatement.executeQuery();
                if (resultSet!= null){
                    while (resultSet.next()){
                        total_rents += resultSet.getDouble("precio_total_alquiler");
                    }
                }
                assert resultSet != null;
                resultSet.close();
                preparedStatement.close();
            }

        } catch (SQLException e ){
            e.fillInStackTrace();
        }finally {
            System.out.printf("La caja del mes de %s es de %.2f"+'\n',Month.JULY, total_rents);
        }
    }
    public void august(Connection connection) throws SQLException{

        String query = "SELECT precio_total_alquiler FROM Alquileres WHERE fecha_alquiler>=? AND fecha_alquiler <=? ";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        double total_rents=0;

        try {
            if (!connection.isClosed()){
                preparedStatement = connection.prepareStatement(query);
                LocalDate firstDayMonth = LocalDate.of(Integer.parseInt(String.valueOf(Year.now())), Month.AUGUST, FIRST_DAY_MONTH);
                LocalDate lastDayMonth = LocalDate.of(Integer.parseInt(String.valueOf(Year.now())), Month.AUGUST, Month.AUGUST.maxLength());

                preparedStatement.setDate(1, Date.valueOf(firstDayMonth));
                preparedStatement.setDate(2, Date.valueOf(lastDayMonth));
                resultSet = preparedStatement.executeQuery();
                if (resultSet!= null){
                    while (resultSet.next()){
                        total_rents += resultSet.getDouble("precio_total_alquiler");
                    }
                }
                assert resultSet != null;
                resultSet.close();
                preparedStatement.close();
            }

        } catch (SQLException e ){
            e.fillInStackTrace();
        }finally {
            System.out.printf("La caja del mes de %s es de %.2f"+'\n',Month.AUGUST, total_rents);
        }
    }
    public void september(Connection connection) throws SQLException{

        String query = "SELECT precio_total_alquiler FROM Alquileres WHERE fecha_alquiler>=? AND fecha_alquiler <=? ";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        double total_rents=0;

        try {
            if (!connection.isClosed()){
                preparedStatement = connection.prepareStatement(query);
                LocalDate firstDayMonth = LocalDate.of(Integer.parseInt(String.valueOf(Year.now())), Month.SEPTEMBER, FIRST_DAY_MONTH);
                LocalDate lastDayMonth = LocalDate.of(Integer.parseInt(String.valueOf(Year.now())), Month.SEPTEMBER, Month.SEPTEMBER.maxLength());

                preparedStatement.setDate(1, Date.valueOf(firstDayMonth));
                preparedStatement.setDate(2, Date.valueOf(lastDayMonth));
                resultSet = preparedStatement.executeQuery();
                if (resultSet!= null){
                    while (resultSet.next()){
                        total_rents += resultSet.getDouble("precio_total_alquiler");
                    }
                }
                assert resultSet != null;
                resultSet.close();
                preparedStatement.close();
            }

        } catch (SQLException e ){
            e.fillInStackTrace();
        }finally {
            System.out.printf("La caja del mes de %s es de %.2f"+'\n',Month.SEPTEMBER, total_rents);
        }
    }
    public void october(Connection connection) throws SQLException{

        String query = "SELECT precio_total_alquiler FROM Alquileres WHERE fecha_alquiler>=? AND fecha_alquiler <=? ";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        double total_rents=0;

        try {
            if (!connection.isClosed()){
                preparedStatement = connection.prepareStatement(query);
                LocalDate firstDayMonth = LocalDate.of(Integer.parseInt(String.valueOf(Year.now())), Month.OCTOBER, FIRST_DAY_MONTH);
                LocalDate lastDayMonth = LocalDate.of(Integer.parseInt(String.valueOf(Year.now())), Month.OCTOBER, Month.OCTOBER.maxLength());

                preparedStatement.setDate(1, Date.valueOf(firstDayMonth));
                preparedStatement.setDate(2, Date.valueOf(lastDayMonth));
                resultSet = preparedStatement.executeQuery();
                if (resultSet!= null){
                    while (resultSet.next()){
                        total_rents += resultSet.getDouble("precio_total_alquiler");
                    }
                }
                assert resultSet != null;
                resultSet.close();
                preparedStatement.close();
            }

        } catch (SQLException e ){
            e.fillInStackTrace();
        }finally {
            System.out.printf("La caja del mes de %s es de %.2f"+'\n',Month.OCTOBER, total_rents);
        }
    }
    public void november(Connection connection) throws SQLException{

        String query = "SELECT precio_total_alquiler FROM Alquileres WHERE fecha_alquiler>=? AND fecha_alquiler <=? ";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        double total_rents=0;

        try {
            if (!connection.isClosed()){
                preparedStatement = connection.prepareStatement(query);
                LocalDate firstDayMonth = LocalDate.of(Integer.parseInt(String.valueOf(Year.now())), Month.NOVEMBER, FIRST_DAY_MONTH);
                LocalDate lastDayMonth = LocalDate.of(Integer.parseInt(String.valueOf(Year.now())), Month.NOVEMBER, Month.NOVEMBER.maxLength());

                preparedStatement.setDate(1, Date.valueOf(firstDayMonth));
                preparedStatement.setDate(2, Date.valueOf(lastDayMonth));
                resultSet = preparedStatement.executeQuery();
                if (resultSet!= null){
                    while (resultSet.next()){
                        total_rents += resultSet.getDouble("precio_total_alquiler");
                    }
                }
                assert resultSet != null;
                resultSet.close();
                preparedStatement.close();
            }

        } catch (SQLException e ){
            e.fillInStackTrace();
        }finally {
            System.out.printf("La caja del mes de %s es de %.2f"+'\n',Month.NOVEMBER, total_rents);

        }
    }
    public void december(Connection connection) throws SQLException {

        String query = "SELECT precio_total_alquiler FROM Alquileres WHERE fecha_alquiler>=? AND fecha_alquiler <=? ";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        double total_rents=0;

        try {
            if (!connection.isClosed()){
                preparedStatement = connection.prepareStatement(query);
                LocalDate firstDayMonth = LocalDate.of(Integer.parseInt(String.valueOf(Year.now())), Month.DECEMBER, 1);
                LocalDate lastDayMonth = LocalDate.of(Integer.parseInt(String.valueOf(Year.now())), Month.DECEMBER, Month.DECEMBER.maxLength());

                preparedStatement.setDate(1, Date.valueOf(firstDayMonth));
                preparedStatement.setDate(2, Date.valueOf(lastDayMonth));
                resultSet = preparedStatement.executeQuery();
                if (resultSet!= null){
                    while (resultSet.next()){
                        total_rents += resultSet.getDouble("precio_total_alquiler");
                    }
                }
                assert resultSet != null;
                resultSet.close();
                preparedStatement.close();
            }

        } catch (SQLException e ){
            e.fillInStackTrace();
        }finally {
            System.out.printf("La caja del mes de %s es de %.2f"+'\n',Month.DECEMBER, total_rents);
        }
    }
}
