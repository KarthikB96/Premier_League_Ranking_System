package eduneu.info6205.components;

import fileParser.FileUtil;

public class SimulatePremierLeague {

	
	public static void simulate() {
		FileUtil fileUtil = new FileUtil();
		
		String fixtures = "./csv/Fixtures.csv";
		
		RankingSystem rankingSystem = new RankingSystem();
		rankingSystem.Initilize();
		
		for(String teams : fileUtil.readFile(fixtures)) {
			String[] args = teams.split(",");
			System.out.println(args[0] +" vs "+args[1]);
			rankingSystem.predict(args[0], args[1]);
		}
	}
}
