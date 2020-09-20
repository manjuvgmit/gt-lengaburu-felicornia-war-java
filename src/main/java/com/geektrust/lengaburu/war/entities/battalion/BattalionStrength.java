package com.geektrust.lengaburu.war.entities.battalion;

public class BattalionStrength {
    private Battalion horses;
    private Battalion elephants;
    private Battalion armouredTanks;
    private Battalion slingGuns;

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

    public void setHorses(Battalion horses) {
        this.horses = horses;
    }

    public void setElephants(Battalion elephants) {
        this.elephants = elephants;
    }

    public void setArmouredTanks(Battalion armouredTanks) {
        this.armouredTanks = armouredTanks;
    }

    public void setSlingGuns(Battalion slingGuns) {
        this.slingGuns = slingGuns;
    }

    public int getTotalBattalionStrength() {
        return horses.getTotalStrength()
                + elephants.getTotalStrength()
                + armouredTanks.getTotalStrength()
                + slingGuns.getTotalStrength();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("BattalionStrength{");
        sb.append("horses=").append(horses);
        sb.append(", elephants=").append(elephants);
        sb.append(", armouredTanks=").append(armouredTanks);
        sb.append(", slingGuns=").append(slingGuns);
        sb.append('}');
        return sb.toString();
    }

    public String toStringCustom() {
        StringBuilder sb = new StringBuilder();
        sb.append(horses.toStringCustom());
        sb.append(" ").append(elephants.toStringCustom());
        sb.append(" ").append(armouredTanks.toStringCustom());
        sb.append(" ").append(slingGuns.toStringCustom());
        return sb.toString();
    }

    public static class Builder {
        private int horses = 0;
        private int elephants = 0;
        private int armouredTanks = 0;
        private int slingGuns = 0;

        public Builder() {
        }

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
