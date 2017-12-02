package ru.atom.gameserver.game;

import org.slf4j.LoggerFactory;

public class GameMechanics {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(GameMechanics.class);

    private InputQueue inputQueue;
    private Replicator replicator;

    public InputQueue getInputQueue() {
        return inputQueue;
    }

    public void setInputQueue(InputQueue inputQueue) {
        this.inputQueue = inputQueue;
    }

    public Replicator getReplicator() {
        return replicator;
    }

    public void setReplicator(Replicator replicator) {
        this.replicator = replicator;
    }
}
