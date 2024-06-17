import javax.swing.*;

public class AppLauncher {
    public static void main(String[] args) {
        // display new weather app gui
//                System.out.println(WeatherApp.getLocationData("Tokyo"));
//            System.out.println(WeatherApp.getCurrentTime());
        SwingUtilities.invokeLater(() -> new WeatherAppGUI().setVisible(true));
    }
}
