package DatabaseTesting;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;

public class JDBCConnectionTest {

    @Test
    public static void jdbcConnection() throws SQLException {
        //connection to database
       // DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/databasename","username","password");
        String host = "localhost";
        String port = "3306";// by default SQL port
        //databasename = database_QA

        Connection conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/database_QA","root","root");

        //"I want to create a mechanism (Statement object) that can send SQL queries to this database connection."
        Statement statementObj = conn.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,// for pointing and moving ResultSetObj in any direction in table as it cannot move backward
                ResultSet.CONCUR_READ_ONLY // for pointing and moving ResultSetObj in any direction in table as it cannot move backward
        );
        //send SQL query
        ResultSet resultSetObj = statementObj.executeQuery("select * from employee");

        //fetch entire data
        ArrayList<Integer> idColumnDataList = new ArrayList<Integer>();
        ArrayList<String> nameColumnDataList = new ArrayList<String>();
        ArrayList<String> placeColumnDataList = new ArrayList<String>();
        ArrayList<Integer> ageColumnDataList = new ArrayList<Integer>();

        System.out.println("All Column data : ");
        while(resultSetObj.next())
        {
            int  idColumnData =  resultSetObj.getInt("id");
            idColumnDataList.add(idColumnData);

            String nameColumnData =  resultSetObj.getString("name");
            nameColumnDataList.add(nameColumnData);

            String  placeColumnData = resultSetObj.getString("place");
            placeColumnDataList.add(placeColumnData);

            int  ageColumnData =  resultSetObj.getInt("age");
            ageColumnDataList.add(ageColumnData);
        }
        System.out.print("\nData of idColumn of table employee = ");
        idColumnDataList.stream().forEach(s->System.out.print(s+" "));
        System.out.print("\n\nData of nameColumn of table employee = ");
        nameColumnDataList.stream().forEach(s->System.out.print(s+" "));
        System.out.print("\n\nData of placeColumn of table employee = ");
        placeColumnDataList.stream().forEach(s->System.out.print(s+" "));
        System.out.print("\n\nData of  ageColumn of table employee = ");
        ageColumnDataList.stream().forEach(s->System.out.print(s+" "));

        System.out.println("\n\nAll Rows data : ");
        resultSetObj.beforeFirst();
        int rowCount=0;
        while(resultSetObj.next())
        {
            rowCount++;
            int  idColumnData =  resultSetObj.getInt("id");
            String  nameColumnData =  resultSetObj.getString("name");
            String  placeColumnData =  resultSetObj.getString("place");
            int  ageColumnData =  resultSetObj.getInt("age");
            System.out.println("\n"+rowCount+" Row data = "+idColumnData+","+nameColumnData+","+placeColumnData+","+ageColumnData);

        }
        System.out.println("\nSpecific data fetch : ");
        resultSetObj.beforeFirst();
        resultSetObj.first();
        String  nameColumnFirstRowData =  resultSetObj.getString("name");
        String  placeColumnFirstRowData =  resultSetObj.getString("place");
        System.out.println("nameColumnFirstRowData = "+nameColumnFirstRowData);
        System.out.println("placeColumnFirstRowData = "+placeColumnFirstRowData);

        System.out.println("\nSpecific data fetch : ");
        resultSetObj.next();
        String  nameColumnSecondRowData =  resultSetObj.getString("name");
        String  placeColumnSecondRowData =  resultSetObj.getString("place");
        System.out.println("nameColumnSecondRowData = "+nameColumnSecondRowData);
        System.out.println("placeColumnSecondRowData = "+placeColumnSecondRowData);

        System.out.println("\nSpecific data fetch : ");
        resultSetObj.last();
        String  nameColumnLastRowData =  resultSetObj.getString("name");
        String  placeColumnLastRowData =  resultSetObj.getString("place");
        System.out.println("nameColumnLastRowData = "+nameColumnLastRowData);
        System.out.println("placeColumnLastRowData = "+placeColumnLastRowData);

        System.out.println("\nSpecific data fetch : ");
        resultSetObj.absolute(3);// give specific row count
        String  nameColumnThirdRowData =  resultSetObj.getString("name");
        String  placeColumnThirdRowData =  resultSetObj.getString("place");
        System.out.println("nameColumnThirdRowData = "+nameColumnThirdRowData);
        System.out.println("placeColumnThirdRowData = "+placeColumnThirdRowData);
    }



}
