package io.diffblue.corebanking.compliance.rules;

public final class TaxDue {
    int amount;
    String description;

    TaxDue(int amount, String description)
    {
        this.amount = amount;
        this.description = description;
    }

    int getAmount() {
        return amount;
    }

    String getDescription() {
        return description;
    }
}
