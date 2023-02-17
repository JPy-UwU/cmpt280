import lib280.list.LinkedList280;
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

	public LinkedList280<Skill> skillDependencies(String sName) {
		LinkedList280<Skill> l =  new LinkedList280<Skill>();

		if (skillDependenciesHelper(this, sName, l)) return l;
		else throw new RuntimeException("The skill was not found.");
	}

	protected boolean skillDependenciesHelper(SkillTree root, String sName, LinkedList280<Skill> l) {
		if (root.isEmpty()) return false;
		if (root.rootItem().getSkillName().equals(sName)) {
			l.insert(root.rootItem());
			return true;
		}

		for (int i = 1; i <= root.rootLastNonEmptyChild(); i++) {
			if (skillDependenciesHelper(root.rootSubTree(i), sName, l)) {
				l.insert(root.rootItem());
				return true;
			}
		}
		return  false;
	}

	public int skillTotalCost(String sName) {
		LinkedList280<Skill> l = new LinkedList280<Skill>();
		l = skillDependencies(sName);

		int total = 0;
		l.goFirst();

		while (l.itemExists()) {
			total += l.item().getSkillCost();
			l.goForth();
		}
		return total;
	}

	public static void main(String[] args) {
		// The skill tree is based on an anime called `One Piece`
		Skill pirate = new Skill("Pirate", "Enter the most interesting world of One Piece.", 3);
		Skill devilFruits = new Skill("Devil Fruits", "A fruit that gives powers to it's eater.", 3);
		Skill haki = new Skill("Haki", "A power that allows the user to utilize their own spiritual energy.", 5);
		Skill paramecia = new Skill("Paramecia", "A fruit that allow users to generate and manipulate substances.", 3);
		Skill zoan = new Skill("Zoan", "A fruit that allow users to change into specific species.", 4);
		Skill logia = new Skill("Logia", "A fruit that allow their users to create, control, and transform their body into a natural element.", 6);
		Skill ancient = new Skill("Ancient", "A fruit that allow the user to transform into an extinct species.", 6);
		Skill mythical = new Skill("Mythical", "A fruit that allow the user to transform into mythological creatures.", 8);
		Skill artificial = new Skill("Artificial", "A fruit that allow the user permanently take on an animal characteristic.", 6);
		Skill observation = new Skill("Observation", "Allows user to sense the presence, strength, and emotions of other people.", 10);
		Skill armament = new Skill("Armament", "Allows user to use their own spiritual energy as armor to defend against attacks, as well as make their own attacks more potent.s", 9);
		Skill conqueror = new Skill("Conqueror's", "Allows user the ability to unleash their own willpower to overpower the will of others.", 15);

		SkillTree op = new SkillTree(pirate, 2);
		op.setRootSubtree(new SkillTree(devilFruits, 3), 1);
		op.setRootSubtree(new SkillTree(haki, 3), 2);

		SkillTree temp = op.rootSubTree(1);
		temp.setRootSubtree(new SkillTree(paramecia, 0), 1);
		temp.setRootSubtree(new SkillTree(zoan, 3), 2);
		temp.setRootSubtree(new SkillTree(logia, 0), 3);

		SkillTree temp2 = temp.rootSubTree(2);
		temp2.setRootSubtree(new SkillTree(ancient, 0), 1);
		temp2.setRootSubtree(new SkillTree(mythical, 0), 2);
		temp2.setRootSubtree(new SkillTree(artificial, 0), 3);

		temp = op.rootSubTree(2);
		temp.setRootSubtree(new SkillTree(observation, 1), 1);
		temp.setRootSubtree(new SkillTree(armament, 1), 2);
		temp.setRootSubtree(new SkillTree(conqueror, 1), 3);

		System.out.println(op.toStringByLevel());

		// testcases for skillDependencies
		try {
			op.skillDependencies("YOHOHO");
			System.out.println("Expected RuntimeException.");
		}
		catch (RuntimeException e) {
			// Expected output
		}

		System.out.println("Dependencies for Conqueror's: ");
		System.out.println(op.skillDependencies("Conqueror's"));
		System.out.println("Dependencies for Mythical: ");
		System.out.println(op.skillDependencies("Mythical"));

		// testcases for skillTotalCost
		try {
			op.skillTotalCost("YOHOHO");
			System.out.println("Expected RuntimeException.");
		}
		catch (RuntimeException e) {
			// Expected output
		}

		System.out.println("Total cost for Conqueror's skill: " + op.skillTotalCost("Conqueror's"));
		System.out.println("Total cost for Mythical skill: " + op.skillTotalCost("Mythical"));
	}

}
