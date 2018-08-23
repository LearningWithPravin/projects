package com.calc.actor;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class DivideActor extends AbstractActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(
                        Double[].class,
                        msg -> {
                            log.info("Received msg :: {} + {}", msg[0], msg[1]);
                            if(msg[1] == 0) {
                                sender().tell("Cannot divide by zero", self());
                                log.info("Returned response :: {}", "Cannot divide by zero");

                            } else {
                                sender().tell(msg[0] / msg[1], self());
                                log.info("Returned response :: {}", msg[0] / msg[1]);
                            }

                        })
                .build();
    }
}
