package electricity.billing.system;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    Connection connection;
    Statement statement;
    Database(){
        try{
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bill_system","root","123456789abcdef");
        statement = connection.createStatement();
    }catch(Exception e){
        e.printStackTrace();
    }
}
}
