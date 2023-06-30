package game.windows.manager;

import game.windows.components.Button;
import game.windows.components.ButtonBuilder;
import game.windows.components.Window;

import java.awt.*;

public class OmokOrderWindowManager implements WindowBuilder {

    @Override
    public Window build() {
        final Button backForeSelect = ButtonBuilder.builder()
                .title("앞 뒤 선택")
                .x(120)
                .y(20)
                .width(140)
                .height(30)
                .background(Color.white)
                .build();

        final Button selectFirst = ButtonBuilder.builder()
                .title("선공은?")
                .x(10)
                .y(240)
                .width(140)
                .height(30)
                .background(Color.white)
                .visible(false)
                .build();

        final Button gameStart = ButtonBuilder.builder()
                .title("게임 시작")
                .x(10)
                .y(300)
                .width(140)
                .height(30)
                .background(Color.white)
                .visible(false)
                .build();

        return new Window(Color.gray)
                .put(backForeSelect)
                .put(selectFirst)
                .put(gameStart)
                ;
    }
}
