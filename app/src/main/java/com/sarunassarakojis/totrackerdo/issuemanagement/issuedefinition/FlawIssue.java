package com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition;

public class FlawIssue implements Issue {

    private String flawIssueSummary;
    private String flawIssueDescription;

    public FlawIssue(String summary, String description) {
        this.flawIssueSummary = summary;
        this.flawIssueDescription = description;
    }

    @Override
    public String getSummary() {
        return flawIssueSummary;
    }

    @Override
    public String getDescription() {
        return flawIssueDescription;
    }
}
