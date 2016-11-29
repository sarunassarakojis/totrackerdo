package com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition;

/**
 *
 */
public class TODOIssue implements Issue {

    private String todoIssueSummary;
    private String todoIssueDescription;

    public TODOIssue(String todoIssueSummary, String todoIssueDescription) {
        this.todoIssueSummary = todoIssueSummary;
        this.todoIssueDescription = todoIssueDescription;
    }

    @Override
    public String getSummary() {
        return todoIssueSummary;
    }

    @Override
    public String getDescription() {
        return todoIssueDescription;
    }
}
