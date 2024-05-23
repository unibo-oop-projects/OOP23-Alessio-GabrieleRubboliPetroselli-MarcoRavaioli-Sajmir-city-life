package unibo.citysimulation.utilities;

public final class ConstantAndResourceLoader extends ClassLoader{
    /**
     * The application name
     */
    public static final String APPLICATION_NAME = "City Simulation";
    /**
     * The application version
     */
    public static final String APPLICATION_VERSION = "0.1";
    /**
     * Screen size percentage
     */
    public static final double SCREEN_SIZE_PERCENTAGE = 0.90;

    public static final int MINUTES_IN_A_SECOND = 5;

    public static final int TIME_UPDATE_RATE = 500;

    public static final int SIMULATION_TOTAL_DAYS = 365;

    public static final int[] SPEEDS = {1, 2, 10, 20};
    /**
     * Screen minimum size pixel
     */
    public static final int SCREEN_MINIMUM_WIDTH_PIXEL = 1000;
    public static final int SCREEN_MINIMUM_HEIGHT_PIXEL = 500;

    public static final int MIN_PEOPLE = 100;
    public static final int MAX_PEOPLE = 1000;

    public static final int MAX_COLUMNS = 150;

    public static final int MAX_DEVIATION_RANGE = 41;

    public static final int MAX_DEVIATION_OFFSET= 20;

    public static final int CONGESTION_VALUE = 98;

    public static final int MAX_MOVING_TIME_VARIATION = 13;

    public static final int SECONDS_IN_A_MINUTE = 60;

    public static final int MAX_RANDOM_AGE = 62;

    public static final int MIN_AGE = 18;

    public static final int CLOCK_PANEL_PANEL_WIDTH = 100;

    public static final int CLOCK_PANEL_PANEL_HEIGHT = 50;

    public static final int CLOCK_PANEL_FONT_SIZE = 15;

    public static final int WELCOME_SCREEN_MIN_WIDTH = 400;

    public static final int WELCOME_SCREEN_MIN_HEIGHT = 200;
}
