package model;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.SplittableRandom;

public class Player {

    Scanner sc = new Scanner(System.in);
    SplittableRandom random = new SplittableRandom();//난수생성기

    /////////////////////////////////////////////////// 필드 //////////////////////////////////////////////////////////

    int idxx = 0; // idx 전역변수

    public String id;
    public String[] inventory = new String[]{"기본검", "보통활", "기본갑옷", "고급 지팡이"};
    public int cost;
    public String upgradeCount = "+"; //강화 성공 할 때 마다 뒤에 붙는 숫자 ex) +1, +2, +3...
    int itemCount = 0;
    public int updateCost = 2000;
    int count = 1;//강화할수록 강화비용 올리는 수 2000->4000->6000 이렇게 되도록
    int upgradePercentCount = 7;

    // 강화 목록에 사용할 필드
    String upgradeSuccess = "강화성공";
    String upgradeFall = "강화실패";
    // 강화 성공이나 실패시 강화성공 여부를 담는 배열
    String[] successOrFall = new String[0];
    // 강화 목록에서 강화아이템을 보여주는 필드
    String selectItem;

    //강화 방어권 카운트
    int doubleCount = 2;

    // 강화 목록에서 사용한 가격을 담는 배열
    int[] costBox = new int[0];

    /////////////////////////////////////////////////// 생성자 //////////////////////////////////////////////////////////
    public Player() {
        cost = 80000;

    }

    public Player(String[] inventory, int cost, String id) {
        this.inventory = inventory;
        this.cost = cost;
    }

    /////////////////////////////////////////////////// get, set ///////////////////////////////////////////////////////
    public String getId() {
        return id;
    }

    /////////////////////////////////////////////////// 메소드 //////////////////////////////////////////////////////////
    //인벤토리를 보여주는 메소드
    public void inventoryView() {

        while (true) {
            System.out.println("\n************* Inventory *************");
            System.out.printf("아이템 목록: %s\n", Arrays.toString(inventory));
            System.out.printf("돈: %d원\n", cost);

            System.out.println("\n[Y]. 나가기");
            System.out.print(">> ");
            String inventoryExit = sc.next();

            if (inventoryExit.equalsIgnoreCase("y")) {
                break;
            } else if (inventoryExit.equalsIgnoreCase("n")) {

            }
        }


    }


