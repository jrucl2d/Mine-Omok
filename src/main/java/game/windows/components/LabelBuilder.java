package game.windows.components;

import game.common.Images;

import javax.swing.*;

public class LabelBuilder {
    private int x;
    private int y;
    private int width;
    private int height;
    private Images image;
    private boolean visible;

    public static LabelBuilder builder() {
        return new LabelBuilder();
    }

    public LabelBuilder x(final int x) {
        this.x = x;
        return this;
    }

    public LabelBuilder y(final int y) {
        this.y = y;
        return this;
    }

    public LabelBuilder width(final int width) {
        this.width = width;
        return this;
    }

    public LabelBuilder height(final int height) {
        this.height = height;
        return this;
    }

    public LabelBuilder image(final Images image) {
        this.image = image;
        return this;
    }

    public LabelBuilder visible(final boolean visible) {
        this.visible = visible;
        return this;
    }

    public Label build() {
        final JLabel label = new JLabel();
        label.setBounds(x, y, width, height);
        if (image != null) {
            label.setIcon(new ImageIcon(image.getPath()));
        }
        label.setVisible(visible);
        return new Label(label);
    }
}
