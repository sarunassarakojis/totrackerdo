package com.sarunassarakojis.totrackerdo.issuemanagement;

import com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.Issue;

/**
 *
 * @author Sarunas
 */
public class IssueBuilder {

    public static Issue createIssue(IssueProvider issueProvider) {
        return issueProvider.createIssue();
    }
}
