package dataTable.departments;

import dataTable.employees.Employee;
import dataTable.employees.EmployeeTable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.StringTokenizer;


public class DepartmentTable {
    private Connection dptn;
    private ArrayList<Employee> emps;
    private HashMap<Employee, CalendarDep> cals;
    //array for calendars
    private String nameDepartment;

    public DepartmentTable(Connection c, ArrayList<Employee> emps, ArrayList<CalendarDep> cals, String name) throws Exception{
        this.nameDepartment = name;
        StringBuilder a = new StringBuilder();
        this.dptn = c;
        this.emps = emps;
        this.cals = new HashMap<>();
        for (int i = 0; i < emps.size(); ++i) this.cals.put(emps.get(i), cals.get(i));
        if (!DepartmentsTable.getInstance().containsOf(name)) {
            initialize();
        }else{
            // -- TODO twrow new Exception()
        }
    }


    public DepartmentTable(Connection c, String name) throws Exception{
        this.dptn = c;
        this.nameDepartment = name;
        this.emps = new ArrayList<>();
        this.cals = new HashMap<>();
        download();
    }

    private void download() throws Exception{
        try{
            Statement stm = dptn.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM " + nameDepartment);

            while(res.next()){
                Employee emp = EmployeeTable.getInstance().getEmployee(res.getInt("IDEMP"));
                emps.add(emp);
                cals.put(emp, downloadCalendar(emp));
            }
            stm.close();
            res.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new Exception("Can't open department table DB");
        }
    }

    private void initialize() throws Exception{
        try {
            Statement stm = dptn.createStatement();
            stm.executeUpdate("CREATE TABLE " + nameDepartment + " " +
                                "(IDEMP                  INT      NOT NULL, " +
                                " CALENDAR               TEXT     NOT NULL)");
            stm.close();



            for (int i = 0; i < emps.size(); ++i){
                stm = dptn.createStatement();
                stm.executeUpdate("INSERT INTO " + nameDepartment + "(IDEMP, CALENDAR)" +
                                    "VALUES (" + emps.get(i).getID() + ", " + "'" + cals.get(emps.get(i)).toString()  + "'" + ")" );
                stm.close();

            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw new Exception("Creation new department(" + nameDepartment + ") table failed");
        }
    }

    public void addEmployeeToDepartment(Employee emp, CalendarDep calendar) throws Exception{
        if (!emps.contains(emp)){
            try {
                Statement stm = dptn.createStatement();
                stm.executeUpdate("INSERT INTO " + nameDepartment + " ( IDEMP, CALENDAR ) " +
                        "VALUES (" + emp.getID() + ", " + "'" + calendar.toString() + "'" + " )");
                stm.close();

                emps.add(emp);
                cals.put(emp, calendar);
            }catch (Exception e){
                System.out.println(e.getMessage());
                throw new Exception("Can't add employee to " + nameDepartment + " DB");
            }
        }
    }

    public void updateCalendar(Employee emp, CalendarDep calendar) throws Exception{
        if (emps.contains(emp)){
            try{
                Statement stm = dptn.createStatement();
                stm.executeUpdate("UPDATE " + nameDepartment + " SET CALENDAR = " + "'" + calendar.toString() + "'"
                                        + " WHERE IDEMP = " + emp.getID());
                cals.replace(emp, calendar);
                stm.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
                throw new Exception("Updating calendar in department failed in" + nameDepartment + " DB");
            }
        }
    }

    public CalendarDep getCalendar(Employee emp) throws Exception{
        CalendarDep a = null;
        if (emps.contains(emp)){
            return cals.get(emp);

            /**/
        }
        return a;
    }

    public ArrayList<CalendarDep> getCalendars(){
        ArrayList<CalendarDep> calendarDeps = new ArrayList<>();
        cals.forEach((e, r) -> calendarDeps.add(r));
        return calendarDeps;
    }

    private CalendarDep downloadCalendar(Employee emp) throws Exception{
        CalendarDep a = null;
        try{
            Statement stm = dptn.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM " + nameDepartment + " WHERE IDEMP=" +emp.getID());
            while(res.next()){
                a =  new CalendarDep(res.getString("CALENDAR"));
            }
            res.close();
            stm.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new Exception("Can't SELECT from " + nameDepartment + " DB");
        }
        return a;
    }

    public void deleteEmployeeFromDepartment(Employee emp) throws Exception{
        if (emps.contains(emp)){
            try{
                Statement stm = dptn.createStatement();
                stm.executeUpdate(" DELETE FROM " + nameDepartment + " WHERE IDEMP = " + emp.getID());
                stm.close();
                emps.remove(emp);
                cals.remove(emp);
            }catch (Exception e){
                System.out.println(e.getMessage());
                throw new Exception("Can't delete employee from " + nameDepartment + "DB");
            }
        }
    }

    public ArrayList<Employee> getEmployees(){
        return emps;
    }

}
