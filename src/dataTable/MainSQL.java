package dataTable;

import dataTable.departments.CalendarDep;
import dataTable.departments.Department;
import dataTable.departments.DepartmentsTable;
import dataTable.departments.ProductionCalendarTable;
import dataTable.employees.Employee;
import dataTable.employees.EmployeeTable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.sql.*;

public class MainSQL {
    public static void main(String[] args) {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:timeTable.db");
            //System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS EMPLOYEE " +
                    "(PASSWORD      TEXT    NOT NULL, " +
                    " ID            INT     NOT NULL," +
                    " FIRSTNAME     TEXT    NOT NULL, " +
                    " LASTNAME      TEXT     NOT NULL, " +
                    " BIRTHDAY      TEXT    NOT NULL, " +
                    " AGE           INT     NOT NULL, " +
                    " POSITIONW     TEXT    NOT NULL, " +
                    " ADDRESS       TEXT    NOT NULL, " +
                    " ACCESS        INT     NOT NULL)";
            stmt.executeUpdate(sql);
            sql = "CREATE  TABLE IF NOT EXISTS DEPARTMENTS " +
                    "(NAME          TEXT    NOT NULL, " +
                    " ID            INT     NOT NULL, " +
                    " ELEVATION     TEXT    NOT NULL)";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS ELEVATION " +
                    "(SHORTNAME             TEXT    NOT NULL, " +
                    "DESCRIPTION            TEXT    NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS PRODUCTIONCALENDAR " +
                    "(CALENDAR              TEXT    NOT NULL)");
            CalendarDep calendarDep = createProductionCalendar();
            stmt.executeUpdate("DELETE FROM PRODUCTIONCALENDAR");
            stmt.executeUpdate("INSERT INTO PRODUCTIONCALENDAR (CALENDAR) VALUES ( '" + calendarDep.toString() +"' )");
            stmt.executeUpdate("DELETE FROM ELEVATION");
            stmt.executeUpdate("INSERT INTO ELEVATION ( SHORTNAME, DESCRIPTION ) " +
                    "VALUES ( 'F', 'Full time day' )");
            stmt.executeUpdate("INSERT INTO ELEVATION ( SHORTNAME, DESCRIPTION ) " +
                    "VALUES ( 'N', 'Absence from work for unexplained reasons' )");
            stmt.executeUpdate("INSERT INTO ELEVATION ( SHORTNAME, DESCRIPTION ) " +
                    "VALUES ( 'W', 'Weekends and holidays' )");
            stmt.executeUpdate("INSERT INTO ELEVATION ( SHORTNAME, DESCRIPTION ) " +
                    "VALUES ( 'WW', 'Work on holidays and weekends; as well as work on holidays and weekends, with\n" +
                    "being on a business trip' )");
            stmt.executeUpdate("INSERT INTO ELEVATION ( SHORTNAME, DESCRIPTION ) " +
                    "VALUES ( 'T', 'Days of temporary incapacity for work' )");
            stmt.executeUpdate("INSERT INTO ELEVATION ( SHORTNAME, DESCRIPTION ) " +
                    "VALUES ( 'B', 'Business days, as well as days off when on a business trip, when\n" +
                    "employee rests' )");
            stmt.executeUpdate("INSERT INTO ELEVATION ( SHORTNAME, DESCRIPTION ) " +
                    "VALUES ( 'PV', 'Annual basic paid vacation' )");
            stmt.executeUpdate("INSERT INTO ELEVATION ( SHORTNAME, DESCRIPTION ) " +
                    "VALUES ( 'UPV', 'Unpaid leave (leave at own expense)' )");
            stmt.executeUpdate("INSERT INTO ELEVATION ( SHORTNAME, DESCRIPTION ) " +
                    "VALUES ( 'PS', 'Leave for the period of study' )");
            stmt.executeUpdate("INSERT INTO ELEVATION ( SHORTNAME, DESCRIPTION ) " +
                    "VALUES ( 'CP', 'Leave to care for the child' )");
            //(NAME, ID, ELEVATION)
            stmt.close();
            DepartmentsTable.setConnection(c);
            EmployeeTable.setConnection(c);
            ProductionCalendarTable.setConnection(c);


