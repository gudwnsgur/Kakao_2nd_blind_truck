public class Location {
    private int id;
    private int locatedBikesCount;

    public Location () {

    }
    public Location(int id, int locatedBikesCount) {
        this.id = id;
        this.locatedBikesCount = locatedBikesCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocatedBikesCount() {
        return locatedBikesCount;
    }

    public void setLocatedBikesCount(int locatedBikesCount) {
        this.locatedBikesCount = locatedBikesCount;
    }
}
