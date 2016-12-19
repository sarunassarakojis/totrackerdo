package com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition;

/**
 *
 * @author Sarunas Sarakojis
 */
public interface Issue {

    int IDENTIFIER_NOT_SET = -1;

    int getUniqueIdentifier();
    String getSummary();
    String getDescription();
    void setSummary(String newSummary);
    void setDescription(String newDescription);
}
