package utils.enums;

public enum HeaderMenuItem {
    SEARCH("//a[text()=' Search ']"),
    LET_THE_CAR_WORK("//a[text()=' Let the car work ']"),
    TERMS_OF_USE("//a[text()=' Terms of use ']"),
    SIGN_UP("//a[text()=' Sign up ']"),
    LOGIN("//a[text()=' Log in ']"),
    LOGOUT("//a[text()=' Logout ']"),
    DELETE_ACCOUNT("//a[text()='Delete account']");

    private final String locator;

    HeaderMenuItem(String locator) {
        this.locator = locator;
    }

    public String getLocator() {
        return locator;
    }
}