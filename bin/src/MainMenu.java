/*******************************************************************************
 * MainMenu.java - Java class the implements main menu front-end
 * 
 * Author(s): Garrick Smith
 * Last Edit: 10/21/2019
*******************************************************************************/
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainMenu extends Application{  
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Meal Planner Login");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setCgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        
        Text scenetitle = new Text("Meal Planner: Sign in");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle,0,0,2,1);
        
        Label userName = new Label("User Name:");
        grid.add(userName,0,1);
        
        TextField userTextField = new TextField();
        grid.add(userTextField,1,1);
        
        Label pw = new Label("Password:");
        grid.add(pwBox,1,2);
        
        PasswordField pwBox = new PasswordField();
        grid.add(pwBox,1,2);
        
        StackPane layout = new StackPane();
        layout.getChildren().add(grid);
        
        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn,1,4);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                UsersFacade uf = new UsersFacade();
                Users u = new Users[1];
                u = uf.getUsersByUsername(userTextField.getText());
                if(u[0] != null) {
                    if(u[0].checkPassword(pwBox.getText());
                        System.out.println("Login Successful");
                        primaryStage.close();
                        mainMenu(u[0], new Stage);
                        
                    }
                    else {
                        System.out.println("Password does not match user");
                    }
                }
                else {
                    System.out.println("User does not exist");
                }
            }
        });
        
        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    @Override
    public void mainMenu(Users u, Stage menuStage) throws exception{
        menuStage.setTitle("Meal Planner Main Menu");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        
        Text scenetitle = new Text("Welcome" + u.getName());
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle,0,0,2,1);
        
        Button userMeals = new Button("Show My Meals");
        grid.add(userMeals,0,1);
        
        Button allMeals = new Button("Show All Meals");
        grid.add(allMeals,1,1);
        
        StackPane layout = new StackPane();
        layout.getChildren().add(grid);
        
        Scene scene = new Scene(layout, 300, 250);
        menuStage.setScene(scene);
        menuStage.show();
    } 
}
