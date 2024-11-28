package edu.curso;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class LivroBoundary implements Tela{

	private Label lblId = new Label("");
	private TextField txtTitulo = new TextField();
	private TextField txtAutor = new TextField();
	private TextField txtGenero = new TextField();

	
	private LivroControl control = null;
	
	private TableView<Livro> tableView = new TableView<>();
	
	
	
	
	@Override
	public Pane render() {
		try {
			control = new LivroControl();
		} catch (LivroException e) {
			new Alert(AlertType.ERROR, "Erro ao iniciar o sistema", ButtonType.OK).showAndWait();
		}
		
		BorderPane panePrincipal = new BorderPane();
		GridPane paneForm = new GridPane();
		
		Button btnGravar = new Button("Gravar");
		
		btnGravar = new Button("Gravar");
		btnGravar.setOnAction(e ->{
			try {
				control.gravar();
			}catch(LivroException err){
				new Alert(AlertType.ERROR, "Erro ao gravar o livro", ButtonType.OK).showAndWait();
			}
			tableView.refresh();
		});
		
		Button btnPesquisar = new Button("Pesquisar");
		btnPesquisar.setOnAction(e ->{
			try {
                control.pesquisar();
            }catch(LivroException err){
                new Alert(AlertType.ERROR, "Erro ao pesquisar por titulo", ButtonType.OK).showAndWait();
            }
        });
		
		Button btnNovo = new Button("*");
		btnNovo.setOnAction(e ->{
            control.limparTudo();
        });
		
		paneForm.add(new Label("Id:"), 0, 0);
		paneForm.add(lblId, 1, 0);
		paneForm.add(new Label("Titulo:"), 0, 1);
		paneForm.add(txtTitulo, 1, 1);
		paneForm.add(btnNovo, 2, 1);
		paneForm.add(new Label("Autor:"), 0, 2);
		paneForm.add(txtAutor, 1, 2);
		paneForm.add(new Label("Genero:"), 0, 3);
		paneForm.add(txtGenero, 1, 3);
		paneForm.add(btnGravar, 0, 4);
		paneForm.add(btnPesquisar, 1, 4);
		
		ligacoes();
		gerarColunas();
		
		panePrincipal.setTop(paneForm);
		panePrincipal.setCenter(tableView);
		
		return panePrincipal;
		
	}
	
	public void gerarColunas() {
		TableColumn<Livro, Long> col1 = new TableColumn<>("Id");
		col1.setCellValueFactory(new PropertyValueFactory<Livro, Long>("id"));
		TableColumn<Livro, String> col2 = new TableColumn<>("Titulo");
		col2.setCellValueFactory(new PropertyValueFactory<Livro, String>("titulo"));
		TableColumn<Livro, String> col3 = new TableColumn<>("Autor");
		col3.setCellValueFactory(new PropertyValueFactory<Livro, String>("autor"));
		TableColumn<Livro, String> col4 = new TableColumn<>("Genero");
		col4.setCellValueFactory(new PropertyValueFactory<Livro, String>("genero"));
		
		tableView.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo)->{
			if (novo != null) {
                System.out.println("Selecionou o livro: " + novo.getTitulo());
                control.paraTela(novo);
            }
		});
		Callback<TableColumn<Livro,Void>, TableCell<Livro, Void>> cb = new Callback<>() {
			@Override
			public TableCell<Livro, Void> call(
					TableColumn<Livro,Void> param){
				TableCell<Livro, Void> celula = new TableCell<>(){
					final Button btnApagar = new Button("Apagar");
					{
                        btnApagar.setOnAction(e ->{
                            Livro livro = tableView.getItems().get(getIndex());
                            try {
                            	control.excluir(livro);
                            }catch(LivroException err) {
                            	new Alert(AlertType.ERROR, "Erro ao excluir o livro", ButtonType.OK).showAndWait();
                            }

                        });
                    }
					@Override
					public void updateItem(Void item, boolean empty) {
						if(!empty) {
							setGraphic(btnApagar);
						}else {
							setGraphic(null);
						}
					}
				};
				return celula;
			}
		};
		
		TableColumn<Livro,Void> col5 = new TableColumn<>("Acao");
		col5.setCellFactory(cb);
		
		tableView.getColumns().addAll(col1, col2, col3, col4, col5);
		tableView.setItems(control.getLista());
		
	}
	public void ligacoes() {
		control.idProperty().addListener((obs, antigo, novo) -> {
			lblId.setText(String.valueOf(novo));
		});
		Bindings.bindBidirectional(control.tituloProperty(), txtTitulo.textProperty());
		Bindings.bindBidirectional(control.autorProperty(),txtAutor.textProperty() );
		Bindings.bindBidirectional(control.generoProperty(), txtGenero.textProperty());
		
	}
    
}
