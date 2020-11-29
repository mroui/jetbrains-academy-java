package search.phonebook;

public class Person {

    private final String firstname;
    private final String lastname;
    private final String email;

    public Person(String firstname, String lastname, String email) {
        this.firstname = firstname == null ? "" : firstname;
        this.lastname = lastname == null ? "" : lastname;
        this.email = email == null ? "" : email;
    }

    @Override
    public String toString() {
        String name = firstname;
        if (!lastname.isEmpty())
            name += " " + lastname;
        if (!email.isEmpty())
            name += " " + email;
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Person p = (Person) obj;
        return firstname.toLowerCase().equals(p.firstname.toLowerCase())
                && lastname.toLowerCase().equals(p.lastname.toLowerCase())
                && email.toLowerCase().equals(p.email.toLowerCase());
    }

    public boolean hasInCommon(String data) {
        for (String datum : data.split("\\s"))
            if (firstname.toLowerCase().equals(datum.toLowerCase())
                    || lastname.toLowerCase().equals(datum.toLowerCase())
                    || email.toLowerCase().equals(datum.toLowerCase()))
                return true;
        return false;
    }

    public boolean is(String data) {
        for (String datum : data.split("\\s"))
            if (!hasInCommon(datum))
                return false;
        return true;
    }
}
