package eafc.peruwelz.miniprojet.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

public class WindowHelper {

    private static ApplicationContext context;

    // Permet d'injecter le contexte Spring lors de l'initialisation
    public static void initialize(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    public static void openWindow(String fxmlPath, String windowTitle, Stage parentStage) {
        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(WindowHelper.class.getResource(fxmlPath));
            loader.setControllerFactory(context::getBean); // Injection via Spring

            // Créer la scène à partir du fichier FXML
            Scene scene = new Scene(loader.load());

            // Créer la fenêtre (Stage)
            Stage stage = new Stage();
            stage.setTitle(windowTitle);
            stage.setScene(scene); // Associer la scène au stage

            // Associer le stage parent
            if (parentStage != null) {
                stage.initOwner(parentStage);
                stage.initModality(Modality.WINDOW_MODAL); // Désactiver le parent
            }

            // Empêcher le redimensionnement
            stage.setResizable(false);

            // Afficher la fenêtre
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            // Log ou gestion des erreurs
        }
    }
}
