package com.calc;

import akka.actor.ActorSystem;
import akka.cluster.Cluster;

public class SeedMain {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("ClusterSystem");
        final Cluster cluster = Cluster.get(system);
    }

}
