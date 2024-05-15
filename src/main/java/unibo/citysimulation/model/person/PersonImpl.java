package unibo.citysimulation.model.person;

import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.clock.ClockModel;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneTable;

import java.time.LocalTime;
import java.util.Optional;

import ch.qos.logback.core.joran.sanity.Pair;


public class PersonImpl implements Person {
    private String name;
    private int age;
    private int experience;
    private int money;
    private Pair<Integer, Integer> home;
    



    public PersonImpl(String name, int age, int experience, Pair<Integer, Integer> home, int money) {
        this.age = age;
        this.name = name;
        this.experience = experience;
        this.home = home;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int getMoney() {
        return money;
    }

    @Override
    public int getExperience() {
        return experience;
    }

    @Override
    public Pair<Integer, Integer> getHome() {
        return home;
    }

    

    
    
}
