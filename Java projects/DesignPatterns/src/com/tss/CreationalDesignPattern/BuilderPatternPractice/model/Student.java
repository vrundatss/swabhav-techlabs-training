package com.tss.CreationalDesignPattern.BuilderPatternPractice.model;

public class Student {
    private final Integer id;
    private final String name;
    private final Integer age;
    private final String email;

    private Student(StudentBuilder studentBuilder){
        this.id = studentBuilder.id;
        this.name = studentBuilder.name;
        this.age = studentBuilder.age;
        this.email = studentBuilder.email;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return
                "Id=" + id +
                ", Name='" + name + '\'' +
                ", Age=" + age +
                ", Email='" + email + '\'' ;
    }

    public static class StudentBuilder {
        private Integer id;
        private String name;
        private Integer age;
        private String email;

        public StudentBuilder(Integer id , String name){
            this.id = id;
            this.name = name;
        }

        public StudentBuilder setAge(Integer age) {
            this.age = age;
            return this;
        }

        public StudentBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Student build(){
            return new Student(this);
        }
    }

}
