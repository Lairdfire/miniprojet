package eafc.peruwelz.miniprojet.ctrl;

import com.sun.javafx.menu.MenuItemBase;
import eafc.peruwelz.miniprojet.domain.Tpersonne;
import eafc.peruwelz.miniprojet.repos.TpersonneRepository;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TpersonneControleur {

    private final TpersonneRepository personneRepository ;

    private enum Actions {ADD, UPDATE}
    private Tpersonne someOne;
    private ObservableList<Tpersonne> dataTableView;
    private Actions act;

    // Injection des composants
    @FXML
    private TextField fieldLastName;
    @FXML
    private TextField fieldFirstName;
    @FXML
    private GridPane form;
    @FXML
    private FlowPane buttonsForm;
    @FXML
    private FlowPane buttonsList;
    @FXML
    private TableView<Tpersonne> tableView;
    @FXML
    private TableColumn<Tpersonne,Integer> colId;
    @FXML
    private TableColumn<Tpersonne,String> colLastName;
    @FXML
    private TableColumn<Tpersonne,String> colFirstName;

    @Autowired
    public TpersonneControleur(TpersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    @FXML
    private void initialize() {
        this.colId.setCellValueFactory(new PropertyValueFactory<>("PersId"));
        this.colLastName.setCellValueFactory(new PropertyValueFactory<>("PersNom"));
        this.colFirstName.setCellValueFactory(new PropertyValueFactory<>("PersPrenom"));
        this.form.setDisable(true);
        this.buttonsForm.setDisable(true);
        this.fillList();
    }

    @FXML
    private void addRequest() {
        this.act = Actions.ADD;
        this.switchForm();
        this.fieldLastName.setText("");
        this.fieldFirstName.setText("");
    }

    @FXML
    private void updateRequest() {
        this.someOne = tableView.getSelectionModel().getSelectedItem();

        if (this.someOne != null) {
            this.act = Actions.UPDATE;
            this.switchForm();
            this.fieldLastName.setText(someOne.getPersNom());
            this.fieldFirstName.setText(someOne.getPersPrenom());
        }
    }

    @FXML
    private void save() {
        if (this.act == Actions.ADD)
            someOne = new Tpersonne();
        someOne.setPersNom(fieldLastName.getText());
        someOne.setPersPrenom(fieldFirstName.getText());
        personneRepository.save(someOne);
        if (this.act == Actions.ADD) {
            dataTableView.add(someOne);
        } else {
            dataTableView.set(dataTableView.indexOf(someOne), someOne);
        }
        switchForm();
    }

    @FXML
    private void delete() {
        int delID = tableView.getSelectionModel().getSelectedIndex();
        if(delID != -1) {
            personneRepository.deleteById(tableView.getSelectionModel().getSelectedItem().getPersId());
            dataTableView.remove(delID);
        }
    }

    @FXML
    private void exit() {
        Platform.exit();
    }

    @FXML
    private void switchForm() {
        form.setDisable(!form.isDisable());
        buttonsForm.setDisable((!buttonsForm.isDisable()));
        buttonsList.setDisable((!buttonsList.isDisable()));
    }

    @FXML
    private void fillList() {
        List<Tpersonne> liste = personneRepository.findAll();
        dataTableView = FXCollections.observableArrayList(liste);
        tableView.setItems(dataTableView);
    }


}