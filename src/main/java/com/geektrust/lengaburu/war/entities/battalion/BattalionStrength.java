package com.geektrust.lengaburu.war.entities.battalion;

public class BattalionStrength {
    private final Battalion horses;
    private final Battalion elephants;
    private final Battalion armouredTanks;
    private final Battalion slingGuns;

    public BattalionStrength(int horses, int elephants, int armouredTanks, int slingGuns) {
        this.horses = new HorseBattalion(horses);
        this.elephants = new ElephantBattalion(elephants);
        this.armouredTanks = new ArmouredTankBattalion(armouredTanks);
        this.slingGuns = new SlingGunBattalion(slingGuns);
    }

    public Battalion getHorses() {
        return horses;
    }

    public Battalion getElephants() {
        return elephants;
    }

    public Battalion getArmouredTanks() {
        return armouredTanks;
    }

    public Battalion getSlingGuns() {
        return slingGuns;
    }

    public int getTotalBattalionStrength() {
        return (horses != null ? horses.getTotalStrength() : 0)
                + (elephants != null ? elephants.getTotalStrength() : 0)
                    + (armouredTanks != null ? armouredTanks.getTotalStrength() : 0)
                        + (slingGuns != null ? slingGuns.getTotalStrength() : 0);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BattalionStrength{");
        sb.append("horses=").append(horses);
        sb.append(", elephants=").append(elephants);
        sb.append(", armouredTanks=").append(armouredTanks);
        sb.append(", slingGuns=").append(slingGuns);
        sb.append('}');
        return sb.toString();
    }

    public String toStringCustom() {
        final StringBuilder sb = new StringBuilder();
        sb.append(horses.toStringCustom());
        sb.append(" ").append(elephants.toStringCustom());
        sb.append(" ").append(armouredTanks.toStringCustom());
        sb.append(" ").append(slingGuns.toStringCustom());
        return sb.toString();
    }

    public static class Builder {
        private int horses;
        private int elephants;
        private int armouredTanks;
        private int slingGuns;

        public int getHorses() {
            return horses;
        }

        public int getElephants() {
            return elephants;
        }

        public int getArmouredTanks() {
            return armouredTanks;
        }

        public int getSlingGuns() {
            return slingGuns;
        }

        public Builder withHorses(int horses) {
            this.horses = horses;
            return this;
        }

        public Builder withElephants(int elephants) {
            this.elephants = elephants;
            return this;
        }

        public Builder withArmouredTanks(int armouredTanks) {
            this.armouredTanks = armouredTanks;
            return this;
        }

        public Builder withSlingGuns(int slingGuns) {
            this.slingGuns = slingGuns;
            return this;
        }

        public BattalionStrength build() {
            return new BattalionStrength(horses, elephants, armouredTanks, slingGuns);
        }
    }
}
