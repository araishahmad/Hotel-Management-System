public class User {
    private String name;
    private String CNIC;
    private int age;

    public User(String name, String CNIC, int age){
        setName(name);
        setCNIC(CNIC);
        setAge(age);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCNIC() {
        return this.CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
