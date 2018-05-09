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
    private Connection eln;
    private String name;
    private HashMap<String, String> elnS;

    public ElevationTable(String name) throws Exception{
        this.name = name;
        this.eln = DepartmentsTable.getInstance().getConnection();
        elnS = new HashMap<>();
    }

    public void createTable() throws Exception{
        try{
            Statement stm = eln.createStatement();
            stm.executeUpdate("CREATE TABLE " + name + " " +
                                "(SHORTNAME           TEXT    NOT NULL, " +
                                "DESCRIPTION    TEXT    NOT NULL)");
            stm.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new Exception("Creation elevation table failed");
        }
    }

    public void openTable() throws Exception{
        try{
            Statement stm = eln.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM " + name);

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
                stm.executeUpdate("INSERT INTO " + name + " (SHORTNAME, DESCRIPTION) " +
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
}
