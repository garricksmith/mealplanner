
public class RecipesIngredients {
	private int ingredientId;
	private int recipeId;
	private int numIngredientServings;
	
	public RecipesIngredients() {
		ingredientId = 0;
		recipeId = 0;
		numIngredientServings = 0;
	}
	public RecipesIngredients(Ingredients ingredientsid, Recipes recipesid, int numIngredientServings) {
		this.ingredientId = ingredientsid.getId();
		this.recipeId = recipesid.getId();
		this.numIngredientServings  = numIngredientServings; 
	}
	public int getIngredientId() {
		return ingredientId;
	}
	public int getRecipesId() {
		return recipeId;
	}
	public int getNumIngredientServings() {
		return numIngredientServings;
	}
	public int setIngredientId(Ingredients ingredientsid) {
		this.ingredientId = ingredientsid.getId();
		return this.ingredientId;
	}
	public int setRecipesId(Recipes recipesid) {
		this.recipeId = recipesid.getId();
		return this.recipeId;
	}
	public int setNumIngredientServings(int numIngredientServings) {
		this.numIngredientServings = numIngredientServings;
		return this.numIngredientServings;
	}
	
}
