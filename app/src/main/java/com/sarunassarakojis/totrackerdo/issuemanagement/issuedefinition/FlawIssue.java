package com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition;

public class FlawIssue implements Issue {

    private String flawIssueSummary;
    private String flawIssueDescription;

    public FlawIssue(String summary, String description) {
        this.flawIssueSummary = summary;
        this.flawIssueDescription = description;
    }

    public FlawIssue(String summary) {
        this(summary, "");
    }

    @Override
    public String getSummary() {
        return flawIssueSummary;
    }

    @Override
    public String getDescription() {
        return flawIssueDescription;
    }

    @Override
    public void setSummary(String newSummary) {
        this.flawIssueSummary = newSummary;
    }

    @Override
    public void setDescription(String newDescription) {
        this.flawIssueDescription = newDescription;
    }
}
