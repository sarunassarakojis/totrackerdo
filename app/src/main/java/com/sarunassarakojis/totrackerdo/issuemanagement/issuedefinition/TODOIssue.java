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

    public TODOIssue(String todoIssueSummary) {
        this(todoIssueSummary, "");
    }

    @Override
    public String getSummary() {
        return todoIssueSummary;
    }

    @Override
    public String getDescription() {
        return todoIssueDescription;
    }

    @Override
    public void setSummary(String newSummary) {
        this.todoIssueSummary = newSummary;
    }

    @Override
    public void setDescription(String newDescription) {
        this.todoIssueDescription = newDescription;
    }
}
