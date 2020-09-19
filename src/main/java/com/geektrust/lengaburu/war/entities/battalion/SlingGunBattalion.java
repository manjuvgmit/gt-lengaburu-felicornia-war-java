package com.geektrust.lengaburu.war.entities.battalion;

public final class SlingGunBattalion extends BaseBattalion {
    public SlingGunBattalion(Integer strength) {
        super(BattalionType.SLING_GUN, strength);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SlingGunBattalion{");
        sb.append("type=").append(type);
        sb.append(", strength=").append(strength);
        sb.append('}');
        return sb.toString();
    }
}
