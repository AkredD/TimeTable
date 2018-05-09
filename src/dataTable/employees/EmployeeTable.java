package dataTable.employees;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;


public class EmployeeTable {
    private static Connection emps;
    private static EmployeeTable instance;
    private static ArrayList<Employee> employees;
    private static HashMap<Integer, Employee> employeeById;

    private EmployeeTable() throws Exception{
        employees = new ArrayList<>();
        employeeById = new HashMap<>();
        initialize();
    }

    public static EmployeeTable getInstance() throws Exception{
        if (instance == null){
            instance = new EmployeeTable();
        }
        return instance;
    }

    private static void initialize() throws Exception{
        Statement stm = emps.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM EMPLOYEE");
        while (rs.next()) {
            employees.add(new Employee(rs.getString("PASSWORD"),
                    rs.getInt("ID"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"),
                    rs.getString("BIRTHDAY"), rs.getInt("AGE"), rs.getString("POSITIONW"),
                    rs.getString("ADDRESS"), rs.getInt("ACCESS")));
            employeeById.put(employees.get(employees.size() - 1).getID(), employees.get(employees.size() - 1));

        }
        rs.close();
        stm.close();
    }

    public static void setConnection(Connection empsl){
        emps = empsl;
    }


    public boolean addEmployee(Employee emp) throws Exception{
        if (!containsOf(emp)) {
            Statement stm = emps.createStatement();
            stm.executeUpdate("INSERT INTO EMPLOYEE (PASSWORD, ID, FIRSTNAME, LASTNAME, BIRTHDAY, AGE, POSITIONW, ADDRESS, ACCESS ) " +
                    "VALUES (" + "'" + emp.getPassword() + "'" + ", " + emp.getID() + ", "
                    + "'" + emp.getFstName() + "'" + ", "  + "'" + emp.getScnName()  + "'" + ", "
                    + "'" + emp.getBrthd() + "'" + ", " + emp.getAge() + ", "  + "'" + emp.getPosition() + "'" + ", "
                    + "'" + emp.getAddress()  + "'" + ", " + emp.getAccess() + " );");
            stm.close();
            employees.add(emp);
            employeeById.put(emp.getID(), emp);
            System.out.println("Emp was added successfully (ID: "+ emp.getID() + ")");
        }else{
            System.out.println("Emp wasn't add (ID: "+ emp.getID() + ")");
            return false;
        }
        return true;
    }

    public boolean deleteEmployee(Employee emp) throws Exception{
        if (containsOf(emp)){
            Statement stm = emps.createStatement();
            stm.executeUpdate("DELETE FROM EMPLOYEE WHERE ID = " + emp.getID());
            employees.remove(emp);
            employeeById.remove(emp.getID());
            System.out.println("Emp was deleted successfully (ID: "+ emp.getID() + ")");
            stm.close();
        }else{
            System.out.println("Emp wasn't deleted (ID: "+ emp.getID() + ")");
            return false;
        }
        return true;
    }

    public Employee getEmployee(Integer ID) throws Exception{
        if (!employeeById.containsKey(ID)) return null;
        return employeeById.get(ID);
    }

    public ArrayList<Employee> getEmployees(){
        return employees;
    }

    public boolean containsOf(Employee emp) throws Exception{
        return employeeById.containsKey(emp.getID());
    }

    public void upToDateEmployee(Employee emp, String field, String value) throws Exception{
        if (containsOf(emp)){
            try{
                Statement stm = emps.createStatement();
                stm.executeUpdate("UPDATE EMPLOYEE SET " + field + "=" + value + " WHERE ID=" + emp.getID());
                stm.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
                throw new Exception("Updating employeeTable failed");
            }
        }
    }


}
