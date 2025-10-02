package com.neokortex.time;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * An Enum representing the twelve months of the year as an enum with support for multiple
 * name aliases.
 * <p>
 * The {@code Month} enum provides functionality to convert between month names and
 * the numeric representations. It supports case-insensitive lookup of
 * months.
 * </p>
 *
 * <p><b>Key Features:</b></p>
 * <ul>
 *   <li>Numeric month representation (1-12)</li>
 *   <li>Support for common abbreviations and full names</li>
 *   <li>Case-insensitive alias lookup</li>
 * </ul>
 *
 * <p><b>Credit: Documentation was written under the guidance of generative AI</b></p>
 */
public enum Month {
    JANUARY(1, Set.of("jan", "january")),
    FEBRUARY(2, Set.of("feb", "february")),
    MARCH(3, Set.of("mar", "march")),
    APRIL(4, Set.of("apr", "april")),
    MAY(5, Set.of("may")),
    JUNE(6, Set.of("jun", "june")),
    JULY(7, Set.of("jul", "july")),
    AUGUST(8, Set.of("aug", "august")),
    SEPTEMBER(9, Set.of("sep", "sept", "september")),
    OCTOBER(10, Set.of("oct", "october")),
    NOVEMBER(11, Set.of("nov", "november")),
    DECEMBER(12, Set.of("dec", "december"));

    private static final Map<String, Month> ALIAS_MAP = new HashMap<>();

    static {
        for (Month month : Month.values()) {
            for (String alias : month.aliases) {
                ALIAS_MAP.put(alias, month);
            }
        }
    }

    private int asNumber;
    private Set<String> aliases;

    /**
     * Constructs a Month enum with the specified numeric representation
     * and associated aliases
     *
     * @param number the numeric representation for that month (1 - 12)
     * @param aliases the set of string aliases for that month
     */
    Month(int number, Set<String> aliases) {
        this.asNumber = number;
        this.aliases = aliases;
    }

    public int getAsNumber() {
        return this.asNumber;
    }

    /**
     * Attempts to match the provided string alias to a month based the aliases
     * specified in the enum
     * <p>
     * The lookup is case-insensitive, and the method compares the provided string
     * to every alias set for each month. If no match is found, an empty Optional
     * will be returned instead.
     * </p>
     *
     * @param alias the string alias to look up (e.g., "jan", "January", "JAN")
     * @return an {@link Optional} containing the matching Month, or empty if no match found
     *
     * @see Optional
     */
    public static Optional<Month> fromAlias(String alias) {
        if (alias == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(ALIAS_MAP.get(alias.toLowerCase()));
    }
}
