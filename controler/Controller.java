package controler;
import service.AdminService;
import java.util.Scanner;
import service.UserService;
//입력받은거 분배하는 컨트롤러
public class Controller {
    Scanner sc=new Scanner(System.in);
    UserService us=new UserService();
    AdminService as=new AdminService();
    public  void selectMode(){
        System.out.println("1:어드민 접속 2:유저 접속\n입력 = ");
        String mode=sc.nextLine();
        if (mode=="1") {
            System.out.println("관리자 모드로 접속했습니다");
            while(true){
                System.out.println("1:시사회 넣기 2:영화관 넣기 3:시사회 영화관 연결시키기 4:예약명단 확인하기 5:처음으로");
                String adminMode=sc.nextLine();
                switch (adminMode){
                    case "1":
                        //todo preview_insert
                        String movieName=sc.nextLine();
                        String dateOfPreview=sc.nextLine();
                        as.previewInsert(movieName,dateOfPreview);
                        break;
                    case "2":
                        //todo cinema_insert
                        String cinemaName=sc.nextLine();
                        String address=sc.nextLine();
                        String numChair=sc.nextLine();
                        as.cinemaInsert(cinemaName,address,numChair);
                        break;
                    case "3":
                        //todo combine_cinema_preview
                        as.printPreview();
                        as.printCinema();
                        String preiviewId=sc.nextLine();
                        String cinemaId=sc.nextLine();
                        as.combineCinemaPreview(preiviewId,cinemaId);
                        break;
                    case "4":
                        //todo get_person
                        as.getPerson();
                        break;
                    case"5":
                        selectMode();
                }
            }
        }
        if(mode=="2"){
            System.out.println("유저모드로 접속했습니다");
            System.out.println("1:시사회 정보 확인 2:영화관내 시사회 정보 확인");
            String firstMode=sc.nextLine();
            if (firstMode=="1"){
                int previewId=us.getPreview();//시사회 정보 출력하고, 선택한 영화관 아이디 반환
                int cinemaId=us.getCinema(previewId);
                us.getChair(cinemaId);
                selectMode();
            }
            else if (firstMode=="2"){
                int cinemaId=us.getCinema();//시사회 정보 출력하고, 선택한 영화관 아이디 반환
                int previewId=us.getPreview(cinemaId);
                us.getChair(cinemaId);
                selectMode();
            }
        }

    }

}
