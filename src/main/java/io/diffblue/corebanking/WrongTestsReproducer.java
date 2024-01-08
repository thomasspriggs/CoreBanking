package io.diffblue.corebanking;

import io.diffblue.corebanking.compliance.rules.ComplianceRuleTaxPaid;

public class WrongTestsReproducer {

    public static class TaxDue {
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

    public static TaxDue calculateTax()
    {
        return new TaxDue(0, "Dummy");
    }
}
