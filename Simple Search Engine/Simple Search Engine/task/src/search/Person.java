package search;

public class Person {

    private final String firstname;
    private final String lastname;
    private final String email;

    public Person(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return firstname + ' ' + lastname + ' ' + email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Person p = (Person) obj;
        if (firstname == null)
            return p.firstname == null;
        if (lastname == null)
            return p.lastname == null;
        if (email == null)
            return p.email == null;
        return firstname.toLowerCase().equals(p.firstname.toLowerCase())
                && lastname.toLowerCase().equals(p.lastname.toLowerCase())
                && email.toLowerCase().equals(p.email.toLowerCase());
    }

    public boolean hasInCommon(String data) {
        return firstname.toLowerCase().contains(data.toLowerCase())
                || lastname.toLowerCase().contains(data.toLowerCase())
                || email.toLowerCase().contains(data.toLowerCase());
    }
}
