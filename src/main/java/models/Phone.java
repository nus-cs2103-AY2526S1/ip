package models;

public class Phone {
    private String countryCode;
    private String number;

    public Phone(String code, String num) {
        countryCode = code;
        number = num;
    }

    public Phone(String fullNumber) {
        if (fullNumber.charAt(0) == '+' || fullNumber.charAt(2) == ' ') {
            fullNumber = fullNumber.replaceAll("[^0-9]", "");
            countryCode = fullNumber.substring(0, 2);
            number = fullNumber.substring(2);
        } else {
            countryCode = "";
            number = fullNumber;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Phone)) {
            return false;
        }
        Phone p = (Phone) obj;
        if (countryCode.isEmpty() || p.countryCode.isEmpty()) {
            return number.equals(p.number);
        }
        return countryCode.equals(p.countryCode) && number.equals(p.number);
    }

    @Override
    public String toString() {
        if (!countryCode.isEmpty()) {
            return String.format("+%s %s", countryCode, number);
        } else {
            return number;
        }
    }
}