package com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition;

/**
 *
 * @author Sarunas Sarakojis
 */
public interface Issue {

    int getUniqueIdentifier();
    String getSummary();
    String getDescription();
    void setSummary(String newSummary);
    void setDescription(String newDescription);
}
