import ui.SplashScreen;
import javax.swing.*;

void main(String[] args) {

  SwingUtilities.invokeLater(() -> {
    SplashScreen splash = new SplashScreen();
    splash.splashFrame.setVisible(true);
  });
  
}