package game.windows.components;

import javax.swing.*;

public class FrameBuilder {
    private String title;
    private int width;
    private int height;

    public FrameBuilder title(final String title) {
        this.title = title;
        return this;
    }

    public FrameBuilder width(final int width) {
        this.width = width;
        return this;
    }

    public FrameBuilder height(final int height) {
        this.height = height;
        return this;
    }

    public Frame build() {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(title);
        frame.setSize(width, height);
        frame.setVisible(true);
        return new Frame(frame);
    }
}
