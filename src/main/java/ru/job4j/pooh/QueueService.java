package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueService implements Service {
    private static ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String method = req.method();
        queue.putIfAbsent(req.name(), new ConcurrentLinkedQueue<>());
        if (method.equals("POST")) {
                queue.get(req.name()).offer(req.message());
            return new Resp("OK", 200);
        } else {
            String text = queue.get(req.name()).poll();
            return new Resp("\r\n" + text, 200);
        }
    }
}