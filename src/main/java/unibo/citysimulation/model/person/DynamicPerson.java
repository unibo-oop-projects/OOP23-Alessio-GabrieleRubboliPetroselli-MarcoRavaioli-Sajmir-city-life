package unibo.citysimulation.model.person;

import java.time.LocalTime;

public interface DynamicPerson extends StaticPerson {

    void checkState(LocalTime currentTime);
}
