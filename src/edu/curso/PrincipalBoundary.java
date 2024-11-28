package edu.curso;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PrincipalBoundary extends Application{
	private Map<String, Tela> telas = new HashMap<>();
	@Override
	public void start(Stage stage) throws Exception{
		telas.put("livro", new LivroBoundary());
		telas.put("equipamento", new EquipamentoBoundary());
		BorderPane panePrincipal = new BorderPane();
		MenuBar menuBar = new MenuBar();
		Menu mnuCadastro = new Menu("Cadastros");
		MenuItem mnuItemLivro = new MenuItem("Livro");
		
		mnuItemLivro.setOnAction(e -> {
			panePrincipal.setCenter(telas.get("livro").render());
		});
		MenuItem mnuItemEquipamento = new MenuItem("Equipamento");
		mnuItemEquipamento.setOnAction(e -> {
			panePrincipal.setCenter(telas.get("equipamento").render());
		});
		
		mnuCadastro.getItems().add(mnuItemLivro);
        mnuCadastro.getItems().add(mnuItemEquipamento);
	    menuBar.getMenus().addAll(mnuCadastro);
	    panePrincipal.setTop(menuBar);
	    Scene scn = new Scene(panePrincipal, 800, 600);
	    stage.setScene(scn);
	    stage.setTitle("Sistema de Biblioteca");
	    stage.show();
	}

	public static void main(String[] args) {
		Application.launch(PrincipalBoundary.class, args);
	}

}
