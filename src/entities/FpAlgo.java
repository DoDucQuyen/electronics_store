package entities;

import java.io.IOException;

import ca.pfv.spmf.algorithms.associationrules.agrawal94_association_rules.AlgoAgrawalFaster94;
import ca.pfv.spmf.algorithms.associationrules.agrawal94_association_rules.AssocRule;
import ca.pfv.spmf.algorithms.associationrules.agrawal94_association_rules.AssocRules;
import ca.pfv.spmf.patterns.itemset_array_integers_with_count.Itemsets;
import ca.pfv.spmf.algorithms.frequentpatterns.apriori.AlgoApriori;

public class FpAlgo {
	public static void main(String []args) throws IOException{
		AssocRules rules = null;
		AlgoApriori apriori = new AlgoApriori();
		Itemsets patterns = apriori.runAlgorithm(0.15, "category_input", null);
		
		int databaseSize = apriori.getDatabaseSize();
	    apriori.printStats();
	    
	    AlgoAgrawalFaster94 algoAgrawal = new AlgoAgrawalFaster94();
	    rules = algoAgrawal.runAlgorithm(patterns, null, databaseSize, 0.05);
	    rules.printRules(databaseSize);

	}
	
}
