package com.xteam;

import org.jooby.Jooby;
import org.jooby.handlers.CorsHandler;
import xteam.marsrover.MarsRover;
import java.nio.charset.StandardCharsets;

/**
 * @author jooby generator
 */
public class App extends Jooby {
  {
    use("*", new CorsHandler());

    assets("/", "index.html");

    post("/marsrover", (req) ->  {
      MarsRover marsRover = new MarsRover();
      String inputData = req.param("data").value("").trim();

      return marsRover.execute(inputData);
    });
  }

  public static void main(final String[] args) {
    run(App::new, args);
  }

}
