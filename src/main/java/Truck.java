public class Truck {
    private int id;
    private int locationId;
    private int loadedBikesCount;

    public Truck() {
    }
    public Truck(int id, int locationId, int loadedBikesCount) {
        this.id = id;
        this.locationId = locationId;
        this.loadedBikesCount = loadedBikesCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getLoadedBikesCount() {
        return loadedBikesCount;
    }

    public void setLoadedBikesCount(int loadedBikesCount) {
        this.loadedBikesCount = loadedBikesCount;
    }
}
