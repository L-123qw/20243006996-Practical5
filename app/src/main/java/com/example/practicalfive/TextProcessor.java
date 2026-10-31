package com.example.practicalfive;

/**
 * A plain Java helper class used to validate and normalise the name of a
 * Chinese municipality entered by the user. It must not extend Activity and
 * does not have an associated XML layout.
 */
public final class TextProcessor {

    /** Intent extra key used by both MainActivity and Municipality. */
    public static final String EXTRA_MUNICIPALITY = "municipality_name";

    /** The four supported municipalities, stored in lowercase for matching. */
    private static final String[] MUNICIPALITIES =
            {"beijing", "shanghai", "tianjin", "chongqing"};

    private TextProcessor() {
        // Utility class: no instances allowed.
    }

    /**
     * Validates and normalises a municipality name.
     *
     * @param municipality the raw text entered by the user, possibly null
     * @return the correctly capitalised name for a valid input, e.g. "Beijing",
     *         or {@code null} if the input is null, empty, whitespace only or
     *         not one of the four supported municipalities
     */
    public static String processMunicipality(String municipality) {
        // Reject null input.
        if (municipality == null) {
            return null;
        }

        // Trim and reject empty / whitespace-only input.
        String trimmed = municipality.trim();
        if (trimmed.isEmpty()) {
            return null;
        }

        // Compare case-insensitively against the supported names.
        String lower = trimmed.toLowerCase();
        for (String name : MUNICIPALITIES) {
            if (name.equals(lower)) {
                return capitalise(name);
            }
        }

        // Unsupported name (e.g. "Guangzhou").
        return null;
    }

    /**
     * Returns the name with the first letter capitalised and the rest lower
     * case, e.g. "chongqing" becomes "Chongqing".
     */
    private static String capitalise(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
