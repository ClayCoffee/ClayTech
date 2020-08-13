package cn.claycoffee.ClayTech;

public enum ClayTechBiomes {
    LAVA_RIVER(1, "Lava River"), PLAIN(2, "Plain"), MOUNTAIN(3, "Mountain"), RIVER(4, "River"), CRATER(5, "Crater");

    public final int id;
    public final String name;

    ClayTechBiomes(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
