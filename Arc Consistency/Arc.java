// DO NOT EDIT THIS FILE

/*
 * A simple class representing an Arc,
 * a connection between a Variable and a Constraint.
 */
public class Arc
{
    // A Constraint and a Variable
	private Constraint con;
	private Variable var;
	
	// Constructor
	public Arc(Constraint c, Variable v)
	{
		con = c;
		var = v;
	}
	
	// Getters
	public Constraint getConstraint()
	{
		return con;
	}
	
	public Variable getVariable()
	{
		return var;
	}
	
	public String toString()
	{
		return "Arc: "+con.toString()+" "+var.toString();
	}
}
