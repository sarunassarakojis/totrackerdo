package com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition;

/**
 *
 */
public class TODOIssue implements Issue {

    private int uniqueIdentifier;
    private String todoIssueSummary;
    private String todoIssueDescription;

    public TODOIssue(int uniqueIdentifier, String todoIssueSummary, String todoIssueDescription) {
        this.uniqueIdentifier = uniqueIdentifier;
        this.todoIssueSummary = todoIssueSummary;
        this.todoIssueDescription = todoIssueDescription;
    }

    public TODOIssue(int uniqueIdentifier, String todoIssueSummary) {
        this(uniqueIdentifier, todoIssueSummary, "");
    }

    @Override
    public int getUniqueIdentifier() {
        return uniqueIdentifier;
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
