import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        String insertTableSQL = "INSERT INTO pets (petKid, petName, petAge, petColor, petOwner)" +
                "VALUES ('Dog', 'Muchtar', 5, 'Brown', 'Artem')," +
                "('Cow', 'Pushistik', 2, 'Black', 'Alexandra')," +
                "('Cat', 'Zefirca', 1, 'White', 'Koly')";
        String deleteTableSQL = "DELETE FROM pets WHERE petColor = 'Brown'";
        String selectTableSQL = "SELECT petName FROM pets WHERE petAge <8";
        Connection connection = null;
        Statement statement = null;
        Class<Driver> driverClass = Driver.class;

        try {
            connection = GetConnection.open();
            statement = connection.createStatement();

            int k = statement.executeUpdate(insertTableSQL);
            if (k!=0)
                System.out.println(k + " записи добавлены");
            int d = statement.executeUpdate(deleteTableSQL);
            if (d!=0)
                System.out.println(d + " записи удалены");
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String petName = rs.getString("petName");
                System.out.println("petName: " + petName);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if(statement!=null)
                statement.close();
            if (connection!=null)
                connection.close();
        }
    }
}
