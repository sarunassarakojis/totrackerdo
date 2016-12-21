package com.sarunassarakojis.totrackerdo.issuemanagement;

import com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.Issue;
import com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.TODOIssue;

/**
 * This <code>class</code> allows to create new {@link TODOIssue issues}.
 *
 * @author Sarunas Sarakojis
 */
public class TODOIssueTypeProvider extends IssueProvider {

    public TODOIssueTypeProvider() {
    }

    @Override
    public Issue createIssue(int uniqueIdentifier, String issueSummary, String issueDescription) {
        return new TODOIssue(uniqueIdentifier, issueSummary, issueDescription);
    }
}
