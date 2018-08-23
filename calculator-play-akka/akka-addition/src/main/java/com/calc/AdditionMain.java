package com.calc;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.cluster.Cluster;
import com.calc.actor.AdditionActor;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class AdditionMain {

  public static void main(String[] args) {
    Config config = ConfigFactory.load();
    final ActorSystem system = ActorSystem.create("ClusterSystem", config);
    ActorRef additionActor = system.actorOf(Props.create(AdditionActor.class), "additionActor");
  }
}
