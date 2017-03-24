import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/squads/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/squad-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/squads", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      // Hero newHero = new Hero("Dong", "Lee", 100);
      String name = request.queryParams("name");
      Team newSquad = new Team(name);
      model.put("template", "templates/squad-success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/squads", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("squads", Team.all());
      model.put("template", "templates/squads.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/squads/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Team squad = Team.find(Integer.parseInt(request.params(":id")));
      model.put("squad", squad);
      model.put("template", "templates/squad.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("squads/:id/heros/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Team squad = Team.find(Integer.parseInt(request.params(":id")));
      model.put("squad", squad);
      model.put("template", "templates/squad-tasks-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/heros", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      Team squad = Team.find(Integer.parseInt(request.queryParams("squadId")));
      String description = request.queryParams("description");
      Member newHero = new Member(description);

      squad.addHero(newHero);

      model.put("squad", squad);
      model.put("template", "templates/squad.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("squads/:squadId/heros/:heroId", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Team squad = Team.find(Integer.parseInt(request.params(":squadId")));
      Member hero = Member.find(Integer.parseInt(request.params(":heroId")));
      model.put("hero",hero);
      model.put("template", "templates/hero.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


  }
}
