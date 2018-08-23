package com.calc;

import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.cluster.Cluster;
import com.calc.actor.SubtractionActor;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class SubtractionMain {

    public static void main(String[] args) {
        Config config = ConfigFactory.load();
        final ActorSystem system = ActorSystem.create("ClusterSystem", config);
        system.actorOf(Props.create(SubtractionActor.class), "subtractionActor");
    }
}
