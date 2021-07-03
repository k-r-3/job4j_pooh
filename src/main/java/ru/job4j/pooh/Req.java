package ru.job4j.pooh;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Req {
    private final String method;
    private final String mode;
    private final String name;
    private final String message;
    private final int id;

    private Req(String method, String mode, String name, String message, int id) {
        this.method = method;
        this.mode = mode;
        this.name = name;
        this.message = message;
        this.id = id;
    }

    public static Req of(String content) {
        Matcher msg = Pattern.compile("!?(.*)\\s.*$").matcher(content);
        Matcher met = Pattern.compile("\\w+").matcher(content);
        Matcher mod = Pattern.compile("[a-z]+", Pattern.MULTILINE).matcher(content);
        Matcher nam = Pattern.compile("\\w+\\W+\\w+\\W(\\w+)", Pattern.MULTILINE).matcher(content);
        Matcher dig = Pattern.compile("\\d", Pattern.MULTILINE).matcher(content);
        String method = "";
        String mode = "";
        String name = "";
        int id = -1;
        if (met.find()) {
            method = met.group();
        }
        if (mod.find()) {
            mode = mod.group();
        }
        if (nam.find()) {
            name = nam.group(1);
        }
        if (dig.find()) {
            id = Integer.parseInt(dig.group().strip());
        }
        StringBuilder message = new StringBuilder(" ");
        if (msg.find()) {
            message.append(msg.group());
        }
        return new Req(method, mode, name, message.toString(), id);
    }

    public String method() {
        return method;
    }

    public String mode() {
        return mode;
    }

    public String name() {
        return name;
    }

    public String message() {
        return message;
    }

    public int id() {
        return id;
    }
}