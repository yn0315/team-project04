package model;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.util.Arrays;
import java.util.Scanner;
import model.Player;
import view.PlayMenu;

public class Shop {
    //스캐너 설정
    Scanner sc = new Scanner(System.in);
    private PlayerController pc = new PlayerController();
    private LoginList lgl = new LoginList();


    /////////////// 필드 ///////////////

    //전체 아이템 배열
    public String[] allItem = new String[]{"기본검", "보통검", "고급검", "기본 지팡이", "보통지팡이", "고급지팡이",
            "기본활", "보통활", "고급활", "기본갑옷", "보통갑옷", "고급갑옷", "목걸이", "반지", "팔찌"};
    public String[] sellWarriorWeapon = new String[]{"기본검", "보통검", "고급검"};
    public String[] sellMageWeapon = new String[]{"기본지팡이", "보통지팡이", "고급 지팡이"};
    public String[] sellArcherWeapon = new String[]{"기본활", "보통활", "고급활"};
    public String[] sellArmor = new String[]{"기본갑옷", "보통갑옷", "고급갑옷"};
    public String[] sellAccessory = new String[]{"목걸이", "반지", "팔찌"};

    // 상점에서 구입할 때 가격을 나누기 위해 기본시리즈, 보통시리즈, 고급시리즈, 장신구 따로 배열을 나눔
    public String[] basiclItem = new String[] {"기본검", "기본지팡이", "기본활", "기본갑옷",};
    public String[] nomalItem = new String[] {"보통검", "보통지팡이", "보통활", "보통갑옷",};
    public String[] highItem = new String[] {"고급검", "고급지팡이", "고급활", "고급갑옷",};
    public String[] AccessoryItem = new String[] {"목걸이", "반지", "팔찌"};

    // 상점에서 구입할 때 아이템별 가격
    // 기본 시리즈 = 2000원, 보통 시리즈 = 5000원, 고급 시리즈 = 10000, 장신구 = 8000원으로 설정
    public int[] buyCost = new int[] {2000, 5000, 10000, 8000};


    /////////////// 생성자 ///////////////
    public Shop() {

    }
    /////////////// 메소드 ///////////////


    public boolean getPlayerId(Player player){//현재 로그인한 아이디가 aaa인지 확인해주고 맞으면 true를 반환하는 메서드
        Player id = pc.searchId(lgl.id1);
        if("aaa".equals(id));
        return true;
    }

   public void buyItem(String itemName, Player myPlayer) {
        for (int i = 0; i < allItem.length; i++) {
            if (itemName.equals(allItem[i])) {
                System.out.println("구매를 하시겠습니까? [Y/N]");
                String answer = sc.next();
                if (answer.equalsIgnoreCase("y")) {
                    System.out.println("\n구매가 완료되었습니다.");
                    System.out.println("\n=====================================");
                    // 구매할 아이템이 기본시리즈이면 플레이어 돈에서 2000원 차감하는 기능
                    for (int j = 0; j < basiclItem.length; j++) {
                        if (itemName.equals(basiclItem[j])) {
                            myPlayer.cost -= buyCost[0];
                            break;
                        }
                    }
                    // 구매할 아이템이 기본시리즈이면 플레이어 돈에서 5000원 차감하는 기능
                    for (int j = 0; j < nomalItem.length; j++) {
                        if (itemName.equals(nomalItem[j])) {
                            myPlayer.cost -= buyCost[1];
                            break;
                        }
                    }

                    // 구매할 아이템이 기본시리즈이면 플레이어 돈에서 10000원 차감하는 기능
                    for (int j = 0; j < highItem.length; j++) {
                        if (itemName.equals(highItem[j])) {
                            myPlayer.cost -= buyCost[2];
                            break;
                        }
                    }
                    // 구매할 아이템이 기본시리즈이면 플레이어 돈에서 8000원 차감하는 기능
                    for (int j = 0; j < AccessoryItem.length; j++) {
                        if (itemName.equals(AccessoryItem[j])) {
                            myPlayer.cost -= buyCost[3];
                            break;
                        }
                    }

                    if (getPlayerId(myPlayer)) {

                        addPlus(myPlayer.inventory, itemName,myPlayer);
                        break;
                    }

                }

            }

        }
    }

    // 상점에서 아이템 구매시 배열에 추가하는 메소드
    String[] addPlus(String[]itemArrs, String itemName, Player myPlayer) {
        String[] temp = new String[itemArrs.length + 1];

        //2. 기존 배열 데이터를 복사해서 신규배열로 이동
        for (int i = 0; i < itemArrs.length; i++) {
            temp[i] = itemArrs[i];
        }
        //3. 추가할 데이터를 맨 마지막 위치에 저장
        temp[temp.length - 1] = itemName;

        //5. 주소지 이전
        myPlayer.inventory = temp;
        temp = null;
        return itemArrs;
    }


}// end class
