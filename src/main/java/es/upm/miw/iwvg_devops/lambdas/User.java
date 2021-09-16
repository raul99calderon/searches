package es.upm.miw.iwvg_devops.lambdas;

import java.util.List;

public class User {

    private String id;

    private String name;

    private String familyName;

    private List<Fraction> fractions;

    public User(String id, String name, String familyName, List<Fraction> fractions) {
        this.id = id;
        this.name = name;
        this.familyName = familyName;
        this.fractions = fractions;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public List<Fraction> getFractions() {
        return fractions;
    }

    public String getInitialFamilyName(){
        return this.familyName.substring(0,1);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                ", fractions=" + fractions +
                '}';
    }
}
