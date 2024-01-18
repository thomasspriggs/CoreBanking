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
        if(incomeEarnedThisYear <= LIMIT_PERSONAL_ALLOWANCE)
            return new TaxDue(0, "No tax due as income is below personal allowance limit");
        final int taxableAtBasicRate = min(incomeEarnedThisYear, LIMIT_BASIC_RATE) - LIMIT_PERSONAL_ALLOWANCE;
        final int basicRateTax = (taxableAtBasicRate * BASIC_RATE_PERCENT) / 100;
        if(incomeEarnedThisYear <= LIMIT_BASIC_RATE)
            return new TaxDue(basicRateTax, "Basic rate tax is due");
        final int taxableAtHigherRate = min(incomeEarnedThisYear, LIMIT_HIGHER_RATE) - LIMIT_BASIC_RATE;
        final int higher_rate_tax = (taxableAtHigherRate * HIGHER_RATE_PERCENT) / 100;
        if(incomeEarnedThisYear <= LIMIT_HIGHER_RATE)
            return new TaxDue(basicRateTax + higher_rate_tax, "Higher and basic rate tax is due");
        final int taxableAtAdditionalRate = incomeEarnedThisYear - ADDITIONAL_RATE;
        final int additionalRateTax = (taxableAtAdditionalRate * ADDITIONAL_RATE) / 100;
        return new TaxDue(additionalRateTax + basicRateTax + higher_rate_tax, "Additional, higher and basic rate tax is due.");
    }

    public TaxStatus checkTax(int incomeEarnedThisYear, int taxPaid)
    {
        TaxDue taxDue = calculateTax(incomeEarnedThisYear);
        if(taxDue.amount == 0)
        {
            if(taxPaid == 0)
            {
                return new TaxStatus("Person is correctly untaxed.", taxDue, 0);
            }
            else
            {
                return new TaxStatus("Person has paid tax when none is payable!", taxDue, 0);
            }
        }
        if(taxPaid == 0)
        {
            return new TaxStatus("Person needs to pay their tax!", taxDue, taxDue.getAmount());
        }
        int balance = taxDue.getAmount() - taxPaid;
        if(balance > 0)
        {
            return new TaxStatus("Tax has been underpaid.", taxDue, balance);
        }
        if(balance < 0)
        {
            return new TaxStatus("Tax has been overpaid.", taxDue, taxDue.getAmount() - taxPaid);
        }
        assert(balance == 0);
        return new TaxStatus("Tax has been correctly paid.", taxDue, balance);
    }
}
