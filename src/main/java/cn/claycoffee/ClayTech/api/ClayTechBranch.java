package cn.claycoffee.ClayTech.api;

public enum ClayTechBranch {

    DEVELOPMENT("dev"), STABLE("stable"), ALL("all");

    public final String name;

    ClayTechBranch(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
