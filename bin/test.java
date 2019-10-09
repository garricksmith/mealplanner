
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Ingredients i = new Ingredients.Builder().calories(123).gSaturatedFat(321).create();
		int c;
		c = i.getCalories();
		System.out.print(c);
		

	}

}
