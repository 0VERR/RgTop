package pl.overr.top.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import pl.overr.top.TopPlugin;
import pl.overr.top.data.DataManager;
import pl.overr.top.user.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MySQL {

    private HikariDataSource hikariDataSource;
    private HikariConfig hikariConfig;
    private final DataManager dataManager;

    FileConfiguration cfg = TopPlugin.getInstance().getConfig();

    private final String username = cfg.getString("MySQL.username");
    private final String password = cfg.getString("MySQL.password");
    private final String host = cfg.getString("MySQL.host");
    private final String database = cfg.getString("MySQL.database");
    private final int port = cfg.getInt("MySQL.port");

    public MySQL(DataManager dataManager) {
        hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + database);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariDataSource = new HikariDataSource(hikariConfig);
        this.dataManager = dataManager;
    }

    public Connection getConnection() {
        try {
            return this.hikariDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createTable() {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `rgtop` (" +
                    "id int(16) PRIMARY KEY AUTO_INCREMENT," +
                    "UUID VARCHAR(36) NOT NULL," +
                    "playerName VARCHAR(16) NOT NULL," +
                    "playerIP VARCHAR(36) NOT NULL," +
                    "minedStone int(16) NOT NULL," +
                    "timePlayed BIGINT NOT NULL," +
                    "deaths int(16) NOT NULL," +
                    "minedWood int(16) NOT NULL," +
                    "kills int(16) NOT NULL)");
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("tabelka..");
        }
    }

    public void insertUser(User user) {
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO rgtop VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, 0);
            statement.setString(2, user.getUUID());
            statement.setString(3, user.getPlayerName());
            statement.setString(4, user.getIp());
            statement.setInt(5, user.getMinedStone());
            statement.setLong(6, user.getTimeplayed());
            statement.setInt(7, user.getDeaths());
            statement.setInt(8, user.getMinedWood());
            statement.setInt(9, user.getKills());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Blad z instertUser");
        }
    }

    public void updateUser() {
        dataManager.getUserMap().values().forEach(user -> {

            try (Connection connection = getConnection()) {
                PreparedStatement statement = connection.prepareStatement("UPDATE rgtop SET playerName = ?, " +
                        "playerIP = ?, minedStone = ?, timePlayed = ?, deaths = ?, minedWood = ?, kills = ? WHERE `UUID` =  '"+user.getUUID()+"';");
                statement.setString(1, user.getPlayerName());
                statement.setString(2, user.getIp());
                statement.setInt(3, user.getMinedStone());
                statement.setLong(4, user.getTimeplayed());
                statement.setInt(5, user.getDeaths());
                statement.setInt(6, user.getMinedWood());
                statement.setInt(7, user.getKills());
                statement.executeUpdate();

            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Update user");
            }
        });
    }
    public void loadUsers(){
        try (Connection connection = getConnection()){
            ResultSet resultSet = connection.prepareStatement("SELECT * FROM rgtop").executeQuery();
            while (resultSet.next()){
                User user = new User(resultSet.getString("UUID"), resultSet.getString("playerName"),
                        resultSet.getString("playerIP"), resultSet.getInt("minedStone"), resultSet.getLong("timePlayed"),
                        resultSet.getInt("deaths"), resultSet.getInt("minedWood"), resultSet.getInt("kills"));
                        dataManager.addToUserMap(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

