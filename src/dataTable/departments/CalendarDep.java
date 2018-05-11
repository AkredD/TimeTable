package dataTable.departments;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;


public class CalendarDep {
    ArrayList<ArrayList<Pair<String, Integer>>> calendar;

    public CalendarDep(String res) throws Exception{
        calendar = new ArrayList<>();
        calendar.add(fill(31)); //january
        calendar.add(fill(28));
        calendar.add(fill(31));
        calendar.add(fill(30));
        calendar.add(fill(31));
        calendar.add(fill(30));
        calendar.add(fill(31));
        calendar.add(fill(31));
        calendar.add(fill(30));
        calendar.add(fill(31));
        calendar.add(fill(30));
        calendar.add(fill(31));
        if (!res.equals("")) {
            StringTokenizer tokenizer = new StringTokenizer(res, "&");
            try {
                for (int i = 0; i < calendar.size(); ++i) {
                    for (int j = 0; j < calendar.get(i).size(); ++j) {
                        String pos = tokenizer.nextToken();
                        StringTokenizer ps = new StringTokenizer(pos, "|");
                        calendar.get(i).set(j, new Pair<>(ps.nextToken(), Integer.parseInt(ps.nextToken())));
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                //throw new Exception("Parse calendat failed");
            }
        }
    }

    private ArrayList<Pair<String, Integer>> fill(int n){
        ArrayList<Pair<String, Integer>> a = new ArrayList<>();
        for (int i = 0; i < n; ++i) a.add(new Pair<>(" ", 0));
        return a;
    }

    public Pair<String, Integer> getPair(int i, int j){
        return calendar.get(i).get(j);
    }

    public CalendarDep setValue(int i, int j, String newValue){
        calendar.get(i).set(j, new Pair<>(newValue, 0));
        return this;
    }

    public ArrayList<Pair<String, Integer>> getMonth(int i) {return calendar.get(i);}

    @Override
    public String toString(){
        StringBuilder a = new StringBuilder();
        for (int i = 0; i < calendar.size() - 1; ++i) {
            a.append(calendar.get(i).get(0).getKey());
            a.append("|");
            a.append(calendar.get(i).get(0).getValue());
            for (int j = 1; j < calendar.get(i).size(); ++j) {
                a.append("&");
                a.append(calendar.get(i).get(j).getKey());
                a.append("|");
                a.append(calendar.get(i).get(j).getValue());
            }
            a.append("&");
        }
        int i = calendar.size() - 1;
        a.append(calendar.get(i).get(0).getKey());
        a.append("|");
        a.append(calendar.get(i).get(0).getValue());
        for (int j = 1; j < calendar.get(i).size(); ++j) {
            a.append("&");
            a.append(calendar.get(i).get(j).getKey());
            a.append("|");
            a.append(calendar.get(i).get(j).getValue());
        }
        return a.toString();
    }
}
