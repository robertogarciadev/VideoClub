package VideoClub;

import com.sun.tools.javac.Main;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;


public class Customer {


    private String name, secondName, id_customer;
    private long phoneNumber;
    private LocalDate birthDate;
    public Customer(){}
    public Customer(String name, String secondName, long phoneNumber, LocalDate birthDate){

        int num = (int)(Math.random()*1000);
        this.id_customer = String.format("%04d", num);
        this.name= name;
        this.secondName= secondName;
        this.phoneNumber = phoneNumber;
        this.birthDate= birthDate;
    }

    public Customer(String id_customer, String name, String secondName, long phoneNumber, LocalDate birthDate) {
        this.id_customer = id_customer;
        this.name = name;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    public String getId_customer() {
        return id_customer;
    }

    public void setId_customer(String id_customer) {
        this.id_customer = id_customer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }


    public String getInfoCustomer(){

        return  "Id Cliente: " + id_customer + '\n'+
                "Nombre: " + name + '\n'+
                "Apellidos: " + secondName + '\n'+
                "Teléfono: " + phoneNumber +'\n'+
                "Fecha de nacimiento: " + birthDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    }

    public static Customer createCustomer(){

        //Uso de métodos para todos los datos por consola
        String name =  CheckEnteredData.customerCheckName();
        String secondName = CheckEnteredData.customerCheckSecondName();
        long phoneNumber = CheckEnteredData.customerCheckPhoneNumber();
        LocalDate birthDate = CheckEnteredData.customerCheckBirthDate();
        Customer customer = new Customer(name, secondName, phoneNumber, birthDate);
        System.out.println(customer.getInfoCustomer());

        return customer;
    }

}
