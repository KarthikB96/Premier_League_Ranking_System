package eduneu.info6205.components;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.apache.commons.math3.distribution.PoissonDistribution;

import fileParser.FileUtil;

public class RankingSystem {
	Map<String,Club> clubMap = new HashMap<>();
	private double totalGames = 0;
	private double totalHomeGoals = 0;
	private double totalAwayGoals=0;
	public double totalHomeAttackStrength=0;  //same as awayDefence Strength
	public double totalAwayAttackStrength=0;  //same as HomeDefenceStrength
	public double totalHomeDefenceStrength=0;
	public double totalAwayDefenceStrength=0;
	
	//Initialize all the data
	public void Initilize() {
		FileUtil fileUtil = new FileUtil();
		
		List<String> fileList = new ArrayList<>();
		//String filePath1 = "./csv/2014-2015.csv" ;
		String filePath2 = "./csv/2015-2016.csv" ;
		String filePath3 = "./csv/2016-2017.csv" ;
		String filePath4 = "./csv/2017-2018.csv" ;
		String filePath5 = "./csv/2018-2019.csv" ;
		String filePath6 = "./csv/2019-2020.csv" ;
		//fileList.add(filePath1);
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
				double homeTeamGoals = Integer.parseInt(args[2]);
				double awayTeamGoals = Integer.parseInt(args[3]);
				double homeTeamShots = Integer.parseInt(args[4]);
				double awayTeamShots = Integer.parseInt(args[5]);
				double homeTeamShotsTarget = Integer.parseInt(args[6]);
				double awayTeamShotsTarget = Integer.parseInt(args[7]);
				double homeFouls = Integer.parseInt(args[8]);
				double awayFouls = Integer.parseInt(args[9]);
				double homeRedCard = Integer.parseInt(args[10]);
				double awayRedCard = Integer.parseInt(args[11]);
				
				totalHomeGoals+=homeTeamGoals;
				totalAwayGoals+=awayTeamGoals;
				
				//adding home team credentials
				if(clubMap.get(homeTeam)==null) {
					Club club = new Club(homeTeam);
					club.homeGoals += homeTeamGoals;
					club.homeConceded += awayTeamGoals;
					club.homeShotsOnTarget += homeTeamShotsTarget;
					club.homeRedCards += homeRedCard;
					club.homeMatchesPlayed+=1;
					clubMap.put(homeTeam,club);
				}
				else {
					Club club = clubMap.get(homeTeam);
					club.homeGoals += homeTeamGoals;
					club.homeConceded += awayTeamGoals;
					club.homeShotsOnTarget += homeTeamShotsTarget;
					club.homeRedCards += homeRedCard;
					club.homeMatchesPlayed+=1;
					clubMap.put(homeTeam,club);
				}
				
				//adding away team credentials
				if(clubMap.get(awayTeam)==null) {
					Club club = new Club(awayTeam);
					club.awayGoals += awayTeamGoals;
					club.awayConceded += homeTeamGoals;
					club.awayShotsOnTarget += awayTeamShotsTarget;
					club.awayRedCards += awayRedCard;
					club.awayMatchesPlayed+=1;
					clubMap.put(awayTeam,club);
				}
				else {
					Club club = clubMap.get(awayTeam);
					club.awayGoals += awayTeamGoals;
					club.awayConceded += homeTeamGoals;
					club.awayShotsOnTarget += awayTeamShotsTarget;
					club.awayRedCards += awayRedCard;
					club.awayMatchesPlayed+=1;
					clubMap.put(awayTeam,club);
				}
				//System.out.println("hometeam: "+homeTeam+ "awayteam: "+awayTeam+" "+totalGames );
			}
		}
		
		totalHomeAttackStrength = totalHomeGoals/totalGames;
		totalAwayAttackStrength = totalAwayGoals/totalGames;
		totalHomeDefenceStrength = totalAwayAttackStrength;
		totalAwayDefenceStrength = totalHomeAttackStrength;
		
		System.out.println("Succesfully Initialized "+ totalHomeAttackStrength);
	}
	
	//Output is the win probability,draw probabilty and loss probability in a double array
	//output format:[homeTeamProbability,drawProbability,awayTeamProbability,Home Result,Away Result]
	public double[] predict(String homeTeamString, String awayTeamString) {
		int maxResultRow = 0;
		int maxResultColumn = 0;
		Club homeTeam = null;
		Club awayTeam = null;
		
		try {
			homeTeam = clubMap.get(homeTeamString);
		}
		catch(Exception e) {
			return null;
		}
		
		try {
			awayTeam = clubMap.get(awayTeamString);
		}
		catch(Exception e) {
			return null;
		}
		
		
//		System.out.println(homeTeam.toString());
//		System.out.println(awayTeam.toString());
		
		double homeTeamGoals = homeTeam.homeGoals;
		double homeTeamGames = homeTeam.homeMatchesPlayed;
		double AttackFormOfHomeTeam = (homeTeamGoals/homeTeamGames)/totalHomeAttackStrength;
		
		double homeTeamGoalsConceded = homeTeam.homeConceded;
		double DefenceFormOfHomeTeam = (homeTeamGoalsConceded/homeTeamGames)/totalHomeDefenceStrength;
		
		
		
		double awayTeamGoals = awayTeam.awayGoals;
		double awayTeamGames = awayTeam.awayMatchesPlayed;
		double AttackFormOfAwayTeam = (awayTeamGoals/awayTeamGames)/totalAwayAttackStrength;
		
		double awayTeamGoalsConceded = awayTeam.awayConceded;
		double DefenceFormOfAwayTeam = (awayTeamGoalsConceded/awayTeamGames)/totalAwayDefenceStrength;
		
		double homeTeamForm = AttackFormOfHomeTeam * DefenceFormOfAwayTeam * totalHomeAttackStrength;
//		System.out.println("Home Form: "+homeTeamForm);
		double awayTeamForm = AttackFormOfAwayTeam * DefenceFormOfHomeTeam * totalAwayAttackStrength;
//		System.out.println("Away Form: "+awayTeamForm);
		
		
		PoissonDistribution poissonDistributionHome = new PoissonDistribution(homeTeamForm);
		PoissonDistribution poissonDistributionAway = new PoissonDistribution(awayTeamForm);
		
//		for(int i=0;i<6;i++) {
//			System.out.println("for Home Team"+i+" "+ poissonDistributionHome.probability(i));
//			System.out.println("for Away Team"+i+" "+ poissonDistributionAway.probability(i));
//		}
		
		double[][] goalArray = new double[6][6];
		PriorityQueue<Double> probabilities = new PriorityQueue<Double>(Collections.reverseOrder());
		//considering each row to be away team goal chances and home team to be columns
		
		for(int i=0;i<goalArray.length;i++) {
			for(int j=0;j<goalArray[0].length;j++) {
				goalArray[i][j]= (poissonDistributionAway.probability(i)*poissonDistributionHome.probability(j))*100; //Getting Probability in terms of percentage
				probabilities.add(goalArray[i][j]);
			}
		}
		
		//finding the top 3 probabilities
		double[] prediction  = new double[9];  //{maxHomeResult1,maxAwayResult1,maxHomeResult2,maxAwayResult2,maxHomeResult3,maxAwayResult3}
		
		for(int i=0;i<prediction.length;i+=2) {
			double probability = probabilities.poll();
			//Finding the maximum probability
			for(int j=0;j<goalArray.length;j++) {
				for(int k=0;k<goalArray[0].length;k++) {
					if(goalArray[j][k]==probability) {
						if(i>=6) {
							break;
						}
						prediction[i]=k;
						prediction[i+1]=j;
					}
				}
			}
		}
		
//		double temp=0;
//		//Finding the maximum probability
//		for(int i=0;i<goalArray.length;i++) {
//			for(int j=0;j<goalArray[0].length;j++) {
//				if(goalArray[i][j]>temp) {
//					temp=goalArray[i][j];
//					maxResultRow = i;
//					maxResultColumn = j;
//				}
//			}
//		}
		
		//printing the probability matrix
//		for(double[] row: goalArray) {
//		System.out.println(Arrays.toString(row));
//		}
		
		
		double homeTeamProbability = 0;
		for(int i=0;i<goalArray.length;i++) {
			for(int j=i+1;j<goalArray[0].length;j++) {
				homeTeamProbability+=goalArray[i][j];
			}
		}
		
		double drawProbability=0;
		for(int i=0;i<goalArray[0].length;i++) {
			drawProbability+=goalArray[i][i];
		}
		
		double awayTeamProbability=0;
		for(int j=0;j<goalArray[0].length;j++) {
			for(int i=j+1;i<goalArray.length;i++) {
				awayTeamProbability+=goalArray[i][j];
			}
		}
		
//		System.out.println(homeTeam.getName()+" winProbability: "+homeTeamProbability+" draw Probability:"
//				+ " "+drawProbability+" "+awayTeam.getName()+" away Team Probability: "+awayTeamProbability);
		prediction[6]=homeTeamProbability;
		prediction[7]=drawProbability;
		prediction[8]=awayTeamProbability;
//		double[] prediction = {homeTeamProbability,drawProbability,awayTeamProbability,maxResultColumn,maxResultRow};
		
		return prediction;
		
	}
	
}
