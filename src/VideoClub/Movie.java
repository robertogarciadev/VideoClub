package VideoClub;

import VideoClub.Enum.Gender;
import VideoClub.Enum.State;

import java.time.format.DateTimeFormatter;

public class Movie {

    private String title, id_movie;
    private int year, duration;
    private Gender gender;
    private State state;


    //El precio de alquiler por día será un atributo de clase (CONSTANTE)


    public Movie(String title, int year, int duration, Gender gender, State state){
        int num = (int) (Math.random()*1000);
        this.id_movie = String.format("%04d", num);
        this.title= title;
        this.year= year;
        this.duration=duration;
        this.gender= gender;
        this.state= state;
    }


    public Movie(String id_movie,String title, int year, int duration, Gender gender, State state) {

        this.id_movie = id_movie;
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.gender = gender;
        this.state = state;
    }

    public static   Movie createMovie(){

        String title = CheckEnteredData.movieCheckTitle();
        int year = CheckEnteredData.movieCheckYear();
        int duration = CheckEnteredData.movieCheckDuration();
        Gender gender = CheckEnteredData.movieCheckGender();
        State state = CheckEnteredData.stateMovie();

        return new  Movie(title, year, duration, gender, state);

    }


    public String getInfoMovie(){
        return "Id Película: " + id_movie + '\n'+
                "Título: " + title + '\n'+
                "Año: " + year + '\n'+
                "Duración: " + duration +'\n'+
                "Género: " + gender+'\n'+
                "Estado: "+ state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId_movie() {
        return id_movie;
    }

    public void setId_movie(String id_movie) {
        this.id_movie = id_movie;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
