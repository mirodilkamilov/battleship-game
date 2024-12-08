import java.util.HashSet;
import java.util.Set;

public class Battleship {
    public int numOfHits = 0;
    public Boolean isDestroyed = false;
    private Set<String> shipCoordinates;
    private Set<String> shipHitCoordinates = new HashSet<>();
    private int sizeOfShip = 3;

    public void destroyShip() {
        isDestroyed = true;
    }

    public void hitShip(String shipCoordinate) {
        if (shipHitCoordinates.contains(shipCoordinate))
            return;

        shipHitCoordinates.add(shipCoordinate);
        numOfHits++;
        if (numOfHits == sizeOfShip) {
            destroyShip();
        }
    }

    public Set<String> getShipCoordinates() {
        return shipCoordinates;
    }

    public void setShipCoordinates(Set<String> shipCoordinates) {
        this.shipCoordinates = shipCoordinates;
    }

    public int getSizeOfShip() {
        return sizeOfShip;
    }
}
