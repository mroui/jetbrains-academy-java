import java.io.Serializable;

class User {
    final static long serialVersionUID = 1L;
    String name;
    transient String password;
}