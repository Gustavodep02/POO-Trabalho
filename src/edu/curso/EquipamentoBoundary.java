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
import javafx.util.StringConverter;
import javafx.util.converter.LongStringConverter;

public class EquipamentoBoundary implements Tela{
	private Label lblId = new Label("");
	private TextField txtNome = new TextField();
	private TextField txtFabricante = new TextField();
	private TextField txtNumeroSerie = new TextField();
	
	private EquipamentoControl control = null;
	
	private TableView<Equipamento> tableView = new TableView<>();
	
	@Override
	public Pane render() {
		try {
			control = new EquipamentoControl();
		} catch (LivroException e) {
			new Alert(AlertType.ERROR, "Erro ao iniciar o sistema", ButtonType.OK).showAndWait();
		}
		BorderPane panePrincipal = new BorderPane();
		GridPane paneForm = new GridPane();
		
		Button btnGravar = new Button("Gravar");
		btnGravar.setOnAction((e) -> {
			try {
				control.gravar();
			} catch (LivroException e1) {
				new Alert(AlertType.ERROR, "Erro ao gravar o livro", ButtonType.OK).showAndWait();
			}
			tableView.refresh();
		});
		Button btnPesquisar = new Button("Pesquisar");
		btnPesquisar.setOnAction((e) -> {
			try {
				control.pesquisar();
			} catch (LivroException e1) {
				new Alert(AlertType.ERROR, "Erro ao pesquisar os livros", ButtonType.OK).showAndWait();
			}
		});
		Button btnNovo = new Button ("*");
		btnNovo.setOnAction((e) -> {
			control.limparTudo();
		});
		
		paneForm.add(new Label("Id: "),0,0);
		paneForm.add(lblId,1,0);
		paneForm.add(new Label("Nome: "),0,1);
		paneForm.add(txtNome,1,1);
		paneForm.add(btnNovo,2,1);
		paneForm.add(new Label("Fabricante: "),0,2);
		paneForm.add(txtFabricante,1,2);
		paneForm.add(new Label("Numero de Serie: "),0,3);
		paneForm.add(txtNumeroSerie,1,3);
		paneForm.add(btnGravar,0,4);
		paneForm.add(btnPesquisar,1,4);
		
		ligacoes();
		gerarColunas();
		
		panePrincipal.setTop(paneForm);
		panePrincipal.setCenter(tableView);
		
		return panePrincipal;
	}
	public void gerarColunas() {
		TableColumn<Equipamento, Long> col1 = new TableColumn<>("Id");
		col1.setCellValueFactory(new PropertyValueFactory<Equipamento, Long>("id"));
		TableColumn<Equipamento, String> col2 = new TableColumn<>("Nome");
		col2.setCellValueFactory(new PropertyValueFactory<Equipamento, String>("nome"));
		TableColumn<Equipamento, String> col3 = new TableColumn<>("Fabricante");
		col3.setCellValueFactory(new PropertyValueFactory<Equipamento, String>("fabricante"));
		TableColumn<Equipamento, Long> col4 = new TableColumn<>("Numero de Serie");
		col4.setCellValueFactory(new PropertyValueFactory<Equipamento, Long>("numeroSerie"));

		tableView.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
			if (novo != null) {
				System.out.println("Equipamento: " + novo.getNome());
				control.entidadeParaTela(novo);
			}
		});
		Callback<TableColumn<Equipamento, Void>, TableCell<Equipamento, Void>> cb = new Callback<>() {
			@Override
			public TableCell<Equipamento, Void> call(
					TableColumn<Equipamento, Void> param) {
				TableCell<Equipamento,Void> celula = new TableCell<>() {
					final Button btnApagar = new Button ("Apagar");
					{
						btnApagar.setOnAction(e -> {
                            Equipamento equipamento = tableView.getItems().get(getIndex());
                            try {
                                control.excluir(equipamento);
                            } catch (LivroException err) {
                                new Alert(AlertType.ERROR, "Erro ao excluir o equipamento", ButtonType.OK).showAndWait();
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
		
		TableColumn<Equipamento, Void> col5 = new TableColumn<>("Acao");
		col5.setCellFactory(cb);
		
		tableView.getColumns().addAll(col1, col2, col3, col4, col5);
		tableView.setItems(control.getLista());
	}
	public void ligacoes() {
		control.idProperty().addListener((obs, antigo, novo) -> {
			lblId.setText(String.valueOf(novo));
		});
		LongStringConverter longConverter = new LongStringConverter();
		Bindings.bindBidirectional(control.nomeProperty(), txtNome.textProperty());
		Bindings.bindBidirectional(control.fabricanteProperty(), txtFabricante.textProperty());
		Bindings.bindBidirectional(txtNumeroSerie.textProperty(),control.numeroSerieProperty() , (StringConverter) longConverter);
		
	}
}
