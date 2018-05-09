package tableGUI.model;

import dataTable.departments.CalendarDep;
import dataTable.departments.Department;
import dataTable.employees.Employee;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class DepartmentModel {
    private Department department;
    private ArrayList<Employee> employees;
    private  ArrayList<CalendarDep> calendars;
    private ArrayList<Pair<Employee, CalendarDep>> tableLine;

    public DepartmentModel(Department department) throws Exception{
        this.department = department;
        this.employees = department.getEmployees();
        this.tableLine = new ArrayList<>();
        calendars = new ArrayList<>();
        for (int i = 0; i < employees.size(); ++i) {
            calendars.add(department.getCalendar(employees.get(i)));
            tableLine.add(new Pair<>(employees.get(i), calendars.get(i)));

        }
    }

    public ArrayList<Employee> getEmployees(){
        return employees;
    }

    public ArrayList<CalendarDep> getCalendars(){
        return calendars;
    }

    public ArrayList<Pair<Employee, CalendarDep>> getTableLine(){
        return tableLine;
    }

}
