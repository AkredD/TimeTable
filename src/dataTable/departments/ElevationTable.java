package dataTable.departments;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.StringTokenizer;


public class ElevationTable {
    private static Connection eln;
    private HashMap<String, String> elnS;

    private static ElevationTable instance;
    private ElevationTable() throws Exception{
        this.elnS = new HashMap<>();
        openTable();
    }

    public static ElevationTable getInstance() throws Exception{
        if (instance == null) {
            instance = new ElevationTable();
        }
        return instance;
    }

    public static void setConnection(Connection connection){
        eln = connection;
    }

    public void openTable() throws Exception{
        try{
            Statement stm = eln.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM ELEVATION");

            elnS.clear();
            while(res.next()){
                elnS.put(res.getString("SHORTNAME"), res.getString("DESCRIPTION"));
            }
            res.close();
            stm.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new Exception("Can't open elevation table");
        }
    }

    public void addElavation(String shtName, String dspn) throws Exception{
        if (!elnS.containsKey(shtName)) {
            try {
                Statement stm = eln.createStatement();
                stm.executeUpdate("INSERT INTO ELEVATION (SHORTNAME, DESCRIPTION) " +
                        "VALUES (" + "'" + shtName + "'" + ", " + "'" + dspn + "'" + ")");
                elnS.put(shtName, dspn);
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new Exception("Can't add new elevation to DB");
            }
        }
    }


    public HashMap<String, String> getElavations(){
        return elnS;
    }

    public boolean containsOf(String shortName){
        return elnS.containsKey(shortName);
    }
}
