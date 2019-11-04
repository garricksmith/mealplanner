import java.sql.*;
import java.util.*;
public class RecipesFacade {
	Recipes[] ingArray = new Recipes[100];
	
	public Recipes[] getRecipesById(int theId)throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mealplanner?useSSL=false&serverTimezone=UTC", "root", "BaoBao729825429");
	
		PreparedStatement stmt = con.prepareStatement("SELECT recipe_id, recipe_name, recipe_desc from recipes WHERE recipe_id =?");
		stmt.setInt(1,  theId);
		ResultSet rs = stmt.executeQuery();
		
		int count = 0;
		while(rs.next()) {
			int theId2 = rs.getInt("recipe_id");
			String recipesname = rs.getString("recipe_name");
			String recipesdesc = rs.getString("recipe_desc");
			
			Recipes ing = new Recipes(theId2, recipesname, recipesdesc);
			ingArray[count]  = ing;
			count++;
		}
		if(count>0) {
			ingArray = Arrays.copyOf(ingArray, count);
			return ingArray;
		}
		else
			return null;
		
	}

	
	public Recipes[] getRecipesByRecipesName(String therecipesname)throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mealplanner?useSSL=false&serverTimezone=UTC", "root", "BaoBao729825429");
		
		
		PreparedStatement stmt = con.prepareStatement("SELECT recipe_id, recipe_name, recipe_desc from recipes WHERE recipe_id =?");
		stmt.setString(1,  therecipesname);
		ResultSet rs = stmt.executeQuery();
	
		int count = 0;
		while(rs.next()) {
			int theId = rs.getInt("recipe_id");
			String recipesname2 = rs.getString("recipe_name");
			String recipesdesc = rs.getString("recipe_desc");

			Recipes ing = new Recipes(theId, recipesname2, recipesdesc);
			ingArray[count]  = ing;
			count++;
		}
		if(count>0) {
			ingArray = Arrays.copyOf(ingArray, count);
			return ingArray;
		}
		else
			return null;
		
	}
	

	
	public Recipes[] getRecipes() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mealplanner?useSSL=false&serverTimezone=UTC", "root", "BaoBao729825429");
	
		PreparedStatement stmt = con.prepareStatement("SELECT recipe_id, recipe_name, recipe_desc from recipes");
		ResultSet rs = stmt.executeQuery();
	
		int count = 0;
		while(rs.next()) {
			int theId = rs.getInt("recipe_id");
			String recipesname = rs.getString("recipe_name");
			String recipesdesc = rs.getString("recipe_desc");

			Recipes ing = new Recipes(theId, recipesname, recipesdesc);
			ingArray[count]  = ing;
			count++;
		}
		if(count>0) {
			ingArray = Arrays.copyOf(ingArray, count);
			return ingArray;
		}
		else
			return null;
		
	}
	
	
	public Recipes[] createRecipes(Recipes theRecipesToAdd) throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mealplanner?useSSL=false&serverTimezone=UTC", "root", "BaoBao729825429");
		
		
		PreparedStatement cstmt = con.prepareStatement("INSERT INTO recipes(recipe_id, recipe_name, recipe_desc) VALUE (?,?,?)");
		cstmt.setInt(1, theRecipesToAdd.getId());
		cstmt.setString(2, theRecipesToAdd.getName());
		cstmt.setString(3, theRecipesToAdd.getDesc());

		int res = cstmt.executeUpdate();
		if(res==1) {
			PreparedStatement retrieveStmt = con.prepareStatement("Select * from recpies WHERE recipe_id=? AND recipe_name = ? AND recipe_desc = ?");
			retrieveStmt.setInt(1, theRecipesToAdd.getId());
			retrieveStmt.setString(2, theRecipesToAdd.getName());
			retrieveStmt.setString(3, theRecipesToAdd.getDesc());

			ResultSet rs = retrieveStmt.executeQuery();
			int count = 0;

			
			while(rs.next()) {
				int theId = rs.getInt("recipe_id");
				String therecipesname = rs.getString("recipe_name");
				String therecipesdesc = rs.getString("recipe_desc");
	
				
				Recipes ing = new Recipes(theId, therecipesname, therecipesdesc);
				
				ingArray[count] = ing;
				count++;
				
			}
			if(count>0) {
				ingArray = Arrays.copyOf(ingArray, count);
				return ingArray;
			}
			else
				return null;
		}
		else {
			Recipes[] blankIngArray = new Recipes[1];
			blankIngArray[0] = new Recipes();
			return blankIngArray;
		}
		
		
		
		
	}

}
