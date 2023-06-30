package game.windows.components;

import javax.swing.*;

public class TextField extends Component {
    private final JTextField textField;

    TextField(JTextField textField) {
        this.textField = textField;
    }

    public String getContent() {
        return textField.getText();
    }

    @Override
    java.awt.Component getComponent() {
        return textField;
    }
}
