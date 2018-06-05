package annotations.database;

@DBTable(name = "MEMBER")
public class Member {
    @SQLString(30)
    private String firstName;
    @SQLString(50)
    private String lastName;
    @SQLInteger
    private int age;
    @SQLString(value = 30, constrains = @Constraints(primaryKey = true))
    private String handle;

    static int memberCount;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getHandle() {
        return handle;
    }

    @Override
    public String toString() {
        return "handle";
    }
}
