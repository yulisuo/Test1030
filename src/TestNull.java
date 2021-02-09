import java.util.Objects;
import java.util.Random;

public class TestNull {

    public static void main(String[] args) {
        Dog d1 = new Dog("ddfaf");
        Dog d2 = new Random().nextBoolean() ? new Dog() : null;
        System.out.println("rrr:" + d1.equals(d2));
        System.out.println("rrr:" + d2.name);
    }
}

class Dog {
    String name;
     public Dog(String n) {
         this.name = n;
     }

     public Dog() {

     }

    @Override
    public boolean equals(Object o) {


        if (this == o) return true;
        if (!(o instanceof Dog)) return false;
        Dog dog = (Dog) o;
        return Objects.equals(name, dog.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
