package nl.niek.minor.aa.hillclimb.field;

/**
 * Create complete fields to use as defaults or examples.
 * 
 * @author Niek
 * 
 */
public class FieldFactory
{
	/**
	 * 
	 * @return A new RightDownField instance with a default layout.
	 */
	public static RightDownField getDefaultField()
	{
		RightDownField field = new RightDownField();

		int[] row0 = new int[] { 0, 5, 2, 1, 6, 3, 5, 6, 2, 7 };
		int[] row1 = new int[] { 4, 9, 3, 5, 7, 2, 8, 3, 5, 2 };
		int[] row2 = new int[] { 8, 3, 9, 4, 2, 1, 1, 4, 3, 2 };
		int[] row3 = new int[] { 2, 4, 5, 1, 4, 3, 4, 3, 7, 9 };
		int[] row4 = new int[] { 1, 9, 2, 6, 2, 9, 3, 5, 7, 1 };
		int[] row5 = new int[] { 1, 9, 2, 6, 6, 9, 3, 5, 4, 3 };
		int[] row6 = new int[] { 1, 7, 2, 6, 2, 9, 4, 5, 7, 5 };
		int[] row7 = new int[] { 1, 9, 2, 6, 7, 6, 1, 9, 2, 7 };
		int[] row8 = new int[] { 1, 7, 2, 6, 2, 7, 3, 5, 7, 7 };
		int[] row9 = new int[] { 1, 2, 3, 4, 2, 9, 3, 5, 4, 0 };

		field.addRow(row0);
		field.addRow(row1);
		field.addRow(row2);
		field.addRow(row3);
		field.addRow(row4);
		field.addRow(row5);
		field.addRow(row6);
		field.addRow(row7);
		field.addRow(row8);
		field.addRow(row9);

		return field;
	}
}
