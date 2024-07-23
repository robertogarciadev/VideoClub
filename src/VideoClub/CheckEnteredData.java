package VideoClub;

import VideoClub.Enum.Gender;
import VideoClub.Enum.State;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

//CLASE QUE CONTIENE TODAS LAS COMPROBACIONES DE DATOS QUE SE HARÁN POR CONSOLA. USO DE EXPRESIONES REGULARES.
public class CheckEnteredData {

    public static String customerCheckName(){

        Scanner sc = new Scanner(System.in);
        String name = null;
        boolean checkName= false;

            //Uso del while para repetir acción en el caso de introducir datos erróneos
            while(!checkName){
                System.out.println("Introduce el nombre del socio. Mínimo tres letras, la primera en mayúsculas");
                name = sc.nextLine();
                checkName= name.matches("^([A-Z][a-z]{2,10})$");
                    if (!checkName){
                        System.out.println("Formato del nombre no válido.");
                    }
            }

        return name;
    }
    public static String customerCheckSecondName(){

        Scanner sc = new Scanner(System.in);
        String name = null;
        boolean checkSecondName = false;

            //Uso del While para repetir acción en en caso de introducir datos erróneos
            while (!checkSecondName){
                System.out.println("Introduce el apellido. Mínimo tres letras, la primera en mayúsculas.");
                name = sc.nextLine();
                checkSecondName= name.matches("^([A-Z][a-z]{2,10})$");
                    if (!checkSecondName){
                        System.out.println("Formato del apellido no válido");
                    }
            }

        return name;
    }
    public static long customerCheckPhoneNumber(){

        Scanner sc = new Scanner(System.in);
        String phoneNumber = null;
        boolean checkPhoneNumber=false;

           while (!checkPhoneNumber){

               System.out.println("Introduce un teléfono de contacto");
               phoneNumber = sc.nextLine();
               checkPhoneNumber = phoneNumber.matches("^([6,9][0-9]{8})$");
                if (!checkPhoneNumber){
                    System.out.println("Formato del teléfono no válido. Prueba otra vez");
                }
           }

       return Long.parseLong(phoneNumber);

    }
    public static LocalDate customerCheckBirthDate(){

        Scanner sc = new Scanner(System.in);
        String birthDate=null;
        boolean checkBirthDate=false;

        while (!checkBirthDate){
            System.out.println("Introduce la fecha de nacimiento. Formato yyyy-mm-dd");
            birthDate = sc.nextLine();
            checkBirthDate = birthDate.matches("^(19|20)(((([02468][048])|([13579][26]))-02-29)|(\\d{2})-((02-((0[1-9])|1\\d|2[0-8]))|((((0[13456789])|1[012]))-((0[1-9])|((1|2)\\d)|30))|(((0[13578])|(1[02]))-31)))$");
                if (!checkBirthDate){
                    System.out.println("Formato no válido.");
                }
        }

        return LocalDate.parse(birthDate);
    }
    public  static boolean confirmData(){

        boolean confirmData = false;
        System.out.println("Son correctos los datos? Y/N");
        Scanner sc = new Scanner(System.in);
        String data = sc.nextLine().toLowerCase();
        String yes ="y";

        if (data.equals(yes)){
            confirmData=true;
        }

        return confirmData;
    }
    public static String movieCheckTitle(){
        Scanner sc = new Scanner(System.in);
        String nameMovie = null;
        boolean checkNameMovie= false;

        while (!checkNameMovie){
            System.out.println("Introduce el nombre de la película. La primera en mayúsculas");
            nameMovie = sc.nextLine();
            checkNameMovie= nameMovie.matches("^([A-Z])?([a-z]+[ ]?)*$");
                if (!checkNameMovie){
                    System.out.println("Formato de nombre no válido");
                }
        }

        return nameMovie;
    }
    public static int movieCheckYear(){
        Scanner sc = new Scanner(System.in);
        String movieYear = null;
        boolean checkMovieYear = false;

        while (!checkMovieYear){
            System.out.println("Introduce el año de la película");
            movieYear = sc.nextLine();
            checkMovieYear = movieYear.matches("^([1-2][0-9]{3})$");
            if (!checkMovieYear){
                System.out.println("Formato no válido");
            }
        }

            return Integer.parseInt(movieYear);
    }
    public static int movieCheckDuration(){
        Scanner sc = new Scanner(System.in);
        String duration = null;
        boolean checkDurationMovie = false;

            while (!checkDurationMovie){
                System.out.println("Introduce la duración, en minutos, de la película");
                duration = sc.nextLine();
                checkDurationMovie = duration.matches("^([0-9]{3})$");
                if (!checkDurationMovie){
                    System.out.println("Formato no válido");
                }
            }

            return Integer.parseInt(duration);
    }

