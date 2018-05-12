package dataTable.departments;

import dataTable.employees.Employee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Department {
    private DepartmentTable dptD;
    private String name;

    public Department(String name) throws Exception{
        this.name = name;
        if (DepartmentsTable.getInstance().containsOf(name)) {
            this.dptD = DepartmentsTable.getInstance().addDepartment(this);
        }else{
            this.dptD = DepartmentsTable.getInstance().addDepartment(this);
        }
    }

    public Department(DepartmentTable dptD, String name){
        this.dptD = dptD;
        this.name = name;
    }

    public String getName(){
        return name;
    }


    public DepartmentTable getDepartmentTable(){
        return dptD;
    }
    public void addEmployee(Employee emp) throws Exception{
        dptD.addEmployeeToDepartment(emp, new CalendarDep(""));
    }

    public void deleteEmployee(Employee emp) throws Exception{
        dptD.deleteEmployeeFromDepartment(emp);
    }

    public void updateEmployeesCalendar(Employee emp, CalendarDep cal) throws Exception{
        dptD.updateCalendar(emp, cal);
    }

    public CalendarDep getCalendar(Employee emp) throws Exception{
        return dptD.getCalendar(emp);
    }

    public ArrayList<Employee> getEmployees(){
        return dptD.getEmployees();
    }

    @Override
    public String toString(){
        return name;
    }
}
