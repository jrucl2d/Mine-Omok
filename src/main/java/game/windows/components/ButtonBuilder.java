package game.windows.components;

import javax.swing.*;
import java.awt.*;

public class ButtonBuilder {
    private String text;
    private int x;
    private int y;
    private int width;
    private int height;
    private Color background;

    public static ButtonBuilder builder() {
        return new ButtonBuilder();
    }

    public ButtonBuilder title(final String text) {
        this.text = text;
        return this;
    }

    public ButtonBuilder x(final int x) {
        this.x = x;
        return this;
    }

    public ButtonBuilder y(final int y) {
        this.y = y;
        return this;
    }

    public ButtonBuilder width(final int width) {
        this.width = width;
        return this;
    }

    public ButtonBuilder height(final int height) {
        this.height = height;
        return this;
    }

    public ButtonBuilder background(final Color background) {
        this.background = background;
        return this;
    }

    public Button build() {
        final JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(background);
        return new Button(button);
    }
}
