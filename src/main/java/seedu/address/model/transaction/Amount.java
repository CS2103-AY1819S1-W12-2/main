package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Currency;
import java.util.Set;


/**
 * Represents the amount of money loaned/owed to end user.
 * Guarantees: immutable; is valid as declared in {@link #isValidAmount(String)}
 */
public class Amount {
    public static final Set<Currency> CURRENCIES = Currency.getAvailableCurrencies();
    public static final String MESSAGE_TRANSACTION_AMOUNT_CONSTRAINTS =
              "The transaction amount must be real number rounded to two decimal places, "
            + "prefixed by a three letter currency code";
    public static final String TRANSACTION_AMOUNT_VALIDATION_REGEX = "\\w{3} \\d{1,}.\\d{2}";
    public final String value;

    /**
     * Constructs an {@code TransactionAmount}.
     *
     * @param amount A valid transaction amount.
     */
    public Amount(String amount) {
        requireNonNull(amount);
        checkArgument(isValidAmount(amount), MESSAGE_TRANSACTION_AMOUNT_CONSTRAINTS);
        value = amount;
    }

    public String getValue() {
        return value;
    }


    /**
     * Returns true if the given string represents a valid transaction amount.
     *
     * @param test the command line argument to be parsed
     */
    public static boolean isValidAmount(String test) {
        return test.matches(TRANSACTION_AMOUNT_VALIDATION_REGEX) && checkCurrency(test);
    }

    /**
     * Compares the currency code of the string {@code test} with the codes of the existing currencies.
     * @param test the command line argument to be parsed
     */
    private static boolean checkCurrency(String test) {
        String testCurrencyCode = test.split(" ")[0].toUpperCase();
        for (Currency currency: CURRENCIES) {
            if (currency.getCurrencyCode().equals(testCurrencyCode)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Amount)) {
            return false;
        }
        Amount amount = (Amount) other;
        return other == this || value.equals(amount.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
