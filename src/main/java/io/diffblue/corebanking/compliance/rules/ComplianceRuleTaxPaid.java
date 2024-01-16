package io.diffblue.corebanking.compliance.rules;

import io.diffblue.corebanking.account.Account;

import static java.lang.Integer.min;

public class ComplianceRuleTaxPaid extends ComplianceRule {
    public ComplianceRuleTaxPaid()
    {
    }

    private final static int LIMIT_PERSONAL_ALLOWANCE = 12_570;
    private final static int BASIC_RATE_PERCENT = 20;
    private final static int LIMIT_BASIC_RATE = 50_270;
    private final static int HIGHER_RATE_PERCENT = 40;
    private final static int LIMIT_HIGHER_RATE = 125_140;
    private final static int ADDITIONAL_RATE = 45;

    public void validateAccountCompliance(Account account) {
        // TODO implement!
    }

    public TaxDue calculateTax(int incomeEarnedThisYear)
    {
        if(incomeEarnedThisYear < LIMIT_PERSONAL_ALLOWANCE)
            return new TaxDue(0, "No tax due as income is below personal allowance limit");
        final int taxable_at_basic_rate = min(incomeEarnedThisYear, LIMIT_BASIC_RATE) - LIMIT_PERSONAL_ALLOWANCE;
        final int basic_rate_tax = (taxable_at_basic_rate * BASIC_RATE_PERCENT) / 100;
        if(incomeEarnedThisYear < LIMIT_BASIC_RATE)
            return new TaxDue(basic_rate_tax, "Basic rate tax is due");
        final int taxable_at_higher_rate = min(incomeEarnedThisYear, LIMIT_HIGHER_RATE) - LIMIT_BASIC_RATE;
        final int higher_rate_tax = (taxable_at_higher_rate * HIGHER_RATE_PERCENT) / 100;
        if(incomeEarnedThisYear < LIMIT_HIGHER_RATE)
            return new TaxDue(basic_rate_tax + higher_rate_tax, "Higher and basic rate tax is due");
        final int taxable_at_additional_rate = incomeEarnedThisYear - ADDITIONAL_RATE;
        final int additional_rate_tax = (taxable_at_additional_rate * ADDITIONAL_RATE) / 100;
        return new TaxDue(additional_rate_tax + basic_rate_tax + higher_rate_tax, "Additional, higher and basic rate tax is due.");
    }
}
