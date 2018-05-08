import javax.swing.plaf.nimbus.State;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gradebook {

	List<Student> students;

	private float examWeight;
	private float hwWeight;
	private float quizWeight;
	private float finalWeight;

	private float finalGrade;

	public Gradebook(){
		students = new ArrayList<Student>();
//		examWeight = 30;
//		hwWeight = 15;
//		quizWeight = 15;
//		finalWeight = 40;
		Connection connection = null;
		try{
			connection = ConnectionConfiguration.getConnection();
			if(connection != null){
				System.out.println("Connection Established.");
			}


			String selectQuery = "select * from gradeWeight;";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(selectQuery);
			while (rs.next())
			{
				int id = rs.getInt("s_id");
				int examWeightInt = rs.getInt("examWeight");
				int hwWeightInt = rs.getInt("examWeight");
				int quizWeightInt = rs.getInt("examWeight");
				int finalWeightInt = rs.getInt("examWeight");


				examWeight = (float) examWeightInt;
				hwWeight = (float) hwWeightInt;
				quizWeight = (float) quizWeightInt;
				finalWeight = (float) finalWeightInt;
			}

			rs.close();
			st.close();
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

	public void readStudents(String fileName) throws IOException{
		File studentInfo = new File("students.txt");
		FileReader fr = new FileReader(studentInfo);
		BufferedReader br = new BufferedReader(fr);
		String currentStudent;
		while((currentStudent = br.readLine()) != null){
			Scanner scanner = new Scanner(currentStudent);
			scanner.useDelimiter(" ");
			String last = scanner.next();
			String first = scanner.next();
			int ID = scanner.nextInt();
			int gradePercentage = scanner.nextInt();
			String gradeLetter = scanner.next();
			students.add(new Student(first, last, ID, gradePercentage, gradeLetter));
			scanner.close();
		}
		br.close();
	}

	public void readStudents() throws IOException{
//		readStudents("students.txt");
//		for(Student student: students){
//			System.out.println(student.getStudentInfo());
//		}

		Connection connection = null;
		try{
			connection = ConnectionConfiguration.getConnection();
			if(connection != null){
				System.out.println("Connection Established.");
			}


			String selectQuery = "select * from gradebook.studentGrade;";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(selectQuery);
			while (rs.next())
			{
				int id = rs.getInt("s_id");
				String firstName = rs.getString("fname");
				String lastName = rs.getString("lname");
				int percentage = rs.getInt("percentage");
				String grade = rs.getString("grade");
//                // print the results
				System.out.format("%s, %s, %s, %s, %s\n", id, firstName, lastName, percentage, grade);
				students.add(new Student(firstName, lastName,id, percentage, grade));
			}

			rs.close();
			st.close();
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
	public void readGrades() throws IOException {
		Connection connection = null;
		try{
			connection = ConnectionConfiguration.getConnection();
			if(connection != null){
				System.out.println("Connection Established.");
			}


			String selectQuery = "select * from gradebook.studentGrade;";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(selectQuery);
			while (rs.next())
			{
				int id = rs.getInt("s_id");
				String firstName = rs.getString("fname");
				String lastName = rs.getString("lname");
				int percentage = rs.getInt("percentage");
				String grade = rs.getString("grade");
//                // print the results
				System.out.format("%s, %s, %s, %s, %s\n", id, firstName, lastName, percentage, grade);
				students.add(new Student(firstName, lastName,id, percentage, grade));
			}

			rs.close();
			st.close();
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

	public void getStudents(){
		int i = 1;
		for(Student student: students){
			System.out.println(i + ". " + student.getStudentName());
			i++;
		}
	}

	public void setExamWeight(float weight){
		examWeight = weight;
	}

	public void setHomeworkWeight(float weight){
		hwWeight = weight;
	}

	public void setQuizWeight(float weight){
		quizWeight = weight;
	}

	public void setFinalWeight(float weight){
		finalWeight = weight;
	}

	public void addExam(){
		int n = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("What is the exam out of?");
		float examGrade = sc.nextFloat();
		for(Student student: students){
			System.out.println("Enter grade for " + student.getStudentName() + ":");
			float score = sc.nextFloat();
			student.addExam(score, examGrade);
			Connection connection = null;
			try{
				connection = ConnectionConfiguration.getConnection();
				if(connection != null){
					System.out.println("Connection Established.");
				}
				String selectQuery =
						"select * from examGrade;";
					String query = " update examGrade set exam1 = ? where s_id = ?;";
					Statement statement = connection.createStatement();
					PreparedStatement preparedStmt = connection.prepareStatement(query);
					preparedStmt.setInt(n+1, (int) score);
					preparedStmt.setInt(2, student.getStudentID());
					int rowsUpdated = preparedStmt.executeUpdate();
					if (rowsUpdated > 0) {
						System.out.println("An existing user was updated successfully!");
					}
					preparedStmt.close();
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
		//----------
	}

	public void addHW(){
		Scanner sc = new Scanner(System.in);
		System.out.println("What is the homwork out of?");
		float HWGrade = sc.nextFloat();
		for(Student student: students){
			System.out.println("Enter grade for " + student.getStudentName() + ":");
			float score = sc.nextFloat();
			student.addHW(score, HWGrade);
		}
	}

	public void addQuiz(){
		Scanner sc = new Scanner(System.in);
		System.out.println("What is the quiz out of?");
		float quizGrade = sc.nextFloat();
		for(Student student: students){
			System.out.println("Enter grade for " + student.getStudentName() + ":");
			float score = sc.nextFloat();
			student.addQuiz(score, quizGrade);
		}
	}

	public void setFinal(){
		int n = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("How much is the Final worth?");
		float finalGrade = sc.nextFloat();
		for(Student student: students){
			System.out.println("Enter grade for " + student.getStudentName() + ":");
			float score = sc.nextFloat();
			student.setFinal(score, finalGrade);
			student.setFinalGrade(score / finalGrade);


			Connection connection = null;
			try{
				connection = ConnectionConfiguration.getConnection();
				if(connection != null){
					System.out.println("Connection Established.");
				}
				String selectQuery =
						"select * from examGrade;";
				String query = " update finalGrade set final = ? where s_id = ?;";
				Statement statement = connection.createStatement();
				PreparedStatement preparedStmt = connection.prepareStatement(query);
				preparedStmt.setInt(n+1, (int) score);
				preparedStmt.setInt(2, student.getStudentID());
				int rowsUpdated = preparedStmt.executeUpdate();
				if (rowsUpdated > 0) {
					System.out.println("An existing user was updated successfully!");
				}
				preparedStmt.close();
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

	}

	public void editStudent(){
		Scanner sc = new Scanner(System.in);
		System.out.println("\n\n       Select a student");
		System.out.println("---------------------------");
		getStudents();
		Student student = students.get(sc.nextInt() - 1);
		System.out.println("Change " + student.getStudentName() + "'s:" );
		System.out.println("1. Exam");
		System.out.println("2. Homework");
		System.out.println("3. Quiz");
		System.out.println("4. Final");
		int choice = sc.nextInt();


		if(choice == 1){
			student.showExams();
			System.out.println("Enter exam, then new score:");
			int examNum = sc.nextInt();
			float score = sc.nextFloat();
			student.changeExamScore(examNum - 1, score);
			System.out.println(student.getStudentName() + "'s new score for Exam " + examNum + " is " + score);
		}


	}

	public void calcExamScore(){
		for(Student student: students){
			float maxExamScore = 0;
			float earnedExamScore = 0;
			float examGrade = 0;
			for(Exam exam : student.getExams()){
				maxExamScore = maxExamScore + exam.grade;
				earnedExamScore = earnedExamScore + exam.score;
			}
			examGrade = earnedExamScore / maxExamScore;
			student.setExamGrade(examGrade);
		}
	}

	public void calcHWScore(){
		for(Student student: students){
			float maxHWScore = 0;
			float earnedHWScore = 0;
			float hwGrade = 0;
			for(Homework homework : student.getHomeworks()){
				maxHWScore = maxHWScore + homework.grade;
				earnedHWScore = earnedHWScore + homework.score;
			}
			hwGrade = earnedHWScore / maxHWScore;
			student.setExamGrade(hwGrade);
		}
	}

	public void calcQuizScore(){
		for(Student student: students){
			float maxQuizScore = 0;
			float earnedQuizScore = 0;
			float quizGrade = 0;
			for(Quiz quiz : student.getQuizzes()){
				maxQuizScore = maxQuizScore + quiz.grade;
				earnedQuizScore = earnedQuizScore + quiz.score;
			}
			quizGrade = earnedQuizScore / maxQuizScore;
			student.setExamGrade(quizGrade);
		}
	}

	public void calcFinalGrade(){
		for(Student student: students){
			float examScore = 0;
			float hwScore = 0;
			float quizScore = 0;
			float finalScore = 0;
			float totalGrade = 0;
			examScore = student.getExamGrade() * examWeight;
			hwScore = student.getHWGrade() * hwWeight;
			quizScore = student.getQuizGrade() * quizWeight;
			finalScore = student.getFinalGrade() * finalWeight;
			totalGrade = examScore + hwScore + quizScore + finalScore;
			student.setFinalGrade(totalGrade);
			System.out.println(totalGrade);
			if(totalGrade >= 97){
				student.setGrade("A+");
			}
			else if(totalGrade >= 93){
				student.setGrade("A");
			}
			else if(totalGrade >= 90){
				student.setGrade("A-");
			}
			else if(totalGrade >= 87){
				student.setGrade("B+");
			}
			else if(totalGrade >= 83){
				student.setGrade("B");
			}
			else if(totalGrade >= 80){
				student.setGrade("B-");
			}
			else if(totalGrade >= 77){
				student.setGrade("C+");
			}
			else if(totalGrade >= 73){
				student.setGrade("C");
			}
			else if(totalGrade >= 70){
				student.setGrade("C-");
			}
			else if(totalGrade >= 67){
				student.setGrade("D+");
			}
			else if(totalGrade >= 63){
				student.setGrade("D");
			}
			else if(totalGrade >= 60){
				student.setGrade("D-");
			}
			else{
				student.setGrade("F");
			}
			System.out.println(student.getStudentName() + "'s grade for the class " + "is " + student.getGrade());
		}
	}

}