    public static Gender movieCheckGender(){

            //Uso del método .values() para guardar todos los géneros en un array, recorrerlos y mostrarlos por pantalla
            Gender [] genderMovies = Gender.values();
            Gender gender = null;
            final int DEFAULT_GENDER = genderMovies.length-1;

            System.out.println("Elige un género para la película");
            Scanner scanner = new Scanner(System.in);
            for (int i = 0; i < genderMovies.length; i++) {
                System.out.println(i+1 +". "+ genderMovies[i]);
            }

            //Uso del while para chequear que la opción introducida sea un número
            String option;
            boolean checkGender = false;

                while (!checkGender){
                    option = scanner.nextLine();
                    checkGender = option.matches("^([0-9])+$");

                        if (!checkGender){
                            System.out.println("Formato no válido. Introduce un opción");
                            continue;
                        } else {

                            //Guardo el rango en un boolean para manejarlo mejor en el if
                            boolean rangeNumber = ( Integer.parseInt(option) > 0 && Integer.parseInt(option)<= genderMovies.length);
                            //Si el número introducido se sale de los límites del array se devuelve la última opción por defecto
                            if (!rangeNumber){
                                System.out.println("No existe la opción. Se ha seleccionado <OTROS GÉNEROS> por defecto");
                                gender = genderMovies[DEFAULT_GENDER];
                            } else {
                                gender = genderMovies[Integer.parseInt(option)-1];
                            }
                        }
                }

            return gender;
    }
    public static State stateMovie(){

        System.out.println("En qúe estado se encuentra la película?");
        State[] states = State.values();
        for (int i = 0; i < states.length; i++) {
            System.out.println(i+1+" "+states[i]);
        }
        Scanner sc = new Scanner(System.in);
        String option;
        State state = null;
        boolean checkState = false;

            //Uso del while para elegir una opción que sea un número entre
            while (!checkState){
                option = sc.nextLine();
                    if (!option.matches("^([1,2])$")){
                        System.out.println("Opción no contemplada");
                        for (int i = 0; i < states.length; i++) {
                            System.out.println(i+1+" "+states[i]);
                        }
                    } else {
                        state = states[Integer.parseInt(option)-1];
                        checkState = true;
                    }
            }
            return state;
    }
    public static String searchByTitle(){

        System.out.println("Introduce el título de la película que quieres buscar");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    public static String searchByName(){

        System.out.println("Introduce el nombre del socio que quieras buscar");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    public static String searchBySurname(){

        System.out.println("Introduce el apellido del socio que quieras buscar");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    public static int enterRentalDays() {
        Scanner sc = new Scanner(System.in);
        final int MAX_RENTAL = 5;
        final int MIN_RENTAL = 1;
        System.out.println("De cuántos días será el alquiler?");

        int rentalDays;
        try {
            rentalDays = sc.nextInt();
            if (rentalDays <= 0) {
                rentalDays = MIN_RENTAL;
            } else if (rentalDays > 5) {
                rentalDays = MAX_RENTAL;
            }
        } catch (InputMismatchException e) {
            e.fillInStackTrace();
            rentalDays = MIN_RENTAL;
        }

        return rentalDays;
    }
}
