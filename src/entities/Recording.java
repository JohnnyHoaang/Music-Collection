package src.entities;

import java.sql.Date;

public class Recording {
    private String recordingId;
    private String contributionId;
    private double rec_offset;
    private double duration_used;
    private Date date;

    public Recording(String recordingId, Date date,
    String contributionId, double rec_offset, double duration_used){
        this.recordingId = recordingId;
        this.contributionId = contributionId;
        this.rec_offset = rec_offset;
        this.duration_used = duration_used;
    }

    public String getRecordingId() {
        return recordingId;
    }

    public String getContributionId() {
        return contributionId;
    }

    public double getRec_offset() {
        return rec_offset;
    }

    public double getDuration_used() {
        return duration_used;
    }

    public Date getDate() {
        return date;
    }

    public String toString(){
        String printing = "RecordingId: "+this.recordingId+" | Date: "+this.date;
        return printing;
    }

}
