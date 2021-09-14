package model;

public class PlayerController {
    private Player[] p = new Player[SIZE];
    public static final int SIZE = 10;


    public PlayerController() {//회원정보 하나 저장해둠

        p[0] = new Player(new String[]{"기본 검", "보통 활", "기본 갑옷", "고급 지팡이"},50000, "aaa");

    }

    //메서드
    //실제 저장된 회원의 숫자를 반환
    public int existMemberNum() {
        int count = 0; //숫자를 세는 변수
        for (int i = 0; i < p.length; i++) {
            if (p[i] == null) {
                break;
            }
            count++;
        }
        return count;

    }

    //아이디 입력하면 그 아이디에 해당하는 회원1명의 정보 리턴
    public Player searchId(String id) {
        for (int i = 0; i < existMemberNum(); i++) {
            if (id.equals(p[i].getId())) {
                return p[i];
            }
        }
        return null;

    }

    public Player[] showPlayer(){//플레이어 정보 리턴해주는 메서드
        return p;
    }


}
