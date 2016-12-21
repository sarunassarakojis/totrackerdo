package com.sarunassarakojis.totrackerdo.issuemanagement;

import com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.Issue;
import com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.IssueType;

/**
 * This <code>class</code> allows to create new {@link Issue issues}
 * based on their types. The logic for each type of the {@link Issue}
 * is in the implementing classes.
 *
 * @author Sarunas Sarakojis
 */
public abstract class IssueProvider {

    private static TODOIssueTypeProvider todoIssueTypeProvider = new TODOIssueTypeProvider();

    public static IssueProvider getConcreteIssueProvider(IssueType issueType) {
        switch (issueType) {
            case TODO:
                return todoIssueTypeProvider;
            default:
                throw new IllegalArgumentException("Illegal type: " + issueType);
        }
    }

    /**
     * Creates an {@link Issue} based on the implementation of <code>this</code> provider.
     *
     * @param uniqueIdentifier unique id
     * @param issueSummary summary
     * @param issueDescription description
     * @return a newly created issue
     */
    public abstract Issue createIssue(int uniqueIdentifier, String issueSummary, String issueDescription);
}
