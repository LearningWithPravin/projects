package com.calc.actor;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class AdditionActor extends AbstractActor {
  LoggingAdapter log = Logging.getLogger(getContext().system(), this);

  @Override
  public Receive createReceive() {
    return receiveBuilder()
        .match(
            Double[].class,
            msg -> {
              log.info("Received msg :: {} + {}", msg[0], msg[1]);
              sender().tell(msg[0] + msg[1], self());
              log.info("Returned response :: {}", msg[0] + msg[1]);
            })
        .build();
  }
}
