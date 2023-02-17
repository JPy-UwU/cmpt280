import lib280.tree.BasicMAryTree280;

public class SkillTree extends BasicMAryTree280<Skill> {

	/**	
	 * Create lib280.tree with the specified root node and
	 * specified maximum arity of nodes.  
	 * @timing O(1) 
	 * @param x item to set as the root node
	 * @param m number of children allowed for future nodes 
	 */
	public SkillTree(Skill x, int m)
	{
		super(x,m);
	}

	/**
	 * A convenience method that avoids typecasts.
	 * Obtains a subtree of the root.
	 * 
	 * @param i Index of the desired subtree of the root.
	 * @return the i-th subtree of the root.
	 */
	public SkillTree rootSubTree(int i) {
		return (SkillTree)super.rootSubtree(i);
	}

	public static void main(String[] args) {
		// The skill tree is based on an anime called `One Piece`
		BasicMAryTree280<String> opTree = new BasicMAryTree280<String>("One Piece", 3);
		opTree.setRootSubtree(new BasicMAryTree280<String> ("Combat Skills", 3), 1);
		opTree.setRootSubtree(new BasicMAryTree280<String>("Devil Fruits", 3), 2);
		opTree.setRootSubtree(new BasicMAryTree280<String>("Haki", 3), 3);

		BasicMAryTree280<String> combatSkills = opTree.rootSubtree(1);
		combatSkills.setRootSubtree(new BasicMAryTree280<String>("Hand-to-Hand", 3), 1);
		combatSkills.setRootSubtree(new BasicMAryTree280<String>("Swordsmanship", 3), 2);

		BasicMAryTree280<String> devilFruits = opTree.rootSubtree(2);
		devilFruits.setRootSubtree(new BasicMAryTree280<String>("Paramecia", 3), 1);
		devilFruits.setRootSubtree(new BasicMAryTree280<String>("Zoan", 3), 2);
		devilFruits.setRootSubtree(new BasicMAryTree280<String>("Logia", 3), 3);

		BasicMAryTree280<String> zoanDevilFruit = devilFruits.rootSubtree(2);
		zoanDevilFruit.setRootSubtree(new BasicMAryTree280<String>("Mythical Zoan", 1), 1);

		BasicMAryTree280<String> haki = opTree.rootSubtree(3);
		haki.setRootSubtree(new BasicMAryTree280<String>("Observation", 3), 1);
		haki.setRootSubtree(new BasicMAryTree280<String>("Armament", 3), 2);
		haki.setRootSubtree(new BasicMAryTree280<String>("Conqueror's", 3), 3);

		System.out.println(opTree.toString());
	}

}
