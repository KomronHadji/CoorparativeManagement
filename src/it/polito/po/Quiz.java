package it.polito.po;

public class Quiz {
	final static public String[] questions = {
	"Which among the following statements are correct?",
	"What is a 'baseline' in configuration management?",
	"Which among the following statements are correct for a comparator?"
	};
	final static public String[][] options = {
	{
		"Validation is the assurance that the product meets the needs of the identified stakeholders",
		"The waterfall model lacks flexibility",
		"In the waterfall model the high-level design is used to devise the integration testing",
		"Validation often involves acceptance and suitability with external customers",
		"Exhaustive testing is not usually feasible"	},
	{
		"A frozen configuration",
		"A stable configuration item",
		"A set of configuration items, each in a specific version",
		"A configuration not yet ready for delivery",
		"The first revision of a configuration item"	},
	{
		"A comparator can be passed as argument to method sort() of class Collections",
		"A comparator implements method compare()",
		"A comparator is an object implementing interface Comparator<T>",
		"A comparator can be passed as an argument to the constructor of HashSet",
		"A comparator implements method compareTo()"	}
	};
	
	/**
	 * Return the index of the right answer(s) for the given question 
	 */
	public static int[] answer(int question){
		// TODO: answer the question
		
		switch(question){
			case 0: return null; // replace with your answers
			case 1: return null; // replace with your answers
			case 2: return null; // replace with your answers
		}
		return null; // means: "No answer"
	}

	/**
	 * When executed will show the answers you selected
	 */
	public static void main(String[] args){
		for(int q=0; q<questions.length; ++q){
			System.out.println("Question: " + questions[q]);
			int[] a = answer(q);
			if(a==null || a.length==0){
				System.out.println("<undefined>");
				continue;
			}
			System.out.println("Answer" + (a.length>1?"s":"") + ":" );
			for(int i=0; i<a.length; ++i){
				System.out.println(a[i] + " - " + options[q][a[i]]);
			}
		}
	}
}

