package game.windows.components;

import javax.swing.*;
import java.awt.*;

public class TextFieldBuilder {
    private String text;
    private int x;
    private int y;
    private int width;
    private int height;
    private Color background = Color.white;
    private Color foreground = Color.black;
    private boolean visible = true;
    private boolean editable = true;

    public static TextFieldBuilder builder() {
        return new TextFieldBuilder();
    }

    public TextFieldBuilder text(final String text) {
        this.text = text;
        return this;
    }

    public TextFieldBuilder x(final int x) {
        this.x = x;
        return this;
    }

    public TextFieldBuilder y(final int y) {
        this.y = y;
        return this;
    }

    public TextFieldBuilder width(final int width) {
        this.width = width;
        return this;
    }

    public TextFieldBuilder height(final int height) {
        this.height = height;
        return this;
    }

    public TextFieldBuilder background(final Color background) {
        this.background = background;
        return this;
    }

    public TextFieldBuilder foreground(final Color foreground) {
        this.foreground = foreground;
        return this;
    }

    public TextFieldBuilder visible(final boolean visible) {
        this.visible = visible;
        return this;
    }

    public TextFieldBuilder editable(final boolean editable) {
        this.editable = editable;
        return this;
    }

    public TextField build() {
        final JTextField textField = new JTextField(text);
        textField.setBounds(x, y, width, height);
        textField.setBackground(background);
        textField.setForeground(foreground);
        textField.setVisible(visible);
        textField.setEditable(editable);
        return new TextField(textField);
    }
}
