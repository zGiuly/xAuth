package it.zgiulydev.zauth.utils;

import it.zgiulydev.zauth.zAuth;

public enum Messages {
    PASSWORD_NOT_CORRECT(zAuth.getInstance().getConfig().getString("messages.password_not_correct")),
    LOGGED(zAuth.getInstance().getConfig().getString("messages.logged")),
    REGISTERED(zAuth.getInstance().getConfig().getString("messages.registered")),
    USER_EXISTS(zAuth.getInstance().getConfig().getString("messages.user_exists"));

    private String value;
    Messages(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
