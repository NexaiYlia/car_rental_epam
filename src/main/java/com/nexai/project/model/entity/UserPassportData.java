package com.nexai.project.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class UserPassportData implements Serializable {

    private static final long serialVersionUID = -6077599575954635719L;

    private String passportNumber;
    private String identificationNumber;
    private LocalDate issueDate;

    public UserPassportData() {}

    public UserPassportData(String passportNumber, String identificationNumber, LocalDate issueDate) {
        this.passportNumber = passportNumber;
        this.identificationNumber = identificationNumber;
        this.issueDate = issueDate;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPassportData passport = (UserPassportData) o;

        if (passportNumber != null ? !passportNumber.equals(passport.passportNumber) : passport.passportNumber != null)
            return false;
        if (identificationNumber != null ? !identificationNumber.equals(passport.identificationNumber) : passport.identificationNumber != null)
            return false;
        return issueDate != null ? issueDate.equals(passport.issueDate) : passport.issueDate == null;
    }

    @Override
    public int hashCode() {
        int result = passportNumber != null ? passportNumber.hashCode() : 0;
        result = 31 * result + (identificationNumber != null ? identificationNumber.hashCode() : 0);
        result = 31 * result + (issueDate != null ? issueDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Passport{");
        sb.append("passportNumber='").append(passportNumber).append('\'');
        sb.append(", identificationNumber='").append(identificationNumber).append('\'');
        sb.append(", issueDate=").append(issueDate);
        sb.append('}');
        return sb.toString();
    }
}
