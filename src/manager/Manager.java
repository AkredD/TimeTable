package manager;

import dataTable.departments.Department;
import dataTable.departments.DepartmentsTable;
import dataTable.employees.Employee;
import dataTable.employees.EmployeeTable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;


public class Manager {
    private static Manager instance;
    private static int access = 0;

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        Manager.access = access;
    }



    private Manager(){

    }

    public static Manager getInstance(){
        if (instance == null)
            instance = new Manager();
        return instance;
    }

    public ArrayList<Department> getDepartments(){

        ArrayList<Department> loc = new ArrayList<>();
        try {
            /*Employee c = new Employee("1234",1, "a", "a", "a",
                    30, "a", "a", 0);
            EmployeeTable.getInstance().addEmployee(c);*/
            /*Department a = new Department("first");
            Department b = new Department("second");
            Employee c = new Employee("1234",1, "a", "b", "c",
                    30, "low", "www.low", 1);
            EmployeeTable.getInstance().addEmployee(c);
            c = new Employee("1234",2, "asd", "b", "c",
                    30, "low", "www.low", 1);
            EmployeeTable.getInstance().addEmployee(c);
            a.addEmployee(EmployeeTable.getInstance().getEmployee(1));
            b.addEmployee(EmployeeTable.getInstance().getEmployee(2));
            //Employee d = new Employee("1234",2, "b", "c", "d",
            //        40, "high", "spb", 2);
            b.addEmployee(EmployeeTable.getInstance().getEmployee(1));*/


            HashMap<String, Department> departments = DepartmentsTable.getInstance().getDepartments();
            departments.forEach((name, dep) -> loc.add(dep));

        }catch (Exception e){
            e.printStackTrace();
        }
        return loc;
    }

    public ArrayList<Employee> getEmployees(){
        try {
            return EmployeeTable.getInstance().getEmployees();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
