/*******************************************************************************
 * MainMenu.java - Java class that implements main menu front-end
 * 
 * Author(s): Garrick Smith, Jason Ren
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
        
        Button btn = new Button("Sign In");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn,1,4);
        
        Button btn2 = new Button("Create Account");
        Hbox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn2);
        grid.add(hbBtn2,1,3);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                UsersFacade uf = new UsersFacade();
                Users[] u = new Users[1];
                u = uf.getUsersByUsername(userTextField.getText());
                if(u[0] != null) {
                    if(u[0].checkPassword(pwBox.getText());
                        System.out.println("Login Successful");
                        primaryStage.close();
                        mainMenu(u[0], new Stage());
                        
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
        
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.close();
                createUser(new Stage);
            }
        });
        
        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void createUser(Stage newUserStage) throws Exception{
        newUserStage.setTitle("Meal Planner New User Menu");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        
        Text scenetitle = new Text("Create New User:");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle,0,0,0,0);
        
        Label userName = new Label("User Name:");
        grid.add(userName,0,1);
        
        TextField userTextField = new TextField();
        grid.add(userTextField,1,1);
        
        Label pw1 = new Label("Password:");
        grid.add(pw1,1,2);
        
        PasswordField pwBox1 = new PasswordField();
        grid.add(pwBox1,1,2);
        
        Label pw2 = new Label("Confirm Password:");
        grid.add(pw2,2,3);
        
        PasswordField pwBox2 = new PasswordField();
        grid.add(pwBox2,2,3);
        
        StackPane layout = new StackPane();
        layout.getChildren().add(grid);
        
        Label name = new Label("Name:");
        grid.add(name,3,4);
        
        TextField nameTextField = new TextField();
        grid.add(nameTextField,3,4);
        
        Button btn = new Button("Create User");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn,1,5);
        
        btn.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent e) {
                boolean taken = false;
                UsersFacade uf = new UsersFacade();
                Users[] users = new Users[100];
                users = getUsers();
                if(!pwBox1.getText().Equals(pwBox2.getText())) {
                    for(int i = 0; i < users.length; i++) {
                        if(userTextField.getText().Equals(users[i].getName())) {
                            System.out.println("Username is taken");
                            taken = true;
                            break;
                        }
                    }
                }
                else {
                    System.out.println("Passwords do not match");
                }
                if(!taken) {
                    User u = new User(userTextField.getText(), pwBox1.getText(), nameTextField.getText(), 0);
                    users = uf.createUsers(u);
                    System.out.println("User created!");
                }
            }
        });
        
        Scene scene = new Scene(layout, 300, 250);
        newUserStage.setScene(scene);
        newUserStage.show();
    }
    
    public void mainMenu(Users u, Stage menuStage) throws Exception{
        menuStage.setTitle("Meal Planner Main Menu");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        
        Text scenetitle = new Text("Welcome " + u.getName());
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
