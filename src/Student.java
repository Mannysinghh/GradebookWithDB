import java.util.ArrayList;
import java.util.List;

public class Student {


	private List<Exam> exams;
	private List<Homework> homeworks;
	private List<Quiz> quizzes;
	private float examGrade;
	private float hwGrade;
	private float quizGrade;
	private float finalGrade;
	private String grade;
	private Final Final;
	private String firstName;
	private String lastName;
	private int percent;
	private String letter;
	private int studentID;




	public Student(String first, String last, int ID, int gradePercentage, String gradeLetter)
	{
		this.firstName = first;
		this.lastName = last;
		this.studentID = ID;
		this.letter = gradeLetter;
		this.percent = gradePercentage;
		exams = new ArrayList<Exam>();
		homeworks = new ArrayList<Homework>();
		quizzes = new ArrayList<Quiz>();
		examGrade = 1;
		hwGrade = 1;
		quizGrade = 1;
		finalGrade = 1;
	}

	public void addExam(float score, float grade)
	{
		Exam toAdd = new Exam(score, grade);
		exams.add(toAdd);
	}

	public void addHW(float score, float grade)
	{
		Homework toAdd = new Homework(score, grade);
		homeworks.add(toAdd);
	}
	
	public void addQuiz(float score, float grade)
	{
		Quiz toAdd = new Quiz(score, grade);
		quizzes.add(toAdd);
	}
	
	public void setFinal(float score, float grade)
	{
		Final = new Final(score, grade);
	}
	
	public void showExams(){
		int i = 1;
		for(Exam exam: exams){
			System.out.println(i + ". Exam " + i + ": " + exam.score + " out of " + exam.grade);
		}
	}
	
	public List<Exam> getExams(){
		return exams;
	}
	
	public List<Homework> getHomeworks(){
		return homeworks;
	}
	
	public List<Quiz> getQuizzes(){
		return quizzes;
	}

	public int getStudentID(){
		return studentID;
	}
	
	public void setExamGrade(float grade){
		examGrade = grade;
	}
	
	public float getExamGrade(){
		return examGrade;
	}
	
	public void setHWGrade(float grade){
		hwGrade = grade;
	}
	
	public float getHWGrade(){
		return hwGrade;
	}
	
	public void setQuizGrade(float grade){
		quizGrade = grade;
	}
	
	public float getQuizGrade(){
		return quizGrade;
	}	
	
	public void setFinalGrade(float grade){
		finalGrade = grade;
	}
	
	public float getFinalGrade(){
		return finalGrade;
	}	
	
	public void setGrade(String grade){
		this.grade = grade;
	}
	
	public String getGrade(){
		return grade;
	}
	
	public void changeExamScore(int examNum, float score){
		exams.get(examNum).setScore(score);
	}

	
	
	public String getStudentName(){
		return firstName + " "+ lastName;
	}

	public String getStudentInfo(){
		return firstName + ", "+ lastName + " 			[Student ID: "
				+ studentID + "], [% " +
				percent + "], [Grade: " + letter + "]";
	}



}
