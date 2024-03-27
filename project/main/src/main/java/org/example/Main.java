package org.example;


import org.example.controller.SomeController;

public class Main {
    public static void main(String[] args) throws Exception {
        Context context = Context.getInstance().initContext(Main.class);
        SomeController controller = context.getBean(SomeController.class);
        System.out.println(controller.execute());
    }
}
