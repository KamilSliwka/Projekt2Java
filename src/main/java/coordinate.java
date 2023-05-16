
public class coordinate {
    private int x;
    private int y;

    public coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public coordinate(coordinate c) {
        this.x = c.getX();
        this.y = c.getY();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof coordinate)) return false;

        coordinate that = (coordinate) o;

        if (getX() != that.getX()) return false;
        return getY() == that.getY();
    }

    @Override
    public int hashCode() {
        int result = getX();
        result = 31 * result + getY();
        return result;
    }

}
