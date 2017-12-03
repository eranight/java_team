package ru.atom.gameserver.gsession.mechanics;

import org.slf4j.LoggerFactory;
import ru.atom.gameserver.gsession.mechanics.message.Message;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class InputQueue {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(InputQueue.class);

    private Queue<Message> queue = new ConcurrentLinkedQueue<>();

    public Message pop() {
        return queue.poll();
    }

    public void push(Message message) {
        queue.offer(message);
    }

}
