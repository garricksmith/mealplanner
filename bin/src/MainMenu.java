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
import java.util.*;

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
        grid.setVgap(10);
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
        grid.add(hbBtn,1,);
        
        Button btn2 = new Button("Create Account");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChildren().add(btn2);
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
        grid.add(scenetitle,0,0,2,1);
        
        Label userName = new Label("User Name:");
        grid.add(userName,0,1);
        
        TextField userTextField = new TextField();
        grid.add(userTextField,1,1);
        
        Label pw1 = new Label("Password:");
        grid.add(pw1,0,2);
        
        PasswordField pwBox1 = new PasswordField();
        grid.add(pwBox1,1,2);
        
        Label pw2 = new Label("Confirm Password:");
        grid.add(pw2,0,3);
        
        PasswordField pwBox2 = new PasswordField();
        grid.add(pwBox2,1,3);
        
        StackPane layout = new StackPane();
        layout.getChildren().add(grid);
        
        Label name = new Label("Name:");
        grid.add(name,0,4);
        
        TextField nameTextField = new TextField();
        grid.add(nameTextField,1,4);
        
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
                if(pwBox1.getText().Equals(pwBox2.getText())) {
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
                    taken = true;
                }
                if(!taken) {
                    User u = new User(userTextField.getText(), pwBox1.getText(), nameTextField.getText(), 0);
                    users = uf.createUsers(u);
                    System.out.println("User created!");
                    
                    newUserStage.close();
                    start(new Stage());
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
        
        Button backBtn = new Button("Log Out");
        HBox hbBtn = new HBox(5);
        hbBtn.setAlignment(Pos.TOP_LEFT);
        hbBtn.getChildren().add(backBtn);
        grid.add(hbBtn,0,0);
        
        
        Text scenetitle = new Text("Welcome " + u.getName());
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle,0,1,2,1);
        
        Button userMeals = new Button("Show My Meals");
        grid.add(userMeals,0,2);
        
        Button allMeals = new Button("Show All Meals");
        grid.add(allMeals,1,2);
        
        StackPane layout = new StackPane();
        layout.getChildren().add(grid);
        
        allMeals.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                RecipesFacade rf = new RecipesFacade();
                ArrayList<Recipe> r = new ArrayList<>();
                r = rf.getRecipes();
                menuStage.close();
                displayMeals(r, u, new Stage());
            }
        });
        
        userMeals.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                RecipesFacade rf = new RecipesFacade();
                ArrayList<Recipe> r = new ArrayList<>();
                r = rf.getUserRecipesByUserName(u.getUsername());
                menuStage.close();
                displayMeals(r, u, new Stage());
            }
        });
        
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                mainMenu.close();
                start(new Stage());
            }
        });
        
        Scene scene = new Scene(layout, 300, 250);
        menuStage.setScene(scene);
        menuStage.show();
    } 
    
    public void displayMeals(ArrayList<Recipes> recipes, Users u, Stage displayMealsStage) {
        displayMealsStage.setTitle("Meals");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        
        Button backBtn = new Button("Back");
        HBox hbBtn = new HBox(5);
        hbBtn.setAlignment(Pos.TOP_LEFT);
        hbBtn.getChildren().add(backBtn);
        grid.add(hbBtn,0,0);
        
        Text sceneTitle = new Text("Meals: ");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle,0,1,2,1);
        
        int i;
        for(i = 0; i < recipes.size(); i++) {
            Label rLabel = new Label(recipes.get(i).toString());
            grid.add(rLabel,0,i+2);
        }
        
        Button createMeal = new Button("Create Meal");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.CENTER);
        hbBtn2.getChildren().add(createMeal);
        grid.add(hbBtn,0,i+2);
        
        StackPane layout = new StackPane();
        layout.getChildren().add(grid);
        
        createMeal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                displayMealsStage.close();
                createMeal(new Recipes, recipes, u, new Stage());
            }
        });
        
        rLabel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Recipe returnRecipe;
                rText = rLabel.getText();
                for(int i = 0; i < recipes.size(); i++) {
                    if(recipes.get(i).toString().equals(rText)) {
                        returnRecipe = recipes.get(i);
                    }
                }
                displayMealsStage.close();
                displayRecipe(returnRecipe, recipes, u, new Stage());
            }
        });
        
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                displayMealsStage.close();
                mainMenu(u, new Stage());
            }
        });
        
        Scene scene = new Scene(layout, 300, 250);
        displayMealsStage.setScene(scene);
        displayMealsStage.show();
    }
    
    public void displayRecipe(Recipes r, ArrayList<Recipes> recipes, Users u, Stage recipeStage) {
        recipeStage.setTitle("Recipe");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        
        Button backBtn = new Button("Back");
        HBox hbBtn = new HBox(5);
        hbBtn.setAlignment(Pos.TOP_LEFT);
        hbBtn.getChildren().add(backBtn);
        grid.add(hbBtn,0,0);
        
        Text sceneTitle = new Text(r.getName());
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle,0,1,2,1);
        
        Label l = new Label("Will display ingredients");
        grid.add(l,0,2);
        
        StackPane layout = new StackPane();
        layout.getChildren().add(grid);
        
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                recipeStage.close();
                displayMeals(recipes, u, new Stage());
            }
        });
        
        Scene scene = new Scene(layout, 300, 250);
        recipeStage.setScene(scene);
        recipeStage.show();
    }
    
    public void createMeal(Recipes r, Recipes[] recipes, Users u, Stage createMealStage) {
        createMealStage.setTitle("Create New Recipe");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        
        Text scenetitle = new Text("Create New Recipe:");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle,0,1,2,1);
        
        Label name = new Label("Name:");
        grid.add(name,0,2);
        
        TextField nameTextField = new TextField();
        grid.add(nameTextField,1,2);
        
        Label desc = new Label("Description:");
        grid.add(desc,0,3);
        
        TextField descBox = new TextField();
        grid.add(descBox,1,3);
        
        TilePane r = new TilePane();
        Label ingLabel = new Label("Add Ingredients");
        r.getChildren.add(ingLabel);
        
        Ingredients[] ingredients = new Ingredients[100];
        IngredientsFacade ingf = new IngredientsFacade();
        ingredients = ingf.getIngredients();
        
        ArrayList<Ingredients> ingToAdd = new ArrayList<Ingredients>();
        
        for(int i = 0; i < ingredients.length; i++) {
            CheckBox c = new CheckBox(ingredients[i].getName());
            r.getChildren().add(c);
            
            c.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {    
                    if(c.isSelected()) {
                        ingToAdd.add(ingredients[i]);
                    }
                    else {
                        ingToAdd.remove(ingredients[i]);
                    }
                }
            });
        }
        
        Button addIngredient = new Button("Create Ingredient");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChidlren().add(addIngredient);
        grid.add(hbBtn2,1,4);
        
        Button btn = new Button("Create Recipe");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn,1,5);
                
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                RecipesFacade rf = new RecipesFacade(); 
                Recipes r = new Recipes(nameTextField.getText(),descBox.getText());
                
                rf.createRecipes(r);
                
                for(int i = 0; i < ingToAdd.size(); i++) {
                    RecipesIngredientsFacade rif = new RecipesIngredientsFacade();
                    Recipes[] ra = rf.getRecipeByRecipeName(nameTextField.getText());
                    RecipesIngredients ri = new RecipesIngredients(ra[0].getId(),ingToAdd.get(i).getId(),0);
                    
                    rif.createRecipesIngredients(ri);
                }
            }
        });
        
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                createMealStage.close();
                createIngredient(r, recipes, u, new Stage());
            }
        });
        
        Scene scene = new Scene(layout, 500, 400);
        newUserStage.setScene(scene);
        newUserStage.show();
    }
    
    public void createIngredient(Recipes r, Recipes[] recipes, User u, Stage createIngredientStage) {
        createIngredientStage.setTitle("Create New Ingredient");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        
        Text scenetitle = new Text("Create New Ingredient:");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle,0,1,2,1);
        
        Label nameL = new Label("Name:");
        grid.add(nameL,0,2);
        TextField nameField = new TextField();
        grid.add(nameField,1,2);
        
        Label descL = new Label("Description:");
        grid.add(descL,0,3);
        TextField descField = new TextField();
        grid.add(descField,1,3);
        
        Label metricL = new Label("Measuring Metric:");
        grid.add(metricL,0,4);
        TextField metricField = new TextField();
        grid.add(metricField,1,4);
        
        Label ufL = new Label("Unsaturated Fat(g):");
        grid.add(ufL,0,5);
        TextField ufField = new TextField();
        grid.add(ufField,1,5);
        
        Label sfL = new Label("Saturated Fat(g):");
        grid.add(sfL,0,6);
        TextField sfField = new TextField();
        grid.add(sfField,1,6);
        
        Label tfL = new Label("Trans Fat(g):");
        grid.add(tfL,0,7);
        TextField tfField = new TextField();
        grid.add(tfField,1,7);
        
        Label cholL = new Label("Cholesterol(mg):");
        grid.add(cholL,0,8);
        TextField cholField = new TextField();
        grid.add(cholField,1,8);

        Label sodiumL = new Label("Sodium(mg):");
        grid.add(sodiumL,0,9);
        TextField sodiumField = new TextField();
        grid.add(sodiumField,1,9);
        
        Label fiberL = new Label("Fiber(g):");
        grid.add(fiberL,0,10);
        TextField fiberField = new TextField();
        grid.add(fiberField,1,10);
        
        Label sugarL = new Label("Sugar(g):");
        grid.add(sugarL,0,11);
        TextField sugarField = new TextField();
        grid.add(sugarField,1,11);
        
        Label proteinL = new Label("Protein(g):");
        grid.add(proteinL,0,12);
        TextField proteinField = new TextField();
        grid.add(proteinField,1,12);
        
        Button addIngredient = new Button("Create Ingredient");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChidlren().add(addIngredient);
        grid.add(hbBtn,1,13);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Ingredients i = new Ingredients.Builder().ingredientId(0).ingredientName(nameField.getText()).ingredientDesc(descField.getText()).servingMetric(metricField.getText()).gUnsaturatedFat(ufField.getText()).gSaturatedFat(sfField.getText()).gTransFat(tfField.getText()).mgCholesterol(cholField.getText()).mgSodium(sodiumField.getText()).gFiber(fiberField.getText()).gSugar(sugarField.getText()).gProtein(proteinField.getText()).create();
                
                IngredientsFacade ingf = new IngredientsFacade();
                Ingredients[] ings = new Ingredients[100];
                
                ings = ingf.createIngredients(i);
                
                createIngredientStage.close();
                createMeal(r, recipes, u, new Stage());
            }
        });
        
        Scene scene = new Scene(layout, 600, 400);
        createIngredientStage.setScene(scene);
        createIngredientStage.show();
    }
}
