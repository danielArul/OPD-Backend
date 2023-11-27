package com.daniel.simpleOPD.dto;


public class CompleteAppointment {
    private boolean approval;
    private String notes;

    public boolean isApproval() {
        return approval;
    }

    public void setApproval(boolean approval) {
        this.approval = approval;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
