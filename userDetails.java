public class userDetails {
    private String username;
    private String fullName;
    private int age;
    private String interests;
    private String hobbies;

    public userDetails(String fullName,String username, int age, String interests, String hobbies) {
        this.fullName = fullName;
        this.username = username;
        this.age = age;
        this.interests = interests;
        this.hobbies = hobbies;
    }
    public String getFullName() {
        return fullName;
    }
    public String getUsername() {
        return username;
    }
    public int getAge() {
        return age;
    }
    public String getInterests() {
        return interests;
    }

    public String getHobbies() {
        return hobbies;
    }
}
