package service;
import repository.Repository;
public class UserService {
    static Repository rp=new Repository();
    public static void printPreview() {
        //printPreview
        rp.printPreview();
    }

    public static void relatedCinemaprint(String previewId) {
        rp.relatedCinemaPrint(previewId);
    }
    public static void printChair(String pcId) {
        rp.printChair(pcId);
    }

    public static void changeStatusAndPersonInsert(String chairId,String name,String phoneNum) {
        rp.changeStatusAndPersonInsert(chairId,name,phoneNum);
    }

    public static void printCinema() {
        rp.printCinema();

    }

    public static  void relatedPreviewPrint(String cinemaId){
        rp.relatedPreviewPrint(cinemaId);
    }

}
