package org.example;

public class Events {
    public static final Event[] ALL_EVENTS = {
        new Event(c -> true, c -> {
            Screen.setBackground(false,"Leo");
            Screen.sPrintln("Back just like clockwork");
            Screen.sPrintln("Blood pouring from their chest");
            Screen.sPrintln("Here to remind you of this mistake");
            Screen.sPrintln("You shed, I shed. The blood just never stops");
            Screen.sPrintln("Your eyes blur, and your focus returns to reality."+
                    "Your orange fur stands straight up like a wildfire swaying in the wind. It's only a matter of time before they return");

            Screen.sPrintln("This mattress is uncharacteristically soft."+
                    " Bet the nurses knew you were coming back. The pillows should be an ocean of tears but you can't cry. They won't let you");
            Screen.sPrintln("You could not even admit yourself");
            Screen.sPrintln("None of the lights are on but the moon provides just enough"+
                    " light to take in the checkerboard floor and plain walls. All of devoid of color");
            Screen.setBackground(false,"scars");
            Screen.sPrintln("You feel a pain coming from some scars on your neck. Your fur somehow manages to hide them."+
                    " The only thing they see is scratches and scuffs on your metal");
            Screen.sPrintln("A sudden buzzing cuts through the silence of the moment. An old TV in the room begins to glow."+
                    " It just keeps trying and trying to find a channel. But the world has given up on it");
            Screen.sPrintln("You failed and you know it");
            Screen.setBackground(false,"blinding CRT");
            Screen.sPrintln("You confide in the TV's warm glow. You don't mind its blinding light Your vision only reminds you what a freak you are");
            Screen.sPrintln("The whine of the TV grows louder. Your ears shoot down and your claws grip them so hard that you might just rip them off");
            Screen.sPrintln("I know how you can fix it all");
            Screen.sPrintln("Your senses play like an orchestra. It's a familiar tune. All too familiar");
            Screen.sPrintln("I don't know if they can even fix you. You know you are just wasting people's time");
            Screen.sPrintln("Orion","If only");
            return true;
        })
    };
}
