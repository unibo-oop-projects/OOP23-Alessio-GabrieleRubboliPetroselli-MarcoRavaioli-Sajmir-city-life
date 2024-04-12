package unibo.citysimulation.model.transport;

public class TransportImpl implements Transport {

    private int capacity;
    private int congestion;

    public TransportImpl(int capacity,int congestion){
        this.congestion=congestion;
        this.capacity=capacity;
    }

    @Override
    public int getCongestion() {
        return this.congestion;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }
    
}
