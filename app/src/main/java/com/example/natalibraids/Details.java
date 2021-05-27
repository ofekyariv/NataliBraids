package com.example.natalibraids;

public class Details {

    private String cost;
    private String time;

    private String name;
    private int id;
    private String kind;

    public Details(String cost, String time, String name, int id) {
        this.cost = cost;
        this.time = time;
        this.name = name;
        this.id = id;

        if (id>=0 && id<=2){
            kind= "Gathered Hair";
        }
        else if (id> 2 && id<=10){
            kind= "Scattered Hair";
        }
        else {
            kind= "Half Gathered Hair And Half Scattered Hair";
        }

    }

    public String toString() {
        String str = "cost:" + this.cost + "\n" + "How long it takes:" + this.time;
        return str;
    }
    public String getCost(){
        return this.cost;
    }
}
