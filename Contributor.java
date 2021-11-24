public class Contributor {
    private String contributorId;
    private String instruments;
    private String songId;
    private String recordingId;
    private String roleId;

    public Contributor(String contributorId, String instruments, String songId, String recordingId, String roleId){
        this.contributorId = contributorId;
        this.instruments = instruments;
        this.songId = songId;
        this.recordingId = recordingId;
        this.roleId = roleId;
    }

    public String getContributorId() {
        return contributorId;
    }

    public void setContributorId(String contributorId) {
        this.contributorId = contributorId;
    }

    public String getInstruments() {
        return instruments;
    }

    public void setInstruments(String instruments) {
        this.instruments = instruments;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getRecordingId() {
        return recordingId;
    }

    public void setRecordingId(String recordingId) {
        this.recordingId = recordingId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
