package eduneu.info6205.components;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.math3.distribution.PoissonDistribution;

import fileParser.FileUtil;

public class RankingSystem {
	Map<String,Club> clubMap = new HashMap<>();
	private int totalGames = 0;
	private int homeGoals = 0;
	private int awayGoals=0;
	
	//Initialize all the data
	public static void Initilize() {
		int totalGames = 0;
		FileUtil fileUtil = new FileUtil();
		
		List<String> fileList = new ArrayList<>();
		String filePath1 = "./csv/2014-2015.csv" ;
		String filePath2 = "./csv/2015-2016.csv" ;
		String filePath3 = "./csv/2016-2017.csv" ;
		String filePath4 = "./csv/2017-2018.csv" ;
		String filePath5 = "./csv/2018-2019.csv" ;
		String filePath6 = "./csv/2019-2020.csv" ;
		fileList.add(filePath1);
		fileList.add(filePath2);
		fileList.add(filePath3);
		fileList.add(filePath4);
		fileList.add(filePath5);
		fileList.add(filePath6);
		
		for(String path: fileList) {
			for(String match : fileUtil.readFile(path)) {
				totalGames+=1;
				String[] args = match.split(",");
				String homeTeam = args[0];
				String awayTeam = args[1];
				int homeTeamGoals = Integer.parseInt(args[2]);
				int awayTeamGoals = Integer.parseInt(args[3]);
				int homeTeamShots = Integer.parseInt(args[4]);
				int awayTeamShots = Integer.parseInt(args[5]);
				int homeTeamShotsTarget = Integer.parseInt(args[6]);
				int awayTeamShotsTarget = Integer.parseInt(args[7]);
				int homeFouls = Integer.parseInt(args[8]);
				int awayFouls = Integer.parseInt(args[9]);
				int homeRedCard = Integer.parseInt(args[10]);
				int awayRedCard = Integer.parseInt(args[11]);
				
				//System.out.println("hometeam: "+homeTeam+ "awayteam: "+awayTeam+" "+totalGames );
			}
		}
		
	}
	
}
