class User {
    private String firstName;
    private String lastName;

    public User() {
        this.firstName = "";
        this.lastName = "";
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName == null && lastName == null
                ? "Unknown"
                : firstName == null
                    ? lastName
                    : lastName == null
                        ? firstName
                        : firstName + " " + lastName;
    }
}
