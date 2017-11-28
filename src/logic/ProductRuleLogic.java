package logic;

import ca.pfv.spmf.algorithms.associationrules.agrawal94_association_rules.AssocRule;
import ca.pfv.spmf.algorithms.associationrules.agrawal94_association_rules.AssocRules;
import dao.ProductRuleDao;

public class ProductRuleLogic {

	public boolean saveRules(AssocRules rules) {
		if (rules == null) {
		    return false;
		}
		boolean result = true;
		int supportCount;
		double confidence;
		StringBuilder itemset1 = new StringBuilder("");
		StringBuilder itemset2 = new StringBuilder("");
		ProductRuleDao productRule = new ProductRuleDao();

		if (!productRule.deleteAllRules()) {
		    result = false;
		} else {
		    for (AssocRule rule : rules.getRules()) {
			supportCount = rule.getAbsoluteSupport();
			confidence = rule.getConfidence();
			itemset1.replace(0, itemset1.length(), "");
			itemset2.replace(0, itemset2.length(), "");
			for (Integer i : rule.getItemset1()) {
			    itemset1.append(i);
			}
			for (Integer i : rule.getItemset2()) {
			    itemset2.append(i);
			}

			if (!productRule.saveRule(Integer.parseInt(itemset1.toString()), Integer.parseInt(itemset2.toString()), supportCount, confidence)) {
			    result = false;
			    break;
			}
		    }
		}

		return result;
	    }

}
