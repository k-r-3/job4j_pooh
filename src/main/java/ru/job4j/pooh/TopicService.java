package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TopicService implements Service {
    private ConcurrentHashMap<Integer, ConcurrentHashMap<String, String>>
            outMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, ConcurrentLinkedQueue<String>>
            inMap = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String method = req.method();
        inMap.putIfAbsent(req.name(), new ConcurrentLinkedQueue<>());
        if (method.equals("POST")) {
            outMap.putIfAbsent(req.id(), new ConcurrentHashMap<>());
            outMap.get(req.id()).putIfAbsent(req.name(), req.name());
            inMap.get(req.name()).offer(req.message());
            return new Resp("OK", 200);
        } else {
            String text = inMap.get(outMap.get(req.id()).get(req.name())).poll();
            return new Resp("\r\n" + text, 200);
        }
    }
}