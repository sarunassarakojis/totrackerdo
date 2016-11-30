package com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition;

/**
 *
 * @author Sarunas Sarakojis
 */
public interface Issue {

    String getSummary();
    String getDescription();
    void setSummary(String newSummary);
    void setDescription(String newDescription);
}
