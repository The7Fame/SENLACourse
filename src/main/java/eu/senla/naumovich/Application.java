package eu.senla.naumovich;

import eu.senla.naumovich.configuration.ApplicationConfig;
import eu.senla.naumovich.controllers.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        PublisherController publisherController = context.getBean(PublisherController.class);
        publisherRequest(publisherController);
    }

    public static void publisherRequest(PublisherController publisherController) {
        String publisherFirst = """
                {
                    "id": 1,
                    "publisherName": "publisher1",
                    "address": {
                        "id": 1,
                        "city": "city1",
                        "street": "street1",
                        "index": 123456
                    }
                }
                """;
        String publisherSecond = """
                {
                    "id": 2,
                    "publisherName": "publisher2",
                    "address": {
                        "id": 2,
                        "city": "city2",
                        "street": "street2",
                        "index": 123456
                    }
                }
                """;
        String publisherToUpdate = """
                {
                    "id": 2,
                    "publisherName": "updatePublisherName",
                    "address": {
                        "id": 2,
                        "city": "city2",
                        "street": "street2",
                        "index": 123456
                    }
                }
                """;
        System.out.println("Insert 2 records");
        publisherController.create(publisherFirst);
        publisherController.create(publisherSecond);
        System.out.println("All publishers");
        System.out.println(publisherController.getAll());
        System.out.println("Get the first publisher");
        System.out.println(publisherController.getById(publisherFirst));
        System.out.println("Delete the first record");
        publisherController.delete(publisherFirst);
        System.out.println("Update publisherName in the second record");
        System.out.println(publisherController.update(publisherToUpdate));
        System.out.println("All records");
        System.out.println(publisherController.getAll());
    }
}