    //강화 메소드
    public void upgradeItem() {


        System.out.println("\n************* Upgrade Item *************");
        System.out.println("\n=============== 강화시스템 설명 ===============");
        System.out.println("1강 -  확률: 90%, 비용: 2000원");
        System.out.println("2강 -  확률: 70%, 비용: 4000원");
        System.out.println("3강 -  확률: 50%, 비용: 6000원");
        System.out.println("4강 -  확률: 30%, 비용: 8000원");
        System.out.println("5강 -  확률: 10%, 비용: 10000원");
        System.out.println("극강 -  확률: 5%, 비용: 20000원");
        System.out.println("\n강화 실패 시 아이템이 즉시 없어지므로 주의하시기 바랍니다!!!!");
        System.out.println("강화 방어권은 계정 당 1회로 제한됩니다!!");
        System.out.println("\n=============================================");

        while (true) {
            System.out.println(" ");
            System.out.printf("아이템 목록: %s\n", Arrays.toString(inventory));
            System.out.printf("돈: %d원\n", cost);

            System.out.println("\n강화 할 아이템를 선택해주시기 바랍니다.");
            System.out.print(">> ");
            String upgradeSelectItem = sc.next();
            sc.nextLine();
            selectItem = upgradeSelectItem;

            // 순차 탐색: 탐색성공시 해당데이터의 인덱스, 실패시 -1
            int idx = -1;

            for (int i = 0; i < inventory.length; i++) {
                if (inventory[i].equals(upgradeSelectItem)) {

                    idx = i;
                    idxx = i;
                    break;
                }
            }// end for

            // 수정 여부 결정
            if (idx != -1) {
                System.out.println(" ");
                System.out.println(upgradeSelectItem + "을(를) 강화할 준비가 완료되었습니다.");
                break;

            } else {
                System.out.println("\n다시 입력해주시기 바랍니다!!");
            }
        }

        while (true) {
            System.out.println("\n처음 강화확률: " + upgradePercentCount * 10 + "%");
            System.out.printf("강화 비용은 [%d원]입니다.\n", updateCost);
            System.out.println("강화를 시작합니다.");
            System.out.println("\n엔터를 누르세요!!");
            sc.nextLine();

            if(cost < updateCost * count) {
                System.out.println("\n돈이 부족하여 강화를 실행할 수 없습니다!!!!");
                System.out.println("강화 시스템을 종료합니다...");
                System.out.println("\n메인으로 돌아갑니다.");
                System.out.println("====================================");
                return;
            }

            String answer = "";

            for (int i = 0; i < 10; i++) {//몇 번 반복해야할지 잘 모르겠어서 일단 10으로

                boolean startUpgrade = random.nextInt(1, 101) <= upgradePercentCount-- * 10;//10%씩 줄어들도록 설정
                ///////다른 아이템을 강화하려고 하면 확률이 줄어든 상태에서 시작하는 문제 발생/////////
                //////기존의 아이템 강화시에는 유지하고 새로운 아이템 강화시 확률 초기화 필요/////////
                //////계속 강화시도시 확률이 마이너스가 돼서 에러뜨는 현상///////////

                //강화시 전체 돈에서 2000 * 1을 먼저 계산한 후에 count++해서 다음 강화비용을 올리고 가지고 있는 돈을 차감하는 기능
                int costuper = updateCost * count++;
                cost -= costuper;


                if (startUpgrade) {

                    System.out.println("\n==================================");
                    System.out.println("=             강화 성공           =");
                    System.out.println("==================================");



                    //강화에 성공하면 해당 아이템에 강화성공 표시를 해주는 기능
                    inventory[idxx] += upgradeCount;

                    ///////////////// 4번 메뉴 담는 배열 /////////////////
                    // 강화 성공하면 강화목록에 보여줄 내용을 담는 배열 push
                    String[] temp = new String[successOrFall.length + 1];

                    for (i = 0; i < successOrFall.length; i++) {
                        temp[i] = successOrFall[i];
                    }

                    temp[temp.length - 1] = upgradeSuccess;


                    successOrFall = temp;
                    temp = null;

                    ///////////////// 4번 메뉴 담는 배열 /////////////////
                    // 강화 성공하면 강화목록에 보여줄 금액을 담는 배열 push
                    int[] temp2 = new int[costBox.length + 1];


                    for (i = 0; i < costBox.length; i++) {
                        temp2[i] = costBox[i];
                    }

                    temp2[temp2.length - 1] = costuper;


                    costBox = temp2;
                    temp2 = null;

                    System.out.println(" ");
                    System.out.println("     <<       " + inventory[idxx] + "      >>");
                    System.out.println("\n남은 돈: " + cost + "원");
                    System.out.println("강화방어권: " + (doubleCount - 1) + "장");

                    //강화 시에 70% 50% 30% 10%씩 확률을 떨어지기하는 기능
                    --upgradePercentCount;


                    System.out.println("\n다음 강화확률: " + upgradePercentCount * 10 + "%");
                    System.out.printf("강화비용은 [%d원]입니다.\n", updateCost * count);//숫자 먼저 올리고 계산하도록 앞에 ++붙임
                    System.out.println("\n이어서 강화하시겠습니까? [Y/N]");
                    System.out.print(">> ");
                    answer = sc.next();

                    if (answer.equalsIgnoreCase("y")) {//강화 다시시도
                        if(cost < updateCost * count) {
                            System.out.println("\n돈이 부족하여 강화를 실행할 수 없습니다!!!!");
                            System.out.println("강화 시스템을 종료합니다...");
                            System.out.println("\n메인으로 돌아갑니다.");
                            System.out.println("====================================");
                            return;
                        }
                        System.out.println("강화를 시작합니다.");

                    } else {
                        System.out.println("\n메인으로 돌아갑니다.");
                        System.out.println("====================================");

                        //확률 초기화
                        upgradePercentCount = 9;
                        return;
                    }


                } else {
                    System.out.println("\n==================================");
                    System.out.println("=             강화 실패           =");
                    System.out.println("==================================");


                    ///////////////// 4번 메뉴 담는 배열 /////////////////
                    //1. 원본 배열보다 사이즈가 1개 더 큰 새 배열을 생성
                    String[] temp = new String[successOrFall.length + 1];

                    //2. 기존 배열 데이터를 복사해서 신규배열로 이동
                    for (i = 0; i < successOrFall.length; i++) {
                        temp[i] = successOrFall[i];
                    }
                    //3. 추가할 데이터를 맨 마지막 위치에 저장
                    temp[temp.length - 1] = upgradeFall;

                    //5. 주소지 이전
                    successOrFall = temp;
                    temp = null;

                    ///////////////// 4번 메뉴 담는 배열 /////////////////
                    // 강화 성공하면 강화목록에 보여줄 금액을 담는 배열 push
                    int[] temp2 = new int[costBox.length + 1];


                    for (i = 0; i < costBox.length; i++) {
                        temp2[i] = costBox[i];
                    }

                    temp2[temp2.length - 1] = costuper;


                    costBox = temp2;
                    temp2 = null;

                    if(doubleCount == 2) {
                        System.out.println("\n[Y]. 아이템 파괴하기  [N]. 강화방어권 사용하기(두배의 비용을 지불하고 다시 강화하기)");
                        System.out.println("\n강화방어권: " + (doubleCount - 1) + "장");
                        System.out.println("\n강화방어권 사용 시 강화 확률은 이전 확률과 동일하게 적용됩니다!!");
                        System.out.printf("※ 두배 강화비용은 [%d원]입니다.\n", updateCost * ((count - 1) * 2));
                    } else {
                        System.out.println("\n[Y]. 아이템 파괴하기");
                        System.out.println("\n강화방어권: " + (doubleCount - 1) + "장");
                        System.out.println("\n강화방어권을 모두 사용해서 아이템이 파괴됩니다...");
                    }




                    System.out.print(">> ");
                    answer = sc.next();
                    if (answer.equalsIgnoreCase("y")) {

                        for (i = idxx; i < inventory.length - 1; i++) {
                            inventory[i] = inventory[i + 1];

                        }


                        String[] temp3 = new String[inventory.length - 1];
                        for (i = 0; i < temp3.length; i++) {
                            temp3[i] = inventory[i];
                        }

                        inventory = temp3;
                        temp3 = null;

                        System.out.println("\n==================================");
                        System.out.println("=        퍼            엉         =");
                        System.out.println("==================================");

                        System.out.println("\n아이템이 파괴되었습니다......");

                        System.out.println("\n메인으로 돌아갑니다.");
                        System.out.println("====================================");
                        //확률 초기화
                        upgradePercentCount = 9;
                        //강화비용 초기화
                        count = 1;
                        return;

                    } else if (answer.equalsIgnoreCase("n") && doubleCount != 2) {
                        System.out.println("\n강화방어권이 존재하지 않습니다.");

                        for (i = idxx; i < inventory.length - 1; i++) {
                            inventory[i] = inventory[i + 1];

                        }


                        temp = new String[inventory.length - 1];
                        for (i = 0; i < temp.length; i++) {
                            temp[i] = inventory[i];
                        }

                        inventory = temp;
                        temp = null;


                        System.out.println("\n==================================");
                        System.out.println("=        퍼            엉         =");
                        System.out.println("==================================");

                        System.out.println("\n아이템이 파괴되었습니다......");

                        System.out.println("\n메인으로 돌아갑니다.");
                        System.out.println("====================================");
                        //확률 초기화
                        upgradePercentCount = 9;
                        count = 1;
                        return;

                    } else if (answer.equalsIgnoreCase("n") && doubleCount == 2) {
                        if(cost < updateCost * count) {

                            for (i = idxx; i < inventory.length - 1; i++) {
                                inventory[i] = inventory[i + 1];

                            }


                            temp = new String[inventory.length - 1];
                            for (i = 0; i < temp.length; i++) {
                                temp[i] = inventory[i];
                            }

                            inventory = temp;
                            temp = null;

                            System.out.println("\n돈이 부족하여 강화를 실행할 수 없습니다!!!!");
                            System.out.println("강화 시스템을 종료합니다...");
                            System.out.println("\n메인으로 돌아갑니다.");
                            System.out.println("====================================");

                            return;
                        }

                        ///////////////// 4번 메뉴 담는 배열 /////////////////
                        // 강화 성공하면 강화목록에 보여줄 금액을 담는 배열 push
                        temp2 = new int[costBox.length + 1];


                        for (i = 0; i < costBox.length; i++) {
                            temp2[i] = costBox[i];
                        }

                        temp2[temp2.length - 1] = updateCost * ((count - 1) * 2);


                        costBox = temp2;
                        temp2 = null;


                        System.out.println("\n강화비용을 두배로 올리고 강화를 다시 시작합니다.");


                        // 방어권 사용시 이전에 확률과 동일한 확률로 설정하는 기능
                        ++upgradePercentCount;



                        // updateCost * ((count - 1) * 2)은 강화비용을 두배로 처리하는 기능
                        System.out.printf("\n강화비용은 [%d원]입니다.", updateCost * ((count - 1) * 2));
                        doubleCount--;
                        count++;


                    }
                }
            }
        }
    }// end upgradeItem


    public void UpgradeListMenu() {
        while (true) {
            System.out.println("\n************* Upgrade Item List *************");
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy년 MM월dd일");
            Date time = new Date();
            String times = format2.format(time);
            System.out.println("");
            for (int i = 0; i < successOrFall.length; i++) {
                System.out.println((i + 1) + ". " + "[날짜]: " + times + " " + "[강화성공여부]: " + successOrFall[i] + " " + "[지불한 금액]: " + costBox[i] + "원");
            }

            System.out.println("\n[Y]. 나가기");
            System.out.print(">> ");
            String inventoryExit = sc.next();

            if (inventoryExit.equalsIgnoreCase("y")) {
                break;
            } else if (inventoryExit.equalsIgnoreCase("n")) {

            }
        }


    }


}