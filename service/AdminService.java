package service;
import repository.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminService {
    static Repository rp=new Repository();
    public static void previewInsert(String movieName,String dateOfPreview){
        rp.preInsert(movieName,dateOfPreview);
    }

    public static void cinemaInsert(String cinemaName,String address,String numChair){
        rp.cinInsert(cinemaName,address,numChair);
    }

    public static void printCinema(){
        rp.printCinema();
    }

    public static void printPreview(){
        rp.printPreview();
    }
    public static void combineCinemaPreview(String previewId,String cinemaId){
        rp.insertCombinePreview(previewId,cinemaId);
    }
    public static void printPerson(){
        rp.printPerson();
    }


}
