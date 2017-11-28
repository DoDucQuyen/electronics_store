package logic;

import ca.pfv.spmf.algorithms.associationrules.agrawal94_association_rules.AlgoAgrawalFaster94;
import ca.pfv.spmf.algorithms.associationrules.agrawal94_association_rules.AssocRules;
import ca.pfv.spmf.algorithms.frequentpatterns.apriori.AlgoApriori;
import ca.pfv.spmf.algorithms.frequentpatterns.fpgrowth.AlgoFPGrowth;
import ca.pfv.spmf.patterns.itemset_array_integers_with_count.Itemsets;
import logic.ProductRuleLogic;

public class AlgorithmLogic {

	public boolean generateRulesByApriori(String input, double minSup, double minConf) {
		boolean result;
		ProductRuleLogic productRule = new ProductRuleLogic();
		AssocRules rules = null;

		try {
		    // Step1: find frequent itemsets by applying the Apriori algorithm
		    AlgoApriori apriori = new AlgoApriori();
		    Itemsets patterns = apriori.runAlgorithm(minSup, input, null);
		    int databaseSize = apriori.getDatabaseSize();
		    apriori.printStats();
		    patterns.printItemsets(databaseSize);

		    // Setp2: generate rules
		    AlgoAgrawalFaster94 algoAgrawal = new AlgoAgrawalFaster94();
		    rules = algoAgrawal.runAlgorithm(patterns, null, databaseSize, minConf);
		    rules.printRules(databaseSize);
		} catch (Exception e) {
		    e.printStackTrace();
		    return false;
		}

		result = productRule.saveRules(rules);

		return result;
    }
	
	public AssocRules generateRulesByFPGrowth(String input, double minSup, double minConf) {
		AssocRules rules = null;

		try {
		    // Step1: find frequent itemsets by applying the FP-Growth algorithm
		    AlgoFPGrowth fpgrowth = new AlgoFPGrowth();
		    Itemsets patterns = fpgrowth.runAlgorithm(input, null, minSup);
		    int databaseSize = fpgrowth.getDatabaseSize();
		    fpgrowth.printStats();
		    patterns.printItemsets(databaseSize);

		    // Setp2: generate rules
		    AlgoAgrawalFaster94 algoAgrawal = new AlgoAgrawalFaster94();
		    rules = algoAgrawal.runAlgorithm(patterns, null, databaseSize, minConf);
		    System.out.println("rules");
		    rules.printRules(databaseSize);
		} catch (Exception e) {
		    rules = null;
		    e.printStackTrace();
		    return null;
		}

		return rules;
	    }
}
