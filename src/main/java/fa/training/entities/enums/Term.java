package fa.training.entities.enums;

public enum Term {
    // because the requirements : term mus be int, so I use int value for each enum
    // and I have not used converter before, so I use ordinal for enum, I will do it again after this exam

    // It has an ordinal value of 0, which is not used in the database.
    DUMMY(0),

    // This represents a term of 1 month. It has an ordinal value of 1, which aligns with the database value.
    ONE(1),

    // This represents a term of 2 months. It has an ordinal value of 2, which aligns with the database value.
    TWO(2),

    // This represents a term of 3 months. It has an ordinal value of 3, which aligns with the database value.
    THREE(3),

    // This represents a term of 6 months. It has an ordinal value of 4, which aligns with the database value.
    SIX(6),

    // This represents a term of 9 months. It has an ordinal value of 5, which aligns with the database value.
    NINE(9),

    // This represents a term of 12 months. It has an ordinal value of 6, which aligns with the database value.
    TWELVE(12),

    // This represents a term of 18 months. It has an ordinal value of 7, which aligns with the database value.
    EIGHTEEN(18),

    // This represents a term of 24 months. It has an ordinal value of 8, which aligns with the database value.
    TWENTY_FOUR(24),

    // This represents a term of 36 months. It has an ordinal value of 9, which aligns with the database value.
    THIRTY_SIX(36);

    private final int value;

    Term(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}