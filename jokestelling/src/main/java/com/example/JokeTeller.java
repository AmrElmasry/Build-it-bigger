package com.example;

public class JokeTeller {

    private static JokeTeller jokeTeller;

    public static JokeTeller getInstance() {
        if (jokeTeller == null) {
            jokeTeller = new JokeTeller();
        }
        return jokeTeller;
    }

    public String tellJoke() {
        return "Cats are cool because you don't have to buy them. You see them on the street, take them home -- they're yours. You ain't never seen a cat being bought out of a pet store. They just sit in the pet store. They're under there like, 'Meow,' and you be looking at them like, 'Oh they're so cute. Let's go find one like that.'";
    }
}
