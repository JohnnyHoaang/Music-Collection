package src.entities;

import java.sql.Date;

public class Recording {
    private String recordingId;
    private Date date;
    private double duration_used;
    private double rec_offset;
   
   //Declare constructor Recording with recid, duration and offset as inputs

    public Recording(String recid, double duration, double offset){
        if(recid==null){
            throw new IllegalArgumentException("Recording is needed");
        }
        this.recordingId = recid;
        this.duration_used = duration;
        this.rec_offset = offset;
    }
    //Declare overloaded constructor Recording with recid, date, duration and offset as inputs full fields
    public Recording(String recordingId, Date date, 
        double duration_used, double rec_offset){

        this(recordingId, duration_used, rec_offset);
        this.date = date;
    }

    //Getter for the recid 
    public String getRecordingId() {
        return this.recordingId;
    }
    //Getter for the date
    public Date getDate() {
        return this.date;
    }
    //Getter for the duration 
    public double getDuration_used() {
        return this.duration_used;
    }
    //Getter for the offset
    public double getRec_offset() {
        return this.rec_offset;
    }
    //toString method 
    public String toString(){
        String printing = "RecordingId: "+this.recordingId+" | Date: "+this.date+
            " | Duration: "+this.duration_used+" | Offset: "+this.rec_offset;
        return printing;
    }

}
