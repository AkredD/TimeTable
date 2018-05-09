package dataTable.departments;

import dataTable.employees.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;


public class DepartmentsTable {
    private static DepartmentsTable instance;
    private HashMap<String, Department> departments;
    private static Connection dpnts;
    private int maxID;

    private DepartmentsTable() throws Exception{
        departments = new HashMap<>();
        //initialize();
    }

    public static DepartmentsTable getInstance() throws Exception{
        if (instance == null){
            instance = new DepartmentsTable();
        }
        return instance;
    }

    public static void setConnection(Connection c){
        dpnts = c;
    }

    public Connection getConnection(){
        return dpnts;
    }

    public void initialize() throws Exception{
        try{
            departments.clear();
            Statement stm = dpnts.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM DEPARTMENTS");
            while(rs.next()){
                departments.put(rs.getString("NAME"), new Department(
                        new DepartmentTable(dpnts, rs.getString("NAME")), rs.getString("NAME")));
            }
            ++maxID;
            rs.close();
            stm.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new Exception("Initializing DepartmentsTable failed");
        }
    }

    public HashMap<String, Department> getDepartments() throws Exception{
        //initialize();
        return departments;
    }

    public Department getDepartment(String name){
        if (containsOf(name)){
            return departments.get(name);
        }
        return null;
    }

    public boolean containsOf(String name){
        return departments.containsKey(name);
    }

    public DepartmentTable addDepartment(Department dp) throws Exception{
        String name = dp.getName();
        if (!containsOf(name)){
            try{
                Statement stm = dpnts.createStatement();
                stm.executeUpdate("INSERT INTO DEPARTMENTS (NAME, ID, ELEVATION ) " +
                        "VALUES (" + "'" + name + "'" + ", " + maxID++ + ", " + "'" + name + "Elevation" + "'" + ")");
                stm.close();

                DepartmentTable a = new DepartmentTable(dpnts, new ArrayList<>(), new ArrayList<>(), name);
                departments.put(name, dp);
                return a;
            }catch (Exception e){
                System.out.println(e.getMessage());
                throw new Exception("Can't add new department");
            }
        }else return new DepartmentTable(dpnts, name);
    }

    public void deleteDepartment(Department dep) throws Exception{
        if (containsOf(dep.getName())){
            try{
                Statement stm = dpnts.createStatement();
                stm.executeUpdate("DELETE FROM DEPARTMENTS WHERE NAME = " + "'" + dep.getName() + "'");
                stm.executeUpdate("DROP TABLE " + dep.getName());
                departments.remove(dep.getName());
                stm.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
                throw new Exception("Can't delete department");
            }
        }
    }

    public void renameDepartment(Department dep, String newName) throws Exception{
        if (!departments.containsKey(newName)) {
            DepartmentTable a = dep.getDepartmentTable();
            ArrayList<CalendarDep> calendarDeps = a.getCalendars();
            ArrayList<Employee> employees = a.getEmployees();
            deleteDepartment(dep);
            a = new DepartmentTable(dpnts, employees, calendarDeps, newName);
            Department loc = new Department(a, newName);
            departments.put(newName, dep);
        }
    }
}
