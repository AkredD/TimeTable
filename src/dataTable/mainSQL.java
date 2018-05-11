package dataTable;

import dataTable.departments.CalendarDep;
import dataTable.departments.Department;
import dataTable.departments.DepartmentsTable;
import dataTable.departments.ElevationTable;
import dataTable.employees.Employee;
import dataTable.employees.EmployeeTable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.sql.*;

public class mainSQL {
    public static void main(String[] args) {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:timeTable.db");
            //System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE EMPLOYEE " +
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
            sql = "CREATE  TABLE DEPARTMENTS " +
                    "(NAME          TEXT    NOT NULL, " +
                    " ID            INT     NOT NULL, " +
                    " ELEVATION     TEXT    NOT NULL)";
            stmt.executeUpdate(sql);
            //(NAME, ID, ELEVATION)
            stmt.close();
            DepartmentsTable.setConnection(c);
            EmployeeTable.setConnection(c);

            EmployeeTable.getInstance().addEmployee(new Employee("1234",0, "admin", "admin", "c",
                    30, "low", "www.low", 1));
            EmployeeTable.getInstance().addEmployee(new Employee("1234",1, "admin", "admin", "c",
                    30, "low", "www.low", 1));
            Department dp = new Department("firstTablea");
            dp.addEmployee(EmployeeTable.getInstance().getEmployee(1));
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
}
