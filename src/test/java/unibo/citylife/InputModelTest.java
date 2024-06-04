package unibo.citylife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unibo.citysimulation.model.InputModel;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;

import static org.junit.jupiter.api.Assertions.*;

public class InputModelTest {
    private InputModel inputModel;

    @BeforeEach
    public void setUp() {
        inputModel = new InputModel();
    }

    @Test
    public void testNumberOfPeople() {
        int range = ConstantAndResourceLoader.MAX_PEOPLE - ConstantAndResourceLoader.MIN_PEOPLE;
        int valueToSet = (50 - ConstantAndResourceLoader.MIN_PEOPLE) * 100 / range;
        inputModel.setNumberOfPeople(valueToSet);
        assertEquals(55, inputModel.getNumberOfPeople());
    }

    @Test
    public void testNumberOfBusiness() {
        inputModel.addNumberOfBusiness(30);
        assertEquals(30, inputModel.getNumberOfBusiness());
    }

    @Test
    public void testCapacity() {
        inputModel.setCapacity(100);
        assertEquals(100, inputModel.getCapacity());
    }

    @Test
    public void testRichness() {
        inputModel.setRichness(200);
        assertEquals(200, inputModel.getRichness());
    }
}