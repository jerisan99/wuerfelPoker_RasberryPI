package post;

public class Person {
    private int id;
    private String name;

    Person (/*int id, String name*/) {
        // this.id = id;
        // this.name = name;
    }

    int getId() {
        return this.id;
    }

    String getName() {
        return this.name;
    }   

    void setId(int id) {
        this.id = id;
    }

    void setName(String name) {
        this.name = name;
    }
}
