package application;
	
import java.util.Map;
import java.util.Vector;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Map;


public class Main extends Application {
	
	// set graph. 
	Graph graph = new Graph();
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Scene scene = SetLayout(primaryStage);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	Scene SetLayout(Stage window) {
		window.setTitle("Graph Visualizer");
		
		// panes.
		VBox leftContent = new VBox();
		VBox rightContent = new VBox();
		VBox topContent = new VBox();
		// buttons.
		Button submitButton = new Button("Add Vertex");
		Button DFSButton = new Button("Depth First Search");
		Button BFSButton = new Button("Breadth First Search");
		// text fields.
		TextField dataInput = new TextField();
		TextField parentInput = new TextField();
		TextField startIDInput = new TextField();
		TextField endIDInput = new TextField();
		TextField xInput = new TextField();
		TextField yInput = new TextField();
		// labels.
		Label errorLabel = new Label("");
		
		// button actions. 
		DFSButton.setOnAction(e-> {
			try {
				errorLabel.setText("");

				Vertex start = graph.getVertex(startIDInput.getText());
				Vertex end = graph.getVertex(endIDInput.getText());
				Vector<Vertex> path = graph.getDFSPath(start, end);
				
				System.out.print("[");
				for (int i = path.size()-1; i >= 0; i--) {
					System.out.printf("%s=>", path.get(i).data);
				}
				System.out.println("]");
			}
			catch(NullPointerException nullptr) {
				errorLabel.setText("ERROR: non-existent start or end vertex");
			}
		});
		submitButton.setOnAction(e->{
			try {
				errorLabel.setText("");

				Vertex parent = graph.getVertex(parentInput.getText());
				String childData = dataInput.getText();
				
				graph.InsertVertex(parent, childData);
				// output to show-case connections (only for testing).
				for (int i = 0; i < graph.getAllVertex().size(); i++) {
					Vertex v = graph.getAllVertex().get(i);
					System.out.printf("%s=>[", v.data);
					for(Map.Entry<String, Vertex> entry : v.neighbors.entrySet()) {
						System.out.printf("(%s= %s)",entry.getValue().data, entry.getValue());
					}
					System.out.println("]");
				}
				
				System.out.println();
				//////////////////////////////////////
			} catch (NullPointerException nullptr) {
				errorLabel.setText("ERROR: parent does not exist");
			}
		});
		
		// text field modifiers. 
		xInput.setPromptText("X");
		yInput.setPromptText("Y");
		dataInput.setPromptText("Enter vertex data value.");
		parentInput.setPromptText("Enter parent ID");
		startIDInput.setPromptText("start ID");
		endIDInput.setPromptText("end ID");
		
		//button modifiers.
		DFSButton.setMinWidth(150);
		BFSButton.setMinWidth(150);
		submitButton.setMinWidth(150);
		
		// left vbox modifiers.
		leftContent.getChildren().addAll(xInput, yInput, dataInput, parentInput, submitButton);
		leftContent.setSpacing(10);
		leftContent.setPadding(new Insets(10, 10, 10, 10));
		leftContent.setAlignment(Pos.CENTER_LEFT);
		// right vbox modifiers.
		rightContent.getChildren().addAll(startIDInput, endIDInput, DFSButton, BFSButton);
		rightContent.setSpacing(10);
		rightContent.setPadding(new Insets(10,10,10,10));
		rightContent.setAlignment(Pos.CENTER_RIGHT); 
		// top vbox modifiers.
		topContent.getChildren().add(errorLabel);
		topContent.setAlignment(Pos.TOP_CENTER);
		
		
		// main view.
		BorderPane mainContent = new BorderPane();
		mainContent.setLeft(leftContent);
		mainContent.setRight(rightContent);
		mainContent.setTop(topContent);
		
		
		Scene primaryScene = new Scene(mainContent, 600, 400);
		
		return primaryScene;
	}
	
}
