package model;

public class Region {
    private String regionName;
    private int regionPosition;

    public Region(String regionName, int regionPosition) {
        this.regionName = regionName;
        this.regionPosition = regionPosition;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public int getRegionPosition() {
        return regionPosition;
    }

    public void setRegionPosition(int regionPosition) {
        this.regionPosition = regionPosition;
    }

    @Override
    public String toString() {
        return "Region{" + "regionName=" + regionName + ", regionPosition=" + regionPosition + '}';
    }
    
    
}
