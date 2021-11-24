public class Recording {
    private String recordingId;
    private String contributionId;
    private double rec_offset;
    private double duration_used;

    public Recording(String recordingId, 
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

}
