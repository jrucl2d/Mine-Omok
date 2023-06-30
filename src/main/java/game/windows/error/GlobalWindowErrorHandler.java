package game.windows.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class GlobalWindowErrorHandler implements Thread.UncaughtExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalWindowErrorHandler.class);

    @Override
    public void uncaughtException(final Thread t, final Throwable e) {
        logger.error("{} : 윈도우 에러 발생!", t, e);
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
}
