package view;

import model.LoginList;
import model.Player;
import model.PlayerController;
import model.Shop;


import java.util.Arrays;
import java.util.Scanner;

public class PlayMenu {
    //스캐너 설정
    Scanner sc = new Scanner(System.in);
    public Shop shop = new Shop();
    PlayerController playerController = new PlayerController();
    LoginList lgl = new LoginList();
    Player[] player = playerController.showPlayer();////////
    Player myPlayer= player[0];/////////////////

    /////////////// 필드 ///////////////


    /////////////// 생성자 ///////////////


    /////////////// 메소드 ///////////////



    //전체 실행 메소드
    public void mainMenu() {
        //로그인 기능 호출
        loginAccess();

        // 전체 메뉴
        while (true) {

            System.out.println("\n========= Item Upgrade Game=========");
            System.out.println("1. 플레이어 인벤토리");
            System.out.println("2. 아이템 상점");
            System.out.println("3. 아이템 강화");
            System.out.println("4. 플레이어 강화 목록");
            System.out.print("5. 프로그램 종료\n");
            System.out.println("====================================");
            System.out.print("# 메뉴 번호: ");
            int menuNum = sc.nextInt();

            switch (menuNum) {
                case 1: {
                    playerInventoryMenu();
                    break;
                }

                case 2: {
                    itemShopMenu();
                    break;
                }

                case 3: {
                    itemUpgradeMenu();
                    break;
                }

                case 4: {
                    playerUpgradeListMenu();
                    break;
                }

                case 5: {
                    System.out.println("\n프로그램을 종료합니다.");
                    System.exit(0); //프로그램 종료
                    break;
                }

                default: {
                    System.out.println("\n잘못 입력하셨습니다!!!");
                    System.out.println("다시 입력해주시기 바랍니다.");
                }
            }
        }

    }// end mainMenu

    //////////////////////////////////////// 로그인 메소드  /////////////////////////////////////////
    public void loginAccess() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=====================================");
            System.out.println("\n+++++++++Item Upgrade Program++++++++\n");
            System.out.println("=====================================");
            System.out.println("\n----------로그인----------");
            System.out.println("\n아이디를 입력 하세요.");
            System.out.print(">> ");
            String id = sc.next();
            System.out.println("비밀번호를 입력 하세요.");
            System.out.print(">> ");
            String pw = sc.next();


            if (id.equals(lgl.id1) && pw.equals(lgl.pw1)) {
                System.out.println("\n정상적으로 로그인이 완료되었습니다.\n");
                System.out.println("나는야 강화왕님 안녕하세요!!");

                break;
            } else if (id.equals(lgl.id2) && pw.equals(lgl.pw2)) {
                System.out.println("\n정상적으로 로그인이 완료되었습니다.\n");

                System.out.println("나는야 강화신님 안녕하세요!!");

                break;
            } else if (id.equals(lgl.id3) && pw.equals(lgl.pw3)) {
                System.out.println("\n정상적으로 로그인이 완료되었습니다.\n");
                System.out.println("나는야 강화꾼님 안녕하세요!!");
                break;
            } else {
                System.out.println("\n아이디 혹은 비밀번호가 틀렸습니다!!");
                System.out.println("\n다시 입력해주시기 바랍니다.");
            }
        }
    }//end loginAccess


    ///////////////////////////////////////// 전체 메뉴 메소드  /////////////////////////////////////////


    /////////// 1. 플레이어 인벤토리 메뉴 ///////////////
    public void playerInventoryMenu() {
        myPlayer.inventoryView();

    }// end playerInventoryMenu()


    /////////2. 아이템 상점 메뉴 /////////////////////
    public void itemShopMenu() {
        System.out.println("\n************* Item Shop *************");
        System.out.println("\n구매하고자 하는 아이템의 번호를 입력하세요.");
        System.out.println("[1. 무기], [2. 방어구], [3. 장신구]");
        System.out.print(">> ");
        int selectItemNo = sc.nextInt();
        sc.nextLine();//밑에 nextLine 자동으로 넘어가는 현상 막기위해 작성

        switch (selectItemNo) {
            case 1://무기목록 출력
                choiceSubject();
                break;
            case 2://방어구목록 출력
                System.out.println("\n<기본갑옷: 2000원>   <보통갑옷: 5000원>   <고급갑옷: 10000원>");
                break;
            case 3://장신구목록 출력
                System.out.println("\n<목걸이: 8000원>   <반지: 8000원>   <팔찌: 8000원>");
                break;
            default:
                System.out.println("잘못 입력하셨습니다.");
                System.out.println("처음부터 다시 입력해주시기 바랍니다!!");
        }

        System.out.println();
        System.out.println("\n 구매하고 싶은 아이템의 이름을 입력하세요.");
        System.out.print(">> ");
        String itemName = sc.nextLine();//replace(" ","") 공백문자열 제거//근데 여기서는 필요없음..

        System.out.println("선택한 아이템: " + itemName);

        shop.buyItem(itemName, myPlayer);


    }



    //---------------------------shop 서브메소드-------------------------------------------//
    private void choiceSubject() {///무기목록 출력 메소드
        System.out.println("\n직업을 선택해주세요!!");
        System.out.println("[1. 전사], [2. 마법사], [3. 궁수]");
        System.out.print(">> ");
        int jobChoice = sc.nextInt();
        sc.nextLine();
        if (jobChoice == 1) {
            System.out.println("\n<기본검: 2000원>   <보통검: 5000원>   <고급검: 10000원>");
            return;
        } else if (jobChoice == 2) {
            System.out.println("\n<기본지팡이: 2000원>   <보통지팡이: 5000원>   <고급지팡이: 10000원>");
            return;
        } else if (jobChoice == 3) {
            System.out.println("\n<기본활: 2000원>   <보통활: 5000원>   <고급활: 10000원>");
            return;
        }
    }



    //---------------------------------------end sub shop method-----------------------------------//

    ///////////// 3. 아이템 강화 메뉴 ///////////////
    public void itemUpgradeMenu() {

        System.out.println("");
        myPlayer.upgradeItem();

    }// end  itemUpgradeMenu()

    ///////////// 4. 플레이어 강화 목록 /////////////////
    public void playerUpgradeListMenu() {
        myPlayer.UpgradeListMenu();
    }// end  playerUpgradeListMenu()

}
