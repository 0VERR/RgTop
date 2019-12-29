package pl.overr.top.user;

public class User {
    private final String UUID;
    private  String playerName;
    private  String ip;
    private  int minedStone;
    private  long timeplayed;

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setMinedStone(int minedStone) {
        this.minedStone = minedStone;
    }

    public void setTimeplayed(long timeplayed) {
        this.timeplayed = timeplayed;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setMinedWood(int minedWood) {
        this.minedWood = minedWood;
    }

    private  int deaths;
    private  int minedWood;

    public void setKills(int kills) {
        this.kills = kills;
    }

    private  int kills;

    public String getUUID() {
        return UUID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getIp() {
        return ip;
    }

    public int getMinedStone() {
        return minedStone;
    }

    public long getTimeplayed() {
        return timeplayed;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getMinedWood() {
        return minedWood;
    }

    public int getKills() {
        return kills;
    }

    public User(String uuid, String playerName, String ip, int minedStone, long timeplayed, int deaths, int kills, int minedWood) {
        UUID = uuid;
        this.playerName = playerName;
        this.ip = ip;
        this.minedStone = minedStone;
        this.timeplayed = timeplayed;
        this.deaths = deaths;
        this.kills = kills;
        this.minedWood = minedWood;
    }
}
