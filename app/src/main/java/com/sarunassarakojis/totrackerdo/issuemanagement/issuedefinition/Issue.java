package com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition;

/**
 * Provides an abstraction for all possible issue types.
 *
 * @author Sarunas Sarakojis
 */
public interface Issue {

    /**
     * Specifies that the unique identifier is currently not set
     */
    int IDENTIFIER_NOT_SET = -1;

    /**
     * Returns unique identifier for the current issue.
     *
     * @return unique identifier
     */
    int getUniqueIdentifier();

    /**
     * Returns a summary of an {@link Issue}.
     *
     * @return summary
     */
    String getSummary();

    /**
     * Returns a description of an {@link Issue}.
     *
     * @return description
     */
    String getDescription();

    /**
     * Sets a {@code newSummary} as the new one
     *
     * @param newSummary new summary to change to
     */
    void setSummary(String newSummary);

    /**
     * Sets a {@code newDescription} as the new one
     *
     * @param newDescription new description to change to
     */
    void setDescription(String newDescription);
}
