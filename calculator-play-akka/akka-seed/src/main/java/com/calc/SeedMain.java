package com.calc;

import akka.actor.ActorSystem;
import akka.cluster.Cluster;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class SeedMain {

    public static void main(String[] args) {
        Config config = ConfigFactory.load();
        ActorSystem system = ActorSystem.create("ClusterSystem", config);
    }

}
