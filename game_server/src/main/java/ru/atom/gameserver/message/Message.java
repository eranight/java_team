package ru.atom.gameserver.message;

public class Message {

    private final Topic topic;
    private final String data;

    public Message(Topic topic, String data) {
        this.topic = topic;
        this.data = data;
    }

    Topic getTopic() {
        return topic;
    }

    String getData() {
        return data;
    }
}
