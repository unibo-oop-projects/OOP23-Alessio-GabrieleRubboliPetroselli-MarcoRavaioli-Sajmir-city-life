package unibo.citysimulation.model.business;

import java.time.LocalTime;

public class BusinessConfig {
    
    public static final int START_TIME_BIG_BUSINESS = 12;
    public static final int END_TIME_BIG_BUSINESS = 21;
    public static final int START_TIME_MEDIUM_BUSINESS = 17;
    public static final int END_TIME_MEDIUM_BUSINESS = 23;
    public static final int START_TIME_SMALL_BUSINESS = 8;
    public static final int END_TIME_SMALL_BUSINESS = 13;
    public static final int MINUTES = 0;


    public static final double BIG_REVENUE = 25.0;
    public static final double MEDIUM_REVENUE = 12.0;
    public static final double SMALL_REVENUE = 7.5;

    public static final LocalTime BIG_OPENING_TIME = LocalTime.of(START_TIME_BIG_BUSINESS, MINUTES);
    public static final LocalTime BIG_CLOSING_TIME = LocalTime.of(END_TIME_BIG_BUSINESS, MINUTES);
    public static final LocalTime MEDIUM_OPENING_TIME = LocalTime.of(START_TIME_MEDIUM_BUSINESS, MINUTES);
    public static final LocalTime MEDIUM_CLOSING_TIME = LocalTime.of(END_TIME_MEDIUM_BUSINESS, MINUTES);
    public static final LocalTime SMALL_OPENING_TIME = LocalTime.of(START_TIME_SMALL_BUSINESS, MINUTES);
    public static final LocalTime SMALL_CLOSING_TIME = LocalTime.of(END_TIME_SMALL_BUSINESS, MINUTES);

    public static final int MAX_EMPLOYEES_BIG_BUSINESS = 300;
    public static final int MAX_EMPLOYEES_MEDIUM_BUSINESS = 100;
    public static final int MAX_EMPLOYEES_SMALL_BUSINESS = 20;

    public static final int BIG_MAX_TARDINESS = 1;
    public static final int MEDIUM_MAX_TARDINESS = 5;
    public static final int SMALL_MAX_TARDINESS = 10;

    public static final int BIG_MIN_AGE = 35;
    public static final int MEDIUM_MIN_AGE = 27;
    public static final int SMALL_MIN_AGE = 18;

    public static final int BIG_MAX_AGE = 60;
    public static final int MEDIUM_MAX_AGE = 50;
    public static final int SMALL_MAX_AGE = 29;
}
