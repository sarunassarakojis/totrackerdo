package com.sarunassarakojis.totrackerdo.issuemanagement;

import com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.Issue;
import com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.TODOIssue;

public class TODOIssueTypeProvider extends IssueProvider {

    public TODOIssueTypeProvider() {
    }

    @Override
    public Issue createIssue(String issueSummary) {
        return new TODOIssue(issueSummary);
    }

    @Override
    public Issue createIssue(String issueSummary, String issueDescription) {
        return new TODOIssue(issueSummary, issueDescription);
    }
}
