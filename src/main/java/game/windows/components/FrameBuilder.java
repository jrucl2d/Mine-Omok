package game.windows.components;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class FrameBuilder {
    private String title;
    private int width;
    private int height;
    private final Map<WindowType, Window> windows = new HashMap<>();

    public static FrameBuilder builder() {
        return new FrameBuilder();
    }

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

    public FrameBuilder addWindow(final WindowType type, final Window window) {
        this.windows.put(type, window);
        return this;
    }

    public Frame build() {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(title);
        frame.setSize(width, height);

        // 화면 가운데에 보이도록 설정
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - width) / 2;
        int y = (screenSize.height - height) / 2;
        frame.setLocation(x, y);

        frame.setVisible(true);
        return new Frame(frame, windows);
    }
}
