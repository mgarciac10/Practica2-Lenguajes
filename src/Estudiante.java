public class Estudiante {
    private String id;
    private String first_name;
    private String last__name;
    private String gender;
    private String career_aspiration;
    private int math_score;

    public Estudiante(String id, String first_name, String last__name, String gender, String career_aspiration, int math_score) {
        this.id = id;
        this.first_name = first_name;
        this.last__name = last__name;
        this.gender = gender;
        this.career_aspiration = career_aspiration;
        this.math_score = math_score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast__name() {
        return last__name;
    }

    public void setLast__name(String last__name) {
        this.last__name = last__name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCareer_aspiration() {
        return career_aspiration;
    }

    public void setCareer_aspiration(String career_aspiration) {
        this.career_aspiration = career_aspiration;
    }

    public int getMath_score() {
        return math_score;
    }

    public void setMath_score(int math_score) {
        this.math_score = math_score;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id='" + id + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last__name='" + last__name + '\'' +
                ", gender='" + gender + '\'' +
                ", career_aspiration='" + career_aspiration + '\'' +
                ", math_score=" + math_score +
                '}';
    }
}
