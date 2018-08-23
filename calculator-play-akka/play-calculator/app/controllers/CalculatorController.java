package controllers;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.cluster.routing.ClusterRouterGroup;
import akka.cluster.routing.ClusterRouterGroupSettings;
import akka.pattern.Patterns;
import akka.pattern.PatternsCS;
import akka.routing.RoundRobinGroup;
import akka.util.Timeout;
import play.mvc.Controller;
import play.mvc.Result;
import scala.concurrent.Future;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

public class CalculatorController extends Controller {

  private ActorRef additionActorRef;
  private ActorRef subractionActorRef;
  private ActorRef multiplyActorRef;
  private ActorRef divideActorRef;

  @Inject
  public CalculatorController(ActorSystem system) {
    this.additionActorRef = getRemoteActorRef(system, "/user/additionActor", "addition");
    this.subractionActorRef = getRemoteActorRef(system, "/user/subtractionActor", "subtraction");
    this.multiplyActorRef = getRemoteActorRef(system, "/user/multiplyActor", "multiply");
    this.divideActorRef = getRemoteActorRef(system, "/user/divideActor", "divide");
  }

  public CompletionStage<Result> add(Double first, Double second) {
    Double[] numbers = new Double[] {first, second};
    CompletionStage<Object> ask =
        PatternsCS.ask(additionActorRef, numbers, Timeout.apply(60, TimeUnit.SECONDS));
    return ask.thenApply(
        o -> {
          return ok(String.valueOf(o));
        });
  }

  public CompletionStage<Result> subtract(Double first, Double second) {
    Double[] numbers = new Double[] {first, second};
    CompletionStage<Object> ask =
        PatternsCS.ask(subractionActorRef, numbers, Timeout.apply(60, TimeUnit.SECONDS));
    return ask.thenApply(
        o -> {
          return ok(String.valueOf(o));
        });
  }

  public CompletionStage<Result> multiply(Double first, Double second) {
    Double[] numbers = new Double[] {first, second};
    CompletionStage<Object> ask =
        PatternsCS.ask(multiplyActorRef, numbers, Timeout.apply(60, TimeUnit.SECONDS));
    return ask.thenApply(
        o -> {
          return ok(String.valueOf(o));
        });
  }

  public CompletionStage<Result> divide(Double first, Double second) {
    Double[] numbers = new Double[] {first, second};
    CompletionStage<Object> ask =
        PatternsCS.ask(divideActorRef, numbers, Timeout.apply(60, TimeUnit.SECONDS));
    return ask.thenApply(
        o -> {
          return ok(String.valueOf(o));
        });
  }

  private ActorRef getRemoteActorRef(ActorSystem system, String path, String role) {
    int totalInstances = 5;
    Iterable<String> routeesPath = Arrays.asList(path);
    boolean allowLocalRoutees = true;
    Set<String> useRoles = new HashSet<>(Arrays.asList(role));

    ActorRef actorRef =
        system.actorOf(
            new ClusterRouterGroup(
                    new RoundRobinGroup(routeesPath),
                    new ClusterRouterGroupSettings(
                        totalInstances, routeesPath, allowLocalRoutees, useRoles))
                .props());

    return actorRef;
  }
}
