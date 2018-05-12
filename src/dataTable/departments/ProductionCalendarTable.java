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


public class ProductionCalendarTable {
    private CalendarDep calendarDep;
    private static Connection connection;
    private static ProductionCalendarTable instance;
    private ProductionCalendarTable() throws Exception{
        openTable();
    }

    public static ProductionCalendarTable getInstance() throws Exception{
        if (instance == null) {
            instance = new ProductionCalendarTable();
        }
        return instance;
    }

    public static void setConnection(Connection eln){
        connection = eln;
    }

    private void openTable() throws Exception{
        try{
            Statement stm = connection.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM PRODUCTIONCALENDAR");
            while(res.next()){
                calendarDep = new CalendarDep(res.getString("CALENDAR"));
            }
            res.close();
            stm.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new Exception("Can't open production calendar table");
        }
    }

    public void updateCalendar(CalendarDep calendarDepUp) throws Exception{
        //this.calendarDep = calendarDep;
        try{
            Statement stm = connection.createStatement();
            stm.executeUpdate("UPDATE PRODUCTIONCALENDAR SET CALENDAR = " + "'" + calendarDepUp.toString() + "'");
            this.calendarDep = calendarDep;
            stm.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new Exception("Updating production calendar failed");
        }
    }

    public CalendarDep getProductionCalendar(){
        return calendarDep;
    }
}
