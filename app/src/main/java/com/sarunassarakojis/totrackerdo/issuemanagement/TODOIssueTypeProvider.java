package com.sarunassarakojis.totrackerdo.issuemanagement;

import com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.Issue;
import com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.TODOIssue;

public class TODOIssueTypeProvider implements IssueProvider {

    private String todoIssueSummary;
    private String todoIssueDescription;

    public TODOIssueTypeProvider(String todoIssueSummary, String todoIssueDescription) {
        this.todoIssueSummary = todoIssueSummary;
        this.todoIssueDescription = todoIssueDescription;
    }

    @Override
    public Issue createIssue() {
        return new TODOIssue(todoIssueSummary, todoIssueDescription);
    }
}
