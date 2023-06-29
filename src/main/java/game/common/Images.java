package game.common;

public enum Images {
    OMOK_BG("오목 게임 배경 이미지", "omok"),
    MINE_BG("지뢰찾기 게임 배경 이미지", "mine"),
    COIN_BACK_BG("동전 뒷면", "back"),
    COIN_FORE_BG("동전 앞면", "fore"),

    // 오목에서 사용
    OMOK_TURN("오목 자신의 턴", "turn"),
    OMOK_NOT_TURN("오목 자신의 턴이 아님", "noturn"),
    OMOK_PANEL("오목 기본 판", "pandegi"),

    WHILE_STONE("하얀 돌", "wdol"),
    BLACK_STONE("검은 돌", "bdol"),

    OMOK_WIN("오목 승리", "win"),

    // 지뢰찾기에서 사용
    DEFAULT_MINE_PANEL("지뢰찾기 기본 칸", "minenormal"),
    NO_MINE_PANEL("지뢰 없는 칸", "nomine"),
    CLICKED_MINE_PANEL("눌린 지뢰", "clickedmine"),
    FLAG_MAKR("깃발 마크", "flag"),
    QUESTION_MARK("물음표 마크", "question"),
    WRONG_CLICKED_MINE_PANEL("잘못 눌린 지뢰", "wrong"),

    MINE_GAMEOVER("지뢰찾기 게임오버", "gameover"),
    SMILE("지뢰찾기 스마일", "smile"),
    SUNGLASS_SMILE("지뢰찾기 선글라스 스마일", "sunglasssm"),
    UNFOUND_MINE_PANEL("못 찾은 지뢰 칸", "unfoundmine"),

    NUMBER_1("숫자 1", "1"),
    NUMBER_2("숫자 2", "2"),
    NUMBER_3("숫자 3", "3"),
    NUMBER_4("숫자 4", "4"),
    NUMBER_5("숫자 5", "5"),
    NUMBER_6("숫자 6", "6"),
    NUMBER_7("숫자 7", "7"),
    NUMBER_8("숫자 8", "8"),
    ;
    private static final String DEFAULT_BASE_PATH = "src/main/resources/";
    private static final String DEFAULT_IMAGE_FILE_EXT = ".jpg";

    private final String desc;
    private final String imageName;

    Images(String desc, String imageName) {
        this.desc = desc;
        this.imageName = imageName;
    }

    public String getPath() {
        return DEFAULT_BASE_PATH + imageName + DEFAULT_IMAGE_FILE_EXT;
    }
}
