package unibo.citylife.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unibo.citysimulation.model.InputModel;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the InputModel class.
 */
final class InputModelTest {
    private static final int TEST_PEOPLE_VALUE = 50;
    private static final int TEST_PEOPLE_EXPECTED = 55;
    private static final int TEST_BUSINESS_COUNT = 30;
    private static final int TEST_CAPACITY = 100;
    private static final int TEST_RICHNESS = 200;

    private InputModel inputModel;

    /**
     * Sets up the tests.
     */
    @BeforeEach
    void setUp() {
        inputModel = new InputModel();
    }

    /**
     * Tests the setNumberOfPeople and getNumberOfPeople methods.
     */
    @Test
    void testNumberOfPeople() {
        int range = ConstantAndResourceLoader.MAX_PEOPLE - ConstantAndResourceLoader.MIN_PEOPLE;
        int valueToSet = (TEST_PEOPLE_VALUE - ConstantAndResourceLoader.MIN_PEOPLE) * 100 / range;
        inputModel.setNumberOfPeople(valueToSet);
        assertEquals(TEST_PEOPLE_EXPECTED, inputModel.getNumberOfPeople());
    }

    /**
     * Tests the addNumberOfBusiness and getNumberOfBusiness methods.
     */
    @Test
    void testNumberOfBusiness() {
        inputModel.addNumberOfBusiness(TEST_BUSINESS_COUNT);
        assertEquals(TEST_BUSINESS_COUNT, inputModel.getNumberOfBusiness());
    }

    /**
     * Tests the setCapacity and getCapacity methods.
     */
    @Test
    void testCapacity() {
        inputModel.setCapacity(TEST_CAPACITY);
        assertEquals(TEST_CAPACITY, inputModel.getCapacity());
    }

    /**
     * Tests the setRichness and getRichness methods.
     */
    @Test
    void testRichness() {
        inputModel.setRichness(TEST_RICHNESS);
        assertEquals(TEST_RICHNESS, inputModel.getRichness());
    }
}

