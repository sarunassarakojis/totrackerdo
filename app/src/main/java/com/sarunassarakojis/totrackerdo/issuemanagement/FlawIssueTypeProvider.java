package com.sarunassarakojis.totrackerdo.issuemanagement;

import com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.FlawIssue;
import com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.Issue;

/**
 * Created by Sarunas on 11/29/2016
 */
public class FlawIssueTypeProvider implements IssueProvider {

    private String flawIssueSummary;
    private String flawIssueDescription;

    public FlawIssueTypeProvider(String flawIssueSummary, String flawIssueDescription) {
        this.flawIssueSummary = flawIssueSummary;
        this.flawIssueDescription = flawIssueDescription;
    }

    @Override
    public Issue createIssue() {
        return new FlawIssue(flawIssueSummary, flawIssueDescription);
    }

    public void setFlawIssueSummary(String flawIssueSummary) {
        this.flawIssueSummary = flawIssueSummary;
    }

    public void setFlawIssueDescription(String flawIssueDescription) {
        this.flawIssueDescription = flawIssueDescription;
    }
}
