package MyAppDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
public class databaseConnection {
    public Connection databaseLink ;
    public Connection getConnection() {
        String databaseName="mansifdb" ;
        String databaseUser="root" ;
        String databasePassword ="mabsur2500";
        String url="jdbc:mysql://localhost/"+databaseName;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink=DriverManager.getConnection(url,databaseUser,databasePassword);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return databaseLink;

    }

}