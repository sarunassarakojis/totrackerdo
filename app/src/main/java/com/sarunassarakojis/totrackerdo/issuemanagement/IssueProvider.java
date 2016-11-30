package com.sarunassarakojis.totrackerdo.issuemanagement;

import com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.Issue;
import com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.IssueType;

/**
 * @author Sarunas Sarakojis
 */
public abstract class IssueProvider {

    private static TODOIssueTypeProvider todoIssueTypeProvider = new TODOIssueTypeProvider();
    private static FlawIssueTypeProvider flawIssueTypeProvider = new FlawIssueTypeProvider();

    public static IssueProvider getConcreteIssueProvider(IssueType issueType) {
        switch (issueType) {
            case TODO:
                return todoIssueTypeProvider;
            case FLAW:
                return flawIssueTypeProvider;
            default:
                throw new IllegalArgumentException("Illegal type: " + issueType);
        }
    }

    public abstract Issue createIssue(String issueSummary);
    public abstract Issue createIssue(String issueSummary, String issueDescription);
}
