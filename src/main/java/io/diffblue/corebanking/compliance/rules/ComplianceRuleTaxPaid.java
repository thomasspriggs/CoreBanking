package io.diffblue.corebanking.compliance.rules;

import io.diffblue.corebanking.account.Account;

public class ComplianceRuleTaxPaid extends ComplianceRule {

    private final int LIMIT_PERSONAL_ALLOWANCE = 12_570;
    private final int LIMIT_BASIC_RATE = 50_270;
    private final int LIMIT_HIGHER_RATE = 125_140;

    public void validateAccountCompliance(Account account) {
        // TODO implement!
    }

    public class TaxDue {
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


    public TaxDue calculateTax(int incomeEarnedThisYear)
    {
        if(incomeEarnedThisYear < LIMIT_PERSONAL_ALLOWANCE)
            return new TaxDue(0, "No tax due as income is below personal allowance limit");
        // Implement me!
        return null;
    }
}
