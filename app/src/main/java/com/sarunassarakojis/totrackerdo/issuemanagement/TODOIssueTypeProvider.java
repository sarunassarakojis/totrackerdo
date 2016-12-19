package com.sarunassarakojis.totrackerdo.issuemanagement;

import com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.Issue;
import com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.TODOIssue;

public class TODOIssueTypeProvider extends IssueProvider {

    public TODOIssueTypeProvider() {
    }

    @Override
    public Issue createIssue(int uniqueIdentifier, String issueSummary, String issueDescription) {
        return new TODOIssue(uniqueIdentifier, issueSummary, issueDescription);
    }
}
