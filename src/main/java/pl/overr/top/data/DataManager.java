package pl.overr.top.data;

import pl.overr.top.TopPlugin;
import pl.overr.top.user.User;

import java.util.*;
import java.util.stream.Collectors;

public class DataManager {

    public Map<UUID, User> getUserMap() {
        return userMap;
    }

    private final Map<UUID, User> userMap = new HashMap<>();
    private Set<User> topTenKills;
    private Set<User> topTenDeaths;
    private Set<User> topTenMinedStone;
    private Set<User> topTenTimePlayed;
    private Set<User> getTopTenMinedWood;


    public void addToUserMap(User user){
        userMap.put(UUID.fromString(user.getUUID()), user);
    }

    public User getUser(UUID UUID){
      return userMap.get(UUID);
    }

    public void createTopTenKills(){
        topTenKills = userMap.values().stream()
                .limit(9)
                .sorted(Comparator.comparingInt(User::getKills).reversed())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public void createTopTenDeaths(){
        topTenDeaths = userMap.values().stream()
                .limit(9)
                .sorted(Comparator.comparingInt(User::getDeaths).reversed())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public void createTopTenMinedStone(){
        topTenMinedStone = userMap.values().stream()
                .limit(9)
                .sorted(Comparator.comparingInt(User::getMinedStone).reversed())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public void createTopTenTimePlayed(){
        topTenTimePlayed = userMap.values().stream()
                .limit(9)
                .sorted(Comparator.comparingLong(User::getTimeplayed).reversed())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public void createTopTenMinedWood(){
        getTopTenMinedWood = userMap.values().stream()
                .limit(9)
                .sorted(Comparator.comparingInt(User::getMinedWood).reversed())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }


    public Set<User> getTopTenKills() {
        return topTenKills;
    }

    public Set<User> getTopTenDeaths() {
        return topTenDeaths;
    }

    public Set<User> getTopTenMinedStone() {
        return topTenMinedStone;
    }

    public Set<User> getTopTenTimePlayed() {
        return topTenTimePlayed;
    }

    public Set<User> getGetTopTenMinedWood() {
        return getTopTenMinedWood;
    }
}



