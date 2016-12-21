package com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition;

/**
 * An implementation of an {@link Issue} that represents a
 * <em>to do</em> item.
 *
 * @author Sarunas Sarakojis
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

    public TODOIssue(String todoIssueSummary, String todoIssueDescription) {
        this(Issue.IDENTIFIER_NOT_SET, todoIssueSummary, todoIssueDescription);
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

    @Override
    public String toString() {
        return "Identifier: " + uniqueIdentifier + ", summary: " + todoIssueSummary;
    }
}
