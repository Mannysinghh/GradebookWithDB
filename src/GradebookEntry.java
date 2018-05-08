import java.io.IOException;
import java.sql.*;
import java.util.*;

public class GradebookEntry{

    public static void main(String[] args) throws IOException{

		Gradebook test = new Gradebook();
		boolean exit = false;
		test.readStudents();
		do{
			Scanner sc = new Scanner(System.in);
			System.out.println("\n       Gradebook Menu");
			System.out.println("---------------------------");
			System.out.println("1. Set Weights of each category");
			System.out.println("2. Add new Exam");
			System.out.println("3. Add new Homework");
			System.out.println("4. Add new Quiz");
			System.out.println("5. Set Final grade");
			System.out.println("6. Change a student's score");
			System.out.println("7. Calculate final grade");
			System.out.println("9. Exit");
			System.out.println("Please select an option:");
			int input = sc.nextInt();
			switch(input){

				case 1:
					System.out.print("Enter weight of exams:");
					float examWeight = sc.nextFloat();
					test.setExamWeight(examWeight);
					System.out.print("Enter weight of homework:");
					float hwWeight = sc.nextFloat();
					System.out.print("Enter weight of quizzes:");
					float quizWeight = sc.nextFloat();
					System.out.print("Enter weight of final:");
					float finalWeight = sc.nextFloat();
					if(examWeight + hwWeight + quizWeight + finalWeight != 100){
						System.out.println("Weights do not add up to 100");
					}
					else{
						System.out.println("Exams are worth " + examWeight + "% of final grade");
						System.out.println("Homeworks are worth " + hwWeight + "% of final grade");
						System.out.println("Quizzes are worth " + quizWeight + "% of final grade");
						System.out.println("Finals are worth " + finalWeight + "% of final grade");
						Connection connection = null;
						try{
							connection = ConnectionConfiguration.getConnection();
							if(connection != null){
								System.out.println("Connection Established.");
							}
							String selectQuery =
									"update gradeWeight set examWeight = ?, hwWeight = ?, quizWeight = ?, finalWeight = ?;";
						Statement st = connection.createStatement();
//						ResultSet rs = st.executeQuery(selectQuery);
						PreparedStatement preparedStmt = connection.prepareStatement(selectQuery);

//
//						int id = rs.getInt("s_id");
//							int examWeightInt = rs.getInt("examWeight");
//							int hwWeightInt = rs.getInt("examWeight");
//							int quizWeightInt = rs.getInt("examWeight");
//							int finalWeightInt = rs.getInt("examWeight");
							preparedStmt.setInt(1, (int) examWeight);
							preparedStmt.setInt(2, (int) hwWeight);
							preparedStmt.setInt(3, (int) quizWeight);
							preparedStmt.setInt(4, (int) finalWeight);
							int rowsUpdated = preparedStmt.executeUpdate();
							if (rowsUpdated > 0) {
								System.out.println("An existing user was updated successfully!");
							}
								preparedStmt.close();
//						rs.close();
//						st.close();
							connection.close();
						}
						catch(Exception e){
							e.printStackTrace();
						}
						finally {
							if(connection != null) {
								try{
									connection.close();
								}
								catch(SQLException e){
									e.printStackTrace();
								}
							}
						}
					}
					break;

				case 2:
					test.addExam();
					break;

				case 3:
					test.addHW();
					break;

				case 4:
					test.addQuiz();
					break;

				case 5:
					test.setFinal();
					break;

				case 6:
					test.editStudent();
					break;

				case 7:
					test.calcExamScore();
					test.calcHWScore();
					test.calcQuizScore();
					test.calcFinalGrade();
					break;

				case 9:
					sc.close();
					exit = true;
			}

		}
		while(!exit);
	}
}
