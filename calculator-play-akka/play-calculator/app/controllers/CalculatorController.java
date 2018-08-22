package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class CalculatorController extends Controller {

    public Result add() {
        return ok(String.valueOf(2+2));
    }

}
