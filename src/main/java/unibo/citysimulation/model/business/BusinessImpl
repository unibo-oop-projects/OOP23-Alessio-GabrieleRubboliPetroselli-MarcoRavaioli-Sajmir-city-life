package unibo.citysimulation.model.business;
/**
 * A record implementation of the Business interface representing a business entity.
 */
public record BusinessImpl(int income, int employers) implements Business {

    /**
     * Constructs a new BusinessImpl instance with the given income and number of employers.
     *
     * @param income    The income of the business.
     * @param employers The number of employers in the business.
     */
    public BusinessImpl {
        if (income < 0 || employers < 0) {
            throw new IllegalArgumentException("Income and employers must be non-negative.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getIncome() {
        return income;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getEmployers() {
        return employers;
    }
}
