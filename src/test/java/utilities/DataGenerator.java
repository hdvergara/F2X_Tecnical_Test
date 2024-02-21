package utilities;

import com.github.javafaker.Faker;

public class DataGenerator {

    /**
     * Method to generate name randomly
     * @return generated name
     */
    public static String randomName(){
        Faker faker = new Faker();
        return faker.name().firstName();
    }

    /**
     * Method to generate Job randomly
     * @return generated Job
     */
    public static String randomJob(){
        Faker faker = new Faker();
        return faker.job().position();
    }
}
