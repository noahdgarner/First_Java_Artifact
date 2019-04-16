import java.sql.*;
//simple jdbc util class for executing sql queries across postgresql server

public class JDBCUtil {

    String className, URL, user, password;
    Connection connection;
    public JDBCUtil(String className, String URL, String user, String password) {
        this.className = className;
        this.URL = URL;
        this.user = user;
        this.password = password;
        this.connection = null;
    }


    //exactly the same as any other kind of database connectivity such as python pconn objects
    public void getConnection() {
        //Load the driver class
        try {
            Class.forName(className);


            //error handling
        } catch (ClassNotFoundException ex) {
            System.out.println("Unable to load the class. Terminating the program");
            System.exit(-1);
        }
        //get the connection
        try {
            connection = DriverManager.getConnection(URL, user, password);


            //error handling
        } catch (SQLException ex) {
            System.out.println("Error getting connection: " + ex.getMessage());
            System.exit(-1);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            System.exit(-1);
        }
    }


    public void executeQuery(String query)
    {
        ResultSet resultSet = null;
        try
        {
            //executing query
            Statement stmt = connection.createStatement();
            resultSet = stmt.executeQuery(query);
            //Get Number of columns
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnsNumber = metaData.getColumnCount();
            //Printing the results
            while(resultSet.next())
            {
                for(int i = 1; i <= columnsNumber; i++)
                {
                    System.out.printf("%-25s\n", (resultSet.getObject(i) != null)?resultSet.getObject(i).toString(): null );
                }
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Exception while executing statement. Terminating program... " + ex.getMessage());
        }
        catch (Exception ex)
        {
            System.out.println("General exception while executing query. Terminating the program..." + ex.getMessage());
        }
    }



    public static  void main(String [] args) {

            //this is simply the name of the driver we installed under Progress / postgress for postgressql
            String className = "org.postgresql.Driver";
            String url = "jdbc:datadirect:postgresql://localhost:5432;DatabaseName=postgres";

            String userName = "postgres";
            String pass = "Cheeseburger12?!";

            //create the JDBCUtil object
            JDBCUtil jdbcUtil = new JDBCUtil(className, url, userName, pass);
            //create the connection
            jdbcUtil.getConnection();



            //todo: experiment with this Friday
           //jdbcUtil.executeQuery("Select * from public.customer_list");



    }
}