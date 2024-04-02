package eu.senla.naumovich;


import eu.senla.naumovich.controller.SomeController;

public class Main {
    public static void main(String[] args) throws Exception {
        Context context = Context.getInstance().initContext(Main.class);
        SomeController controller = context.getClass2Object(SomeController.class);
        System.out.println(controller.execute());
    }
}
