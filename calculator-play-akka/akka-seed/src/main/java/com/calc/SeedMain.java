package com.calc;

import akka.actor.ActorSystem;

public class SeedMain {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("ClusterSystem");
    }

}
