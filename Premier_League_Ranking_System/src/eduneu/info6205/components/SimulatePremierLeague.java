package eduneu.info6205.components;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import fileParser.FileUtil;

public class SimulatePremierLeague {
	Map<String,ClubRow> tableMap = new HashMap<>();
	
	public void initialize() {
		FileUtil fileUtil = new FileUtil();
		String plTable = "./csv/PremierLeague_Table.csv";
		for(String row: fileUtil.readFile(plTable)) {
			String[] args = row.split(",");
			
			String name = args[0];
			int played = Integer.parseInt(args[1]);
			int won = Integer.parseInt(args[2]);
			int drew = Integer.parseInt(args[3]);
			int lost = Integer.parseInt(args[4]);
			int GF = Integer.parseInt(args[5]);
			int GA = Integer.parseInt(args[6]);
			int GD = Integer.parseInt(args[7]);
			int points = Integer.parseInt(args[8]);
			
			ClubRow club = new ClubRow();
			club.setName(name);
			club.setWon(won);
			club.setLost(lost);
			club.setDrew(drew);
			club.setPlayed(played);
			club.setGoalsFired(GF);
			club.setGoalsAcquired(GA);
			club.setGoalDifference(GD);
			club.setPoints(points);
			
			tableMap.put(name, club);
		}
//		for(Map.Entry<String, ClubRow> entry: tableMap.entrySet()) {
//			System.out.println(entry.getValue().toString());
//		}
	}
	
	
	
	public void simulate() {
		FileUtil fileUtil = new FileUtil();
		
		String fixtures = "./csv/Fixtures.csv";
		
		RankingSystem rankingSystem = new RankingSystem();
		rankingSystem.Initilize();
		
		for(String teams : fileUtil.readFile(fixtures)) {
			String[] args = teams.split(",");
			double[] prediction=rankingSystem.predict(args[0], args[1]);  //{maxHomeResult1,maxAwayResult1,maxHomeResult2,maxAwayResult2,maxHomeResult3,maxAwayResult3}
			
			
			//considering the score with highest probability
			int homeResult = (int)prediction[0];
			int awayResult = (int)prediction[1];
			System.out.println(args[0] +" vs "+args[1]+": Home Team winProbability: "+prediction[6]+" draw Probability:"
					+ " "+prediction[7]+"  away Team WinProbability: "+prediction[8]);
			System.out.println(" predicted scores: "+" first: "+homeResult+"-"+awayResult+" second: "+(int)prediction[2]+"-"+(int)prediction[3]+" third: "+(int)prediction[4]+"-"+(int)prediction[5]);
			String homeTeamString = args[0];
			String awayTeamString = args[1];
			ClubRow homeRow = tableMap.get(homeTeamString);
			homeRow.setPlayed(homeRow.getPlayed()+1);
			ClubRow awayRow = tableMap.get(awayTeamString);
			awayRow.setPlayed(awayRow.getPlayed()+1);
			
			if(homeResult>awayResult) {
				//home team has won
				
				//setting home team table
				homeRow.setGoalsFired(homeRow.getGoalsFired()+homeResult);
				homeRow.setGoalsAcquired(homeRow.getGoalsAcquired()+awayResult);
				homeRow.setPoints(homeRow.getPoints()+3);
				homeRow.setWon(homeRow.getWon()+1);
				
				int homeGD = homeRow.getGoalsFired()-homeRow.getGoalsAcquired();
				homeRow.setGoalDifference(homeGD);
				
				//setting away team table
				awayRow.setGoalsFired(awayRow.getGoalsFired()+awayResult);
				awayRow.setGoalsAcquired(awayRow.getGoalsAcquired()+homeResult);
				awayRow.setLost(awayRow.getLost()+1);
				
				int awayGD= awayRow.getGoalsFired() - awayRow.getGoalsAcquired();
				awayRow.setGoalDifference(awayGD);
				
				tableMap.put(homeTeamString, homeRow);
				tableMap.put(awayTeamString,awayRow);
			}
			else if(homeResult<awayResult) {
				//away team has won
				
				//setting away team table
				awayRow.setGoalsFired(awayRow.getGoalsFired()+awayResult);
				awayRow.setGoalsAcquired(awayRow.getGoalsAcquired()+homeResult);
				awayRow.setPoints(awayRow.getPoints()+3);
				awayRow.setWon(awayRow.getWon()+1);
				
				int awayGD = awayRow.getGoalsFired()-awayRow.getGoalsAcquired();
				awayRow.setGoalDifference(awayGD);
				
				//setting home team table
				homeRow.setGoalsFired(homeRow.getGoalsFired()+homeResult);
				homeRow.setGoalsAcquired(homeRow.getGoalsAcquired()+awayResult);
				homeRow.setLost(homeRow.getLost()+1);
				
				int homeGD = homeRow.getGoalsFired()-homeRow.getGoalsAcquired();
				homeRow.setGoalDifference(homeGD);
				
				tableMap.put(homeTeamString,homeRow);
				tableMap.put(awayTeamString,awayRow);
			}
			else {
				//draw
				
				//setting home team table
				homeRow.setGoalsFired(homeRow.getGoalsFired()+homeResult);
				homeRow.setGoalsAcquired(homeRow.getGoalsAcquired()+awayResult);
				homeRow.setPoints(homeRow.getPoints()+1);
				homeRow.setDrew(homeRow.getDrew()+1);
				
				//setting away team table
				awayRow.setGoalsFired(awayRow.getGoalsFired()+awayResult);
				awayRow.setGoalsAcquired(awayRow.getGoalsAcquired()+homeResult);
				awayRow.setPoints(awayRow.getPoints()+1);
				awayRow.setDrew(awayRow.getDrew()+1);
				
				tableMap.put(homeTeamString,homeRow);
				tableMap.put(awayTeamString,awayRow);
			}
		}
		Comparator<Map.Entry<String, ClubRow>> comparator = new Comparator<Map.Entry<String, ClubRow>>() {

			@Override
			public int compare(Entry<String, ClubRow> o1, Entry<String, ClubRow> o2) {
				// TODO Auto-generated method stub
				int result=Integer.compare(o2.getValue().getPoints(),o1.getValue().getPoints());
				if(result==0) {
					return Integer.compare(o2.getValue().getGoalDifference(),o1.getValue().getGoalDifference());
				}
				return Integer.compare(o2.getValue().getPoints(),o1.getValue().getPoints());
			}
			
		};
		
		Set<Map.Entry<String, ClubRow>> finalTableSet = tableMap.entrySet(); 
		List<Map.Entry<String, ClubRow>> finalTable = finalTableSet.stream().collect(Collectors.toList());
		Collections.sort(finalTable,comparator);
		
		//creating a csv file to store the final league table
		fileUtil.createFile(finalTable);
		//printing the table
		for(Map.Entry<String, ClubRow> entry: finalTable) {
			System.out.println(entry.getValue().toString());
		}
	}
}
