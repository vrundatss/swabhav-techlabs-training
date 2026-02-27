package FoodOrderingSystem.model.User;

public abstract class AbstractUser implements User {
    protected String id;
    protected String name;
    protected String username;
    protected String password;
    protected String email;

    protected AbstractUser(Builder<?> builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.username = builder.username;
        this.password = builder.password;
    }

    public static abstract class Builder<T extends Builder<T>> {
        private String id;
        private String name;
        private String username;
        private String password;
        private String email;

        public T setId(String id) { this.id = id; return self(); }
        public T setName(String name) { this.name = name; return self(); }
        public T setUsername(String username) { this.username = username; return self(); }
        public T setPassword(String password) { this.password = password; return self(); }
        public T setEmail(String email) { this.email = email; return self(); }


        public T id(String id) { return setId(id); }
        public T name(String name) { return setName(name); }
        public T username(String username) { return setUsername(username); }
        public T password(String password) { return setPassword(password); }
        public T email(String password) { return setEmail(email); }

        public abstract T self();
        public abstract AbstractUser build();
    }

    public String getUsername() { return username; }
    public String getId() { return id; }
    @Override public String getPassword() { return password; }

    public String getName() {
        return name;
    }
}