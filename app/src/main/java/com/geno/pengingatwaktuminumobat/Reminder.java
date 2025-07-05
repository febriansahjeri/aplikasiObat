package com.geno.pengingatwaktuminumobat;

public class Reminder {
    private int id;
    private String name;
    private int hour, minute;

    public Reminder() {}
    public Reminder(int id, String name, int hour, int minute) {
        this.id = id; this.name = name; this.hour = hour; this.minute = minute;
    }
    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getHour() { return hour; }
    public void setHour(int hour) { this.hour = hour; }
    public int getMinute() { return minute; }
    public void setMinute(int minute) { this.minute = minute; }
}
