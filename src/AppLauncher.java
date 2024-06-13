import javax.swing.*;

public class AppLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // display new weather app gui
//            new WeatherAppGUI().setVisible(true);

//                System.out.println(WeatherApp.getLocationData("Tokyo"));
            System.out.println(WeatherApp.getCurrentTime());
        });
    }
}
