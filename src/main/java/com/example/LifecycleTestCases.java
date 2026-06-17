package com.example;

import java.util.ArrayList;
import java.util.List;

public class LifecycleTestCases {

    //instance variables
    private String status;
    private List<String> eventLog;
    private int counter;

    //constructor
    public LifecycleTestCases(){
        this.status = "NEW";
        this.eventLog = new ArrayList<>();
        this.counter = 0;
    }

    //Status methods
    public String getStatus(){
        return status;
    }
    public void activate(){
        if(status.equals("NEW")|| status.equals("INACTIVE")){
            status = "ACTIVE";
        }
    }
    public void deactivate(){
        if(status.equals("ACTIVE")){
            status = "INACTIVE";
        }
    }
    public boolean isActive(){
        return status.equals("ACTIVE");
    }

    //Event log methods
    public void logEvent(String event){
        if(event == null||event.isBlank()) {
            throw new IllegalArgumentException("Event cannot be null or blank!");
        }
        eventLog.add(event);
    }
    public List<String> getEventLog(){
        return new ArrayList<>(eventLog);
    }
    public void clearLog(){
        eventLog.clear();
    }
    public int getEventCount(){
        return eventLog.size();
    }

    //Counter methods
    public void increment(){
        counter++;
    }
    public void increment(int amount){
        if(amount <0){
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        counter += amount;
    }
    public void reset(){
        counter =0;
    }
    public int getCounter(){
        return counter;
    }


}