            EmployeeTable.getInstance().addEmployee(new Employee("1234",0, "admin", "admin", "0.0.0",
                    -1, "admin", "server", 0));
            EmployeeTable.getInstance().addEmployee(new Employee("1234", 1, "departmentAdministrator", "admin",
                    "0.0.0", -1, "admin", "server", 1));
            EmployeeTable.getInstance().addEmployee(new Employee("1234", 2, "employeeAdministrator", "admin",
                    "0.0.0", -1, "admin", "server", 2));
            EmployeeTable.getInstance().addEmployee(new Employee("1234", 3, "tableAdministrator", "admin",
                    "0.0.0", -1, "admin", "server", 3));
            //ProductionCalendarTable.getInstance().updateCalendar(calendarDep);
            /*EmployeeTable.getInstance().addEmployee(new Employee("1234",1, "admin", "admin", "c",
                    30, "low", "www.low", 1));
            Department dp = new Department("firstTablea");
            dp.addEmployee(EmployeeTable.getInstance().getEmployee(1));*/
            /*
            Employee emp = EmployeeTable.getInstance().getEmployee(1);

            System.out.println(emp.getFstName());
            Department dp = new Department("firstTablea");
            Department dp2 = new Department("second");
            Department dp3 = new Department("ts");
            //Department dpa = DepartmentsTable.getInstance().getDepartment("firstTablea");
            if (DepartmentsTable.getInstance().containsOf("firstTablea")) {
                dp.addEmployee(emp);
                dp2.addEmployee(emp);
                dp3.addEmployee(emp);
            }
            dp.deleteEmployee(emp);
            ArrayList<String> deps = EmployeeTable.getInstance().getEmployeesDepartments(emp);
            for (int i = 0; i < deps.size(); ++i){
                System.out.println(deps.get(i));
            }*/

