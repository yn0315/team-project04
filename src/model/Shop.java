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
    public String[] allItem = new String[]{"기본 검", "보통 검", "고급 검", "기본 지팡이", "보통 지팡이", "고급 지팡이",
            "기본 활", "보통 활", "고급 활", "기본갑옷", "보통갑옷", "고급갑옷", "목걸이", "반지", "팔찌"};
    public String[] sellWarriorWeapon = new String[]{"기본 검", "보통 검", "고급 검"};
    public String[] sellMageWeapon = new String[]{"기본 지팡이", "보통 지팡이", "고급 지팡이"};
    public String[] sellArcherWeapon = new String[]{"기본 활", "보통 활", "고급 활"};
    public String[] sellArmor = new String[]{"기본갑옷", "보통갑옷", "고급갑옷"};
    public String[] sellAccessory = new String[]{"목걸이", "반지", "팔찌"};

    // 상점에서 구입할 때 가격을 나누기 위해 기본시리즈, 보통시리즈, 고급시리즈, 장신구 따로 배열을 나눔
    public String[] basiclItem = new String[] {"기본 검", "기본 지팡이", "기본 활", "기본갑옷",};
    public String[] nomalItem = new String[] {"보통 검", "보통 지팡이", "보통 활", "보통갑옷",};
    public String[] highItem = new String[] {"고급 검", "고급 지팡이", "고급 활", "고급갑옷",};
    public String[] AccessoryItem = new String[] {"목걸이", "반지", "팔찌"};

    // 상점에서 구입할 때 아이템별 가격
    // 기본 시리즈 = 2000원, 보통 시리즈 = 5000원, 고급 시리즈 = 10000, 장신구 = 8000원으로 설정
    public int[] buyCost = new int[] {2000, 5000, 10000, 8000};


    /////////////// 생성자 ///////////////
    public Shop() {

    }
    /////////////// 메소드 ///////////////


    public boolean getPlayerId(){//현재 로그인한 아이디가 aaa인지 확인해주고 맞으면 true를 반환하는 메서드
        Player id = pc.searchId(lgl.id1);
        if("aaa".equals(id));
        return true;
    }

}// end class
