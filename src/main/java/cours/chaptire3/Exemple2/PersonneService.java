package cours.chaptire3.Exemple2;

public class PersonneService {

    public static String getTypeParAge(int age) {
        if (age > 0 && age < 12) return "enfant";
        else if (age >= 12 && age < 18) return "adolescent";
        else if (age >= 18 && age < 60) return "adulte";
        else return "personne du troisième âge";
    }
}
