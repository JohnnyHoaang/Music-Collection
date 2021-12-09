package src.entities;

import java.sql.Date;

public class Recording {
    private String recordingId;
    private String contributionId;
    private double rec_offset;
    private double duration_used;
    private Date date;

    public Recording(String recid, double duration, double offset){
        if(recid==null){
            throw new IllegalArgumentException("Recording is needed");
        }
        this.recordingId = recid;
        this.duration_used = duration;
        this.rec_offset = offset;
    }
    public Recording(String recordingId, Date date,
    String contributionId, double rec_offset, double duration_used){
        this(recordingId, duration_used, rec_offset);
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
}
