package ru.atom.gameserver.game;

import org.slf4j.LoggerFactory;
import ru.atom.gameserver.util.Replica;

public class Replicator {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Replicator.class);

    public void writeReplica(Replica rep) {
        log.info("Replica has been received");
    }

}
