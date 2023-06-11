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
        if (mode.equals("1")) {
            System.out.println("관리자 모드로 접속했습니다");
            while(true){
                System.out.println("1:시사회 넣기 2:영화관 넣기 3:시사회 영화관 연결시키기 4:예약명단 확인하기 5:처음으로");
                String adminMode=sc.nextLine();
                switch (adminMode){
                    case "1":
                        //todo preview_insert
                        System.out.printf("영화이름 = ");
                        String movieName=sc.nextLine();
                        System.out.printf("상영일짜 = ");
                        String dateOfPreview=sc.nextLine();
                        as.previewInsert(movieName,dateOfPreview);
                        break;
                    case "2":
                        //todo cinema_insert
                        System.out.println("영화관 이름 = ");
                        String cinemaName=sc.nextLine();
                        System.out.println("영화관 주소 = ");
                        String address=sc.nextLine();
                        System.out.println("총좌석수 = ");
                        String numChair=sc.nextLine();
                        as.cinemaInsert(cinemaName,address,numChair);
                        break;
                    case "3":
                        //todo combine_cinema_preview
                        as.printPreview();
                        as.printCinema();
                        System.out.println("previewId = ");
                        String preiviewId=sc.nextLine();
                        System.out.println("cinemaId = ");
                        String cinemaId=sc.nextLine();
                        as.combineCinemaPreview(preiviewId,cinemaId);
                        break;
                    case "4":
                        //todo get_person
                        as.printPerson();
                        break;
                    case"5":
                        selectMode();
                }
            }
        }
        if(mode.equals("2")){
            System.out.println("유저모드로 접속했습니다");
            System.out.println("1:시사회 정보 확인 2:영화관내 시사회 정보 확인 3:처음으로");
            String firstMode=sc.nextLine();
            if (firstMode.equals("1")){
                us.printPreview();//시사회 정보 출력
                System.out.printf("previewId = ");
                String previewId=sc.nextLine();
                us.relatedCinemaprint(previewId);//선택한 시사회 상영하는 영화관 출력
                System.out.printf("pcId = ");//preview,cinema 연결된 id 입력
                String pcId=sc.nextLine();
                us.printChair(pcId);
                System.out.printf("chairId = ");
                String chairId=sc.nextLine();
                System.out.printf("name = ");
                String name=sc.nextLine();
                System.out.printf("phoneNum = ");
                String phoneNum=sc.nextLine();
                us.changeStatusAndPersonInsert(chairId,name,phoneNum);//좌석 선택
                selectMode();
            }
            else if (firstMode.equals("2")){
                us.printCinema();//영화관 정보 출력
                System.out.printf("cinemaId = ");
                String cinemaId=sc.nextLine();
                us.relatedPreviewPrint(cinemaId);//선택한 영화관에서 상영하는 시사화 출력
                System.out.printf("pcId = ");//preview,cinema 연결된 id 입력
                String pcId=sc.nextLine();
                us.printChair(pcId);
                System.out.printf("chairId = ");
                String chairId=sc.nextLine();
                System.out.printf("name = ");
                String name=sc.nextLine();
                System.out.printf("phoneNum = ");
                String phoneNum=sc.nextLine();
                us.changeStatusAndPersonInsert(chairId,name,phoneNum);//좌석 선택
                selectMode();
            }else {
                selectMode();
            }
        }

    }

}
