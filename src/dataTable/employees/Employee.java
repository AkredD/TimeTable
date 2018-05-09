package dataTable.employees;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class Employee {
    private String fstName;
    private String scnName;
    private String brthd;
    private int age;
    private String position;
    private String address;
    private int access;
    private int ID;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public IntegerProperty getIdProperty(){
        return new SimpleIntegerProperty(ID);
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFstName() {
        return fstName;
    }

    public StringProperty getFstNameProperty() {
        return new SimpleStringProperty(fstName);
    }

    public void setFstName(String fstName) {
        this.fstName = fstName;
    }

    public String getScnName() {
        return scnName;
    }

    public StringProperty getScnNameProperty(){
        return new SimpleStringProperty(scnName);
    }

    public void setScnName(String scnName) {
        this.scnName = scnName;
    }

    public String getBrthd() {
        return brthd;
    }

    public StringProperty getBrthdProperty(){
        return new SimpleStringProperty(brthd);
    }

    public void setBrthd(String brthd) {
        this.brthd = brthd;
    }

    public int getAge() {
        return age;
    }

    public IntegerProperty getAgeProperty(){
        return new SimpleIntegerProperty(age);
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public StringProperty getPositionProperty(){
        return new SimpleStringProperty(position);
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public StringProperty getAddressProperty(){
        return new SimpleStringProperty(address);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAccess() {
        return access;
    }

    public IntegerProperty getAccessProperty(){
        return new SimpleIntegerProperty(access);
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public Employee(String password, int ID, String fstName, String scnName, String brthd, int age,
                    String position, String address, int access){
        this.password = password;
        this.access = access;
        this.address = address;
        this.age = age;
        this.brthd = brthd;
        this.fstName = fstName;
        this.scnName = scnName;
        this.ID = ID;
        this.position = position;
    }
}
