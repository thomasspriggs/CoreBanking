package io.diffblue.corebanking.compliance.rules;

public final class TaxStatus {
    String description;
    TaxDue taxDue;
    int balance;

    TaxStatus(String description, TaxDue taxDue, int balance)
    {
        this.description = description;
        this.taxDue = taxDue;
        this.balance = balance;
    }

    String getDescription() {
        return description;
    }

    TaxDue getTaxDue()
    {
        return taxDue;
    }

    int getBalance()
    {
        return balance;
    }
}
