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
        String[] command = content.split("/");
//        Pattern msg = Pattern.compile("!?.*[a-zA-Z]+$");
//        Pattern msg = Pattern.compile("!?(.*)\\s.*$");
//        Matcher mod = Pattern.compile("!?(\\w\\s+.)[a-z]+").matcher(content);
//        Matcher nam = Pattern.compile("!?\\W{2}[a-z]+", Pattern.MULTILINE).matcher(content);
        Matcher msg = Pattern.compile("!?(.*)\\s.*$").matcher(content);
        Matcher met = Pattern.compile("[A-Z]+").matcher(content);
        Matcher mod = Pattern.compile("!?\\w[a-z]+", Pattern.MULTILINE).matcher(content);
        Matcher dig = Pattern.compile("\\d", Pattern.MULTILINE).matcher(content);
        String m = "";
        String mo = "";
        String na = "";
        int id = -1;
        if (met.find()) {
            m = met.group();
        }
        if (mod.find()) {
            mo = mod.group();
        }
//        if (nam.find()) {
//            na = nam.group();
//        }
        if (dig.find()) {
            id = Integer.parseInt(dig.group().strip());
        }
        String method = m;
        String mode = mo;
        String name = command[2];
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