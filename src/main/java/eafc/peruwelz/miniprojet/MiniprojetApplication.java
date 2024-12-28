package eafc.peruwelz.miniprojet;

import eafc.peruwelz.miniprojet.Utils.WindowConfig;
import eafc.peruwelz.miniprojet.Utils.WindowHelper;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class MiniprojetApplication extends Application {

    public ApplicationContext context;

    public static void main(final String[] args) {
        launch();
    }

    @Override
    public void init() {
        context = new SpringApplicationBuilder(MiniprojetApplication.class).run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initialiser WindowConfig avec le contexte Spring
        WindowHelper.initialize(context);

        // Charger la vue principale
        FXMLLoader loader = new FXMLLoader(getClass().getResource(WindowConfig.MAIN_VIEW));
        loader.setControllerFactory(context::getBean);
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle(WindowConfig.MAIN_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        SpringApplication.exit(context, () -> 0);
    }
}