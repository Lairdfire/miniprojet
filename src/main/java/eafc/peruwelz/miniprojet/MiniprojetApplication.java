package eafc.peruwelz.miniprojet;

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
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainView.fxml"));
        loader.setControllerFactory(context::getBean);
        Parent root = loader.load();
        stage.setTitle("ORM With SPRING & HIBERNATE");
        stage.setScene(new Scene(root, 400, 400));
        stage.show();
        stage.setOnCloseRequest(event -> {
            Platform.exit();
        });
    }

    @Override
    public void stop() throws Exception {
        SpringApplication.exit(context, () -> 0);
    }
}