
After cloning,
include commons-math3-3.6.1.jar.
select the Premier_League_Ranking_System, right click on the project -> buid path -> configure build path -> libraries
-> Add Jars -> Select commons-math3-3.6.1/commons-math3-3.6.1.jar and apply.

Steps to run the application.
1. The Driver Class with the main function.
2. All csv files are under the csv folder. Current Premier league table is in PremierLeague_Table.csv and the upcoming fixtures are in Fixtures.csv.
3. Club class is used to hold the create teams that is used for prediction,.
4. ClubRow class is used to create team objects that represent each row in the premier league table.

Simulaing the Premier League:
1. To simulate the premier league and get the table we use the SimulatePremierLeague class.
2. call the initialize method which initializes the table of 2019-2020 season.
3. to simulate the league, call the simulate method and the table is stored in Final_League_Table.csv under the csv folder.

Predicting the result of a single game:
1. To predict a single match between two premier league teams, first create an object of RankingSystem.
2. Then we need to initalize the data to calculate the prediction. run the initialize() first.
3. To predict a single match, call the predict("Team1","Team2") method that returns an array of double.
4.double[] result = {maxHomeResult1,maxAwayResult1,maxHomeResult2,maxAwayResult2,maxHomeResult3,maxAwayResult3,home team win probability,draw probability,away team win probability }
