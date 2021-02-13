// DO NOT EDIT THIS FILE
import java.util.ArrayList;
import java.util.List;

/*
 * A binary constraint specifying that one Variable
 * has to be less than another Variable.
 */
public class LessThanConstraint implements Constraint
{
    // Two variables involved in Constraint
	private Variable var1;
	private Variable var2;
	
	// Keep track of the Variables involved in the Constraint
	private List<Variable> variablesInScope;
	
	// A simple ID for each Constraint
	private String constraintID;
	
	// Constructor takes ID and two Variables
	public LessThanConstraint(String cid, Variable var1, Variable var2)
	{
		constraintID = cid;
		this.var1 = var1;
		this.var2 = var2;
		variablesInScope = new ArrayList<Variable>(2);
		variablesInScope.add(var1);
		variablesInScope.add(var2);
		for (Variable v: variablesInScope)
		{
			v.addConstraint(this);
		}
	}
	
	public String getID()
	{
		return constraintID;
	}
	
	public List<Variable> getVariables()
	{
		return variablesInScope;
	}
	
	// Given an Assignment of values to Variable,
	// check whether this Constraint is satisfied.
	// In this case, constraint is only satisfied if
	// val1 < val 2. 
	public boolean constraintIsSatisfied(Assignment asn)
	{
		Integer val1 = (Integer) asn.getAssignment(var1);
		Integer val2 = (Integer) asn.getAssignment(var2);
		return val1 < val2;
	}
	
	public String toString()
	{
		return constraintID;
	}
	
}
