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
		int[] row4 = new int[] { 1, 9, 2, 6, 2, 9, 3, 5, 7, 0 };

		field.addRow(row0);
		field.addRow(row1);
		field.addRow(row2);
		field.addRow(row3);
		field.addRow(row4);

		return field;
	}
}
