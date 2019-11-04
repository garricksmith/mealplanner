import java.sql.*;
import java.util.*;
public class IngredientsFacade {
	Ingredients[] ingArray = new Ingredients[100];
	
	public Ingredients[] getIngredients() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mealplanner?useSSL=false&serverTimezone=UTC", "root", "BaoBao729825429");
	
		PreparedStatement stmt = con.prepareStatement("SELECT * from ingredients");
		ResultSet rs = stmt.executeQuery();
	
		int count = 0;
		while(rs.next()) {
			int ingredientId = rs.getInt("ingredient_id");
			String ingredientName= rs.getString("ingredient_name");
			String ingredientDesc = rs.getString("ingredient_desc");
			String servingMetric = rs.getString("serving_metric");
			int gUnsaturatedFat = rs.getInt("g_unsaturated_fat");
			int gSaturatedFat = rs.getInt("g_saturated_fat");
			int gTransFat = rs.getInt("g_trans_fat");
			int myCholesterol = rs.getInt("my_cholesterol");
			int mgSodium = rs.getInt("mg_sodium");
			int gFiber = rs.getInt("g_fiber");
			int gSugar = rs.getInt("g_sugar");
			int gProtein = rs.getInt("g_protein");

			Ingredients ing = new Ingredients.Builder().ingredientId(ingredientId).ingredientName(ingredientName).ingredientDesc(ingredientDesc).servingMetric(servingMetric).gUnsaturatedFat(gUnsaturatedFat).gSaturatedFat(gSaturatedFat).gTransFat(gTransFat).mgCholesterol(myCholesterol).mgSodium(mgSodium).gFiber(gFiber).gSugar(gSugar).gProtein(gProtein).create();
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
	
	
	public Ingredients[] createIngredients(Ingredients theIngredientsToAdd) throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mealplanner?useSSL=false&serverTimezone=UTC", "root", "BaoBao729825429");
		
		
		PreparedStatement cstmt = con.prepareStatement("INSERT INTO ingredients VALUE (?,?,?,?,?,?,?,?,?,?,?,?)");
		cstmt.setInt(1, theIngredientsToAdd.getId());
		cstmt.setString(2,theIngredientsToAdd.getIngredientName());
		cstmt.setString(3, theIngredientsToAdd.getIngredientDesc());
		cstmt.setString(4, theIngredientsToAdd.getServingMetric());
		cstmt.setInt(5, theIngredientsToAdd.getGUnsaturatedFat());
		cstmt.setInt(6, theIngredientsToAdd.getGSaturatedFat());
		cstmt.setInt(7, theIngredientsToAdd.getGTransFat());
		cstmt.setInt(8,theIngredientsToAdd.getMgCholesterol());
		cstmt.setInt(9, theIngredientsToAdd.getMgSodium());
		cstmt.setInt(10, theIngredientsToAdd.getGFiber());
		cstmt.setInt(11, theIngredientsToAdd.getGSugar());
		cstmt.setInt(12, theIngredientsToAdd.getGProtein());

		int res = cstmt.executeUpdate();
		if(res==1) {
			PreparedStatement retrieveStmt = con.prepareStatement("Select * from ingredients WHERE recipe_id=? AND user_id = ? AND meal_date = ? AND meal_time = ?");
			retrieveStmt.setInt(1, theIngredientsToAdd.getId());
			retrieveStmt.setString(2, theIngredientsToAdd.getIngredientName());
			retrieveStmt.setString(3, theIngredientsToAdd.getIngredientDesc());
			retrieveStmt.setString(4, theIngredientsToAdd.getServingMetric());
			retrieveStmt.setInt(5, theIngredientsToAdd.getGUnsaturatedFat());
			retrieveStmt.setInt(6, theIngredientsToAdd.getGSaturatedFat());
			retrieveStmt.setInt(7, theIngredientsToAdd.getGTransFat());
			retrieveStmt.setInt(8, theIngredientsToAdd.getMgCholesterol());
			retrieveStmt.setInt(9, theIngredientsToAdd.getMgSodium());
			retrieveStmt.setInt(10, theIngredientsToAdd.getGFiber());
			retrieveStmt.setInt(11, theIngredientsToAdd.getGSugar());
			retrieveStmt.setInt(12, theIngredientsToAdd.getGProtein());

			ResultSet rs = retrieveStmt.executeQuery();
			int count = 0;

			
			while(rs.next()) {
				int theIngredientsId = rs.getInt("ingredient_id");
				String theIngredientsName = rs.getString("ingredient_name");
				String theIngredientsDesc = rs.getString("ingredient_desc");
				String theIngredientsMetric = rs.getString("serving_metric");
				int theIngredientsGUF = rs.getInt("g_unsaturated_fat");
				int theIngredientsGSF = rs.getInt("g_saturated_fat");
				int theIngredientsFTF = rs.getInt("g_trans_fat");
				int theIngredientsMC = rs.getInt("my_cholesterol");
				int theIngredientsMS = rs.getInt("mg_sodium");
				int theIngredientsGF= rs.getInt("g_fiber");
				int theIngredientGS = rs.getInt("g_sugar");
				int theIngredientGP = rs.getInt("g_protein");
	
				
				Ingredients ing = new Ingredients.Builder().ingredientId(theIngredientsId).ingredientName(theIngredientsName).ingredientDesc(theIngredientsDesc).servingMetric(theIngredientsMetric).gUnsaturatedFat(theIngredientsGUF).gSaturatedFat(theIngredientsGSF).gTransFat(theIngredientsFTF).mgCholesterol(theIngredientsMC).mgSodium(theIngredientsMS).gFiber(theIngredientsGF).gSugar(theIngredientGS).gProtein(theIngredientGP).create();
				
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
			Ingredients[] blankIngArray = new Ingredients[1];
			blankIngArray[0] = null;
			return blankIngArray;
		}
		
		
		
		
	}


}
