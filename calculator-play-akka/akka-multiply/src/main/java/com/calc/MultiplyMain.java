package com.calc;

import akka.actor.ActorSystem;
import akka.cluster.Cluster;

public class MultiplyMain {


    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("ClusterSystem");
        final Cluster cluster = Cluster.get(system);
    }
}
