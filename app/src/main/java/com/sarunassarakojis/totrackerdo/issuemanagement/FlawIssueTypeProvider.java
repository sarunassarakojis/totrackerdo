package com.sarunassarakojis.totrackerdo.issuemanagement;

import com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.FlawIssue;
import com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.Issue;

/**
 * Created by Sarunas on 11/29/2016
 */
public class FlawIssueTypeProvider extends IssueProvider {

    public FlawIssueTypeProvider() {
    }

    @Override
    public Issue createIssue(String issueSummary) {
        return new FlawIssue(issueSummary);
    }

    @Override
    public Issue createIssue(String issueSummary, String issueDescription) {
        return new FlawIssue(issueSummary, issueDescription);
    }
}
