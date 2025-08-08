package helpers;

public enum EnvKeys {
    GRID_URL("grid.url"),

    WAY2_BASE_URL("way2automation.base.url"),
    JQUERY_ALERT_PATH("jquery.alert.path"),
    JQUERY_DROPPABLE_PATH("jquery.droppable.path"),
    JQUERY_FRAMES_PATH("jquery.frames-and-windows.path"),
    JQUERY_NEW_TAB_PATH("jquery.frames-and-windows.new-tab.path"),
    LOGIN_CONFIRM_PATH("angularjs-protractor.registration.confirm.path"),
    LOGIN_FORM_PATH("angularjs-protractor.registration.login.path"),

    SQLEX_MAIN_PAGE_URL("sqlex.base.url"),
    SQLEX_LOGIN("sqlex.login"),
    SQLEX_PASSWORD("sqlex.password"),
    SQLEX_COOKIE_FILEPATH("sqlex.cookie.filepath"),

    HTTPWATCH_AUTH_URL("httpwatch.auth.url"),
    HTTPWATCH_LOGIN("httpwatch.login"),
    HTTPWATCH_PASSWORD("httpwatch.password");

    private final String key;

    EnvKeys(String key) {
        this.key = key;
    }

    public String get() {
        return PropertyProvider.getProperty(key);
    }
}

