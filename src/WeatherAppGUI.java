import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class WeatherAppGUI extends JFrame {
    private JSONObject weatherData;

    public WeatherAppGUI() {
        // Setup gui and add title
        super("Weather App"); // adds title to the app
        // configure gui to exit the program process once it has been closed
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // set the size of our gui (px)
        // setLayout(new FlowLayout()); check what this does in the future
        setSize(450, 650);
        // load our gui at the center of the screen
        setLocationRelativeTo(null);
        // make our layout manager null to manually position our components within the gui
        setLayout(null);
        // prevent resize of the gui
        setResizable(false);

        addGuiComponents();
    }

    private void addGuiComponents() {
        // search field
        JTextField searchField = new JTextField();
        // set the location and size of our component
        searchField.setBounds(15, 15, 351, 45);
        // change the font style and size
        searchField.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(searchField);

        // weather image
        JLabel weatherConditionImage = new JLabel(loadImage("src/assets/img/cloudy.png"));
        weatherConditionImage.setBounds(0, 125, 450, 217);
        add(weatherConditionImage);

        // temperature txt
        JLabel temperatureText = new JLabel("10 c");
        temperatureText.setBounds(0, 350, 450, 54);
        temperatureText.setFont(new Font("Dialog", Font.BOLD, 48));
        temperatureText.setHorizontalAlignment(SwingConstants.CENTER);
        add(temperatureText);

        // weather condition desc
        JLabel weatherConditionDesc = new JLabel("Cloudy");
        weatherConditionDesc.setBounds(0, 405, 450, 36);
        weatherConditionDesc.setFont(new Font("Dialog", Font.PLAIN, 32));
        weatherConditionDesc.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherConditionDesc);

        // humidity
        JLabel humidityImage = new JLabel(loadImage("src/assets/img/humidity.png"));
        humidityImage.setBounds(15, 500, 74, 66);
        add(humidityImage);
        //text
        // YOU CAN PLACE HTML CODE WITHIN SWING COMPONENTS
        JLabel humidityText = new JLabel("<html><b>Humidity</b> 100%</html>");
        humidityText.setBounds(90, 500, 85, 55);
        humidityText.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(humidityText);

        // wind speed
        JLabel windSpeedImage = new JLabel(loadImage("src/assets/img/windspeed.png"));
        windSpeedImage.setBounds(220, 500, 74, 66);
        add(windSpeedImage);
        JLabel windSpeedText = new JLabel("<html><b>Wind Speed</b> 15km/h</html>");
        windSpeedText.setBounds(310, 500, 85, 55);
        windSpeedText.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(windSpeedText);

        // add a search button
        JButton searchButton = new JButton(loadImage("src/assets/img/search.png"));
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBounds(375, 13, 47, 45);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get location from user
                String userInput = searchField.getText();

                // validate input - remove whitespace to ensure non empty text
                if (userInput.replaceAll("\\s", "").length() <= 0) {
                    return;
                }

                // retrieve weather data
               JSONObject weatherData = WeatherApp.getWeatherData(userInput);

                // update gui
                // update weather image
                String weatherCondition = (String) weatherData.get("weather_condition");

                // depending on the condition, we will update the weather image that corresponds with the condition
                switch (weatherCondition) {
                    case "Clear":
                        weatherConditionImage.setIcon(loadImage("src/assets/clear.png"));
                        break;
                    case "Cloudy":
                        weatherConditionImage.setIcon(loadImage("src/assets/cloudy.png"));
                        break;
                    case "Rain":
                        weatherConditionImage.setIcon(loadImage("src/assets/rain.png"));
                        break;
                    case "Snow":
                        weatherConditionImage.setIcon(loadImage("src/assets/snow.png"));
                        break;
                }

                // update temperature text
                double temperature = (double) weatherData.get("temperature");
                temperatureText.setText(temperature + " C");

                // update weather condition text
                weatherConditionDesc.setText(weatherCondition);

                // humidity text
                long humidity = (long) weatherData.get("humidity");
                humidityText.setText("<html><b>Humidity:</b> " + humidity + "%</html>");

                // update windspeed text
                double windspeed = (double) weatherData.get("windspeed");
                windSpeedText.setText("<html><b>Windspeed</b> " + windspeed + "km/h</html>");

            }
        });
        add(searchButton);
    }

    // used to create images in our gui components
    private ImageIcon loadImage(String path) {
        try {
            BufferedImage image = ImageIO.read(new File(path));

            // returns an image icon so component can render it
            return new ImageIcon(image);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Could not find resource");
        return null;
    }
}