            /*dp.getEmployees();
            dp.deleteEmployee(emp);
            dp.getEmployees();
            dp.addEmployee(emp);
            //dp.updateEmployeesCalendar(emp, new CalendarDep("dsa"));
            dp.getCalendar(emp);*/
            /*EmployeeTable.getInstance().setConnection(c);


            EmployeeTable.getInstance().deleteEmployee(emp);
            //if (emp != null) System.out.println(emp.getFstName());
            EmployeeTable.getInstance().deleteEmployee(emp);
            if(EmployeeTable.getInstance().containsOf(emp)){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }*/

            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        //System.out.println("Table created successfully");

    }

    private static CalendarDep createProductionCalendar(){
        CalendarDep calendarDep;
        try {
            calendarDep = new CalendarDep("");
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        calendarDep.setValue(0, 0, "H");
        calendarDep.setValue(0, 1, "H");
        calendarDep.setValue(0, 2, "H");
        calendarDep.setValue(0, 3, "H");
        calendarDep.setValue(0, 4, "H");
        calendarDep.setValue(0, 5, "H");
        calendarDep.setValue(0, 6, "H");
        calendarDep.setValue(0, 7, "H");
        calendarDep.setValue(0, 12, "W");
        calendarDep.setValue(0, 13, "W");
        calendarDep.setValue(0, 19, "W");
        calendarDep.setValue(0, 20, "W");
        calendarDep.setValue(0, 26, "W");
        calendarDep.setValue(0, 27, "W");
        calendarDep.setValue(1,2, "W");
        calendarDep.setValue(1,3, "W");
        calendarDep.setValue(1,9, "W");
        calendarDep.setValue(1,10, "W");
        calendarDep.setValue(1,16, "W");
        calendarDep.setValue(1,17, "W");
        calendarDep.setValue(1,21, "PH");
        calendarDep.setValue(1,22, "H");
        calendarDep.setValue(1,23, "H");
        calendarDep.setValue(1,24, "H");
        calendarDep.setValue(2,2,"W");
        calendarDep.setValue(2,3,"W");
        calendarDep.setValue(2,6,"PH");
        calendarDep.setValue(2,7,"H");
        calendarDep.setValue(2,8,"H");
        calendarDep.setValue(2,9,"H");
        calendarDep.setValue(2,10,"H");
        calendarDep.setValue(2,16,"W");
        calendarDep.setValue(2,17,"W");
        calendarDep.setValue(2,23,"W");
        calendarDep.setValue(2,24,"W");
        calendarDep.setValue(2,30,"W");
        calendarDep.setValue(3, 0,"W");
        calendarDep.setValue(3, 6,"W");
        calendarDep.setValue(3, 7,"W");
        calendarDep.setValue(3, 13,"W");
        calendarDep.setValue(3, 14,"W");
        calendarDep.setValue(3, 20,"W");
        calendarDep.setValue(3, 21,"W");
        calendarDep.setValue(3, 27,"PH");
        calendarDep.setValue(3, 28,"H");
        calendarDep.setValue(3, 29,"H");
        calendarDep.setValue(4, 0,"H");
        calendarDep.setValue(4, 1,"H");
        calendarDep.setValue(4, 7,"PH");
        calendarDep.setValue(4, 8,"H");
        calendarDep.setValue(4, 4,"W");
        calendarDep.setValue(4, 5,"W");
        calendarDep.setValue(4, 11,"W");
        calendarDep.setValue(4, 12,"W");
        calendarDep.setValue(4, 18,"W");
        calendarDep.setValue(4, 19,"W");
        calendarDep.setValue(4, 25,"W");
        calendarDep.setValue(4, 26,"W");
        calendarDep.setValue(5, 1,"W");
        calendarDep.setValue(5, 2,"W");
        calendarDep.setValue(5, 15,"W");
        calendarDep.setValue(5, 16,"W");
        calendarDep.setValue(5, 22,"W");
        calendarDep.setValue(5, 23,"W");
        calendarDep.setValue(5, 29,"W");
        calendarDep.setValue(5, 8,"PH");
        calendarDep.setValue(5, 9,"H");
        calendarDep.setValue(5, 10,"H");
        calendarDep.setValue(5, 11,"H");
        calendarDep.setValue(6, 0,"W");
        calendarDep.setValue(6, 6,"W");
        calendarDep.setValue(6, 7,"W");
        calendarDep.setValue(6, 13,"W");
        calendarDep.setValue(6, 14,"W");
        calendarDep.setValue(6, 20,"W");
        calendarDep.setValue(6, 21,"W");
        calendarDep.setValue(6, 27,"W");
        calendarDep.setValue(6, 28,"W");
        calendarDep.setValue(7, 3,"W");
        calendarDep.setValue(7, 4,"W");
        calendarDep.setValue(7, 10,"W");
        calendarDep.setValue(7, 11,"W");
        calendarDep.setValue(7, 17,"W");
        calendarDep.setValue(7, 18,"W");
        calendarDep.setValue(7, 24,"W");
        calendarDep.setValue(7, 25,"W");
        calendarDep.setValue(8, 0,"W");
        calendarDep.setValue(8, 1,"W");
        calendarDep.setValue(8, 7,"W");
        calendarDep.setValue(8, 8,"W");
        calendarDep.setValue(8, 14,"W");
        calendarDep.setValue(8, 15,"W");
        calendarDep.setValue(8, 21,"W");
        calendarDep.setValue(8, 22,"W");
        calendarDep.setValue(8, 28,"W");
        calendarDep.setValue(8, 29,"W");
        calendarDep.setValue(9, 5,"W");
        calendarDep.setValue(9, 6,"W");
        calendarDep.setValue(9, 12,"W");
        calendarDep.setValue(9, 13,"W");
        calendarDep.setValue(9, 19,"W");
        calendarDep.setValue(9, 20,"W");
        calendarDep.setValue(9, 26,"W");
        calendarDep.setValue(9, 27,"W");
        calendarDep.setValue(10, 9,"W");
        calendarDep.setValue(10, 10,"W");
        calendarDep.setValue(10, 16,"W");
        calendarDep.setValue(10, 17,"W");
        calendarDep.setValue(10, 23,"W");
        calendarDep.setValue(10, 24,"W");
        calendarDep.setValue(10, 1,"PH");
        calendarDep.setValue(10, 2,"H");
        calendarDep.setValue(10, 3,"H");
        calendarDep.setValue(10, 4,"H");
        calendarDep.setValue(11, 0,"W");
        calendarDep.setValue(11, 1,"W");
        calendarDep.setValue(11, 7,"W");
        calendarDep.setValue(11, 8,"W");
        calendarDep.setValue(11, 14,"W");
        calendarDep.setValue(11, 15,"W");
        calendarDep.setValue(11, 21,"W");
        calendarDep.setValue(11, 22,"W");
        calendarDep.setValue(11, 29,"W");
        calendarDep.setValue(11, 28,"PH");
        calendarDep.setValue(11, 30,"H");




        return calendarDep;
    }
}
