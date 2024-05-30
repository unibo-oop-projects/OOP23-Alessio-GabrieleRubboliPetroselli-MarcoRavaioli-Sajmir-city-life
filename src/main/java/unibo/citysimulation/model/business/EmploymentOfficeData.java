package unibo.citysimulation.model.business;

import java.util.List;

import unibo.citysimulation.model.person.DynamicPerson;

/**
 * The EmploymentOffice class represents an employment office in a city simulation.
 * It keeps track of disoccupied people and provides methods to add and remove them.
 */
public record EmploymentOfficeData(List <DynamicPerson> disoccupied) {}
