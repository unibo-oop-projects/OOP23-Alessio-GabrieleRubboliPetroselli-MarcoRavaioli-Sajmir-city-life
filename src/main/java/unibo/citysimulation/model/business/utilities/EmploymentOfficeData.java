package unibo.citysimulation.model.business.utilities;

import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import unibo.citysimulation.model.person.api.DynamicPerson;
/**
 * Represents the data of an employment office, including a list of disoccupied dynamic persons.
 * @param disoccupied the list of disoccupied dynamic persons
 */
@SuppressFBWarnings(value = "EI", justification = """
""")
public record EmploymentOfficeData(List<DynamicPerson> disoccupied) { }
