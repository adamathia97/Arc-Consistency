// ONLY EDIT THE GAC() METHOD BELOW
import java.util.List;
import java.util.ArrayList;


/*
 * STUDENT 1 NAME:Aayush Damathia
 * STUDENT 1 NUMBER:300178855
 */

/*
 * A class representing a CSP.
 * A CSP consists of Constraints and Arcs to the Variables involved.
 */

public class CSP
{

	//private Variable[] vars;
	private Constraint[] cons;
	private ArrayList<Arc> toDoArcs;
	
	// Constructor
	public CSP(Variable[] v, Constraint[] c)
	{
		//vars = v;
		cons = c;
		toDoArcs = new ArrayList<Arc>();
		for (Constraint constraint: cons)
		{
			for (Variable variable: constraint.getVariables())
			{
				Arc arc = new Arc(constraint, variable);
				toDoArcs.add(arc);
			}
		}
	}
	
	/*
	 * For binary constraints, return the other variable involved
	 * in Constraint c with Variable v1.
	 */
	public Variable getOtherVariable(Variable v1, Constraint c)
	{
		List<Variable> allConVars = c.getVariables();
		
		if (allConVars.size() == 2) {
			if (v1.equals(allConVars.get(0)))
				return allConVars.get(1);
			else if (v1.equals(allConVars.get(1)))
				return allConVars.get(0);
		}
		return null;
	}
	
	/*
	 * This is the only method you need to implement.
	 * Follow the provided pseudo-code.
	 */
	public void GAC()
	{
		while(!toDoArcs.isEmpty())	// WHILE toDoArcs is not empty:
		{
			Arc arc1 = toDoArcs.get(0);// GET an Arc from toDoArcs
			toDoArcs.remove(arc1);		//Had to put this in because if not it would run infinitly
			Variable X = arc1.getVariable(); // Arc involves some Variable X and Constraint C
			Constraint C = arc1.getConstraint();
			
			List<Object> dx = X.getDomain();	// dx is domain of X
			List<Object> ndx = new ArrayList<Object>();	// ndx will be new domain of X
			
			for(Object val : dx)	// FOR EACH value val in dx:
			{
				Boolean satisfied = false;	//	   boolean satisfied = false;
				Assignment new_Assig = new Assignment();	//	   create new Assignment
				new_Assig.setAssignment(X,val);	//     SET assignment of X to val
			
				if(C.getVariables().size() == 2)//     IF C is binary constraint:
				{	
					Variable O = C.getVariables().get(0);	//	       GET other variable O involved in C
					if(O.getID().equals(X.getID()))			//Check whether that variable O is not the same as variable X P.S Thank you 
					{
						O = C.getVariables().get(1);
					}
					List<Object> dO = O.getDomain();	//		   dO is domain of O
			
					for(Object val2 : dO)	//	       FOR each value val2 in dO:
					{
						new_Assig.setAssignment(O,val2);	//			   SET assignment of O to val2
			
						if(C.constraintIsSatisfied(new_Assig))	//			   IF constraint is satisfied:
						{
							satisfied = true;	//			       satisfied = true
							break;	//                 break
						}
					}
				}
				else	//    ELSE: (is unary constraint)
				{
					if(C.constraintIsSatisfied(new_Assig))	//		  IF constraint is satisfied:
					{
						satisfied = true;	//			   satisfied = true
					}
				}
				if(satisfied)	//    IF satisfied:
				{	
					ndx.add(val);	//		   ADD val to ndx
				}
			}
			if(ndx.size() < dx.size())	// IF size of ndx < size of dx: (values have been removed)
			{
				X.setDomain(ndx);	//		set domain of x to ndx
			
									//		update toDoArcs
									//		(add Arcs involving other variables involved in other
									//		constraints with X)
				for(Constraint temp : X.getConstraints())
				{
					for(Variable temp1 : temp.getVariables())
					{
						toDoArcs.add(new Arc(temp,temp1));
					}
				}
			}
		}	
	}
}
