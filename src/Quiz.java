
public class Quiz {
	float score;
	float grade;

	public Quiz(float score, float grade){
		this.score = score;
		this.grade = grade;
	}
	
	public void setScore(float score){
		this.score = score;
	}
	
	public float calcScore(){
		return score / grade;
	}
	
}
