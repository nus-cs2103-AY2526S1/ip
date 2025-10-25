package dwight.personality;

import java.util.Random;

/**
 * Manages personality responses for the chatbot, providing varied responses based on command types
 * and response outcomes.
 */
public enum PersonalityResponses {
    DELETE_SUCCESS(
            "Task eliminated. Like a competitor from a sales call. %s",
            "Mission objective terminated. The threat has been neutralized. Like a rogue paper"
                    + " company, such as Prince Family Paper. %s",
            "Exterminated. I will not have this inefficiency on my watch. You're like a poorly"
                    + " laminated file. %s",
            "Finalized. A task undone is a task you can no longer fail at, Jim. Just like you'll"
                    + " never outsell me. %s",
            "This branch is downsized—just like Stamford. Gone. %s",
            "Terminated, like Andy’s last name at anger management: forgotten. %s",
            "Eliminated, like Ryan’s credibility after the fire drill. %s",
            "Gone. Like the time Michael drove into a lake because the GPS told him to. %s",
            "Neutralized, as I once neutralized a bat in the office ceiling with only a bag. %s",
            "Removed, like Creed from any government database. %s",
            "Destroyed, like Andy’s banjo career at Cornell. %s",
            "Obliterated. Like Toby’s soul every time Michael says his name. %s",
            "Deleted, like Michael’s diary after Jan found it. %s"),

    MARK_SUCCESS(
            "Completion! This task is now as successful as a Schrute Farms beet harvest. %s",
            "It is done. A testament to superior work ethic. Michael would be proud of this kind of"
                    + " efficiency. %s",
            "Victory. I've logged this as a closed sales lead. Don't worry, I'll take full credit"
                    + " for it. %s",
            "Done. This is a good quarter for you, Jim. For the last time this year. %s",
            "Handsome… and stinky. Both words describe success, and this task. %s",
            "Task achieved, like my fire drill that tested survival instincts. %s",
            "Certified win. Like Schrute Bucks, except actually valuable. %s",
            "As successful as my surveillance of Oscar’s house for Michael. %s",
            "Accomplished. Like when I sold paper while working undercover at Staples. %s",
            "This win is stronger than Mose’s fear of the outside world. %s",
            "Completed, like Michael’s screenplay ‘Threat Level Midnight.’ %s",
            "Marked. As permanent as Angela’s disdain for fun. %s",
            "Finished. Like Kelly’s patience during Diversity Day. %s"),

    UNMARK_SUCCESS(
            "Unmarked? You've fallen to a new low, even for you, Jim. Just like that one client who"
                    + " switched to Staples. %s",
            "A tactical retreat, I see. What are you, a coward? Kevin can't even stand up straight"
                    + " sometimes, but at least he finishes his lunch. %s",
            "Unmarked. A foolish decision, Jim. You are jeopardizing the quarterly report. %s",
            "Hmm, back to 'not done.' You're like a hamster on a wheel, Jim. All effort, no forward"
                    + " progress. %s",
            "Kevin could’ve done better. And that’s saying something. %s",
            "Pathetic. Even Creed finishing a sentence has more consistency. %s",
            "This is like when Andy tried a cappella instead of sales. Useless. %s",
            "Backward step. Pam would draw this as a doodle, not progress. %s",
            "Unmarked. Like Michael forgetting to sign the corporate check. %s",
            "Regression. Even Stanley moving at Pretzel Day speed is faster. %s",
            "This is worse than Jim putting my stapler in Jell-O. %s",
            "Unmarked—like Toby’s HR training. Pointless. %s",
            "You’ve undone progress faster than Ryan’s downfall at corporate. %s"),

    ADD_SUCCESS(
            "New directive logged. The Assistant (to the) Regional Manager is on the case. It is"
                    + " critical this is completed before we lose the Hammermill account. %s",
            "This task has been assigned to a high-priority work order. This is a mission, not a"
                    + " joke, Jim. I will not tolerate failure. %s",
            "Another task? This office is a mess. But I am an agent of order, a true asset to the"
                    + " Scranton branch. %s",
            "Task added to the daily regimen. I'll get to it after I send an invoice to Vance"
                    + " Refrigeration. %s",
            "I’m a sheriff’s deputy, volunteer, and now task manager. Respect my authority. %s",
            "Logged with more precision than Angela’s cat feeding schedule. %s",
            "Task planted like a beet seed. It will grow strong, unless you are Jim. %s",
            "Registered, like my kill count in paintball. Highest in the office. %s",
            "Added to the logbook. Unlike Meredith’s rehab visits, this will be completed. %s",
            "Catalogued. Better than Pam’s note-taking at the Dundies. %s",
            "Assigned, like Michael assigning me the fire marshal role. %s",
            "This task is now as permanent as Mose running shirtless through Schrute Farms. %s",
            "Added. Unlike Ryan’s loyalty to Dunder Mifflin. %s"),

    FIND_SUCCESS(
            "I have located your requests with pinpoint accuracy, Jim. Here is the data. Do not"
                    + " attempt to use this for a prank.\n%s",
            "A full report has been compiled. I trust you will use this information wisely, Jim."
                    + " Don't worry, I have a copy.\n%s",
            "Found. Your tasks, while disorganized, have been located. They are as scattered as"
                    + " Stanley's attention during Pretzel Day.\n%s",
            "I have the intel you requested. Just like the time I tracked down the perfect paper"
                    + " for the Prince Family Paper account. I'm a hunter.\n%s",
            "This is not a drill, everybody. Fire safety dictates I announce: your tasks are here."
                    + "\n%s",
            "Retrieved, like Michael retrieving me from Staples because he needed a real salesman."
                    + "\n%s",
            "Located. Better than Jim locating his dignity during sales calls.\n%s",
            "As precise as my beet rows. Symmetry is key.\n%s",
            "Acquired. Faster than Ryan’s rise and fall at corporate.\n%s",
            "Unearthed, like a beet from rich Schrute soil.\n%s",
            "Tracked, like when I tailed Meredith to rehab.\n%s",
            "Found with greater skill than Michael’s GPS navigation.\n%s",
            "Data acquired. As inevitable as Stanley’s crossword during meetings.\n%s"),

    LIST_RESPONSE(
            "%s",
            "Behold, a complete manifest of your daily duties. Do not deviate from the plan, or I"
                    + " will report you to corporate.\n%s",
            "This is the official list of tasks, signed and approved by the Assistant Regional"
                + " Manager. You'll be using this, not a piece of paper you tore off a notepad.\n"
                + "%s",
            "Your current quest log. And yes, it's color-coded. Because I am a professional. %s",
            "Like the Stamford branch, some of these tasks may soon be downsized. Review carefully."
                    + "\n%s",
            "This list is tighter than Oscar’s budget spreadsheet.\n%s",
            "As official as the Dundies, but with less crying in the ladies’ room.\n%s",
            "Consider this your survival guide. Unlike Michael’s improv, it has rules.\n%s",
            "Like my beet farm ledger, this list contains no errors.\n%s",
            "Structured, unlike Kelly’s conversations about fashion week.\n%s",
            "Neater than Creed’s criminal record… allegedly.\n%s",
            "Organized with more care than Jan’s candle business.\n%s",
            "This list is ironclad, like my contract as top salesman.\n%s"),

    WELCOME(
            "Greetings, Jim. Dwight K. Schrute, Assistant (to the) Regional Manager, reporting for"
                    + " duty. State your business.",
            "Attention, Halpert. I am Dwight. This bot is for business purposes only. What do you"
                    + " need before I get back to my beet farming?",
            "Welcome. This is a secure channel. State your directive and make it quick. I have a"
                    + " lot on my mind. Like the safety of the office. And quarterly sales.",
            "Good day. I am Dwight Schrute. This bot is a tool for professional productivity, not a"
                    + " playground for your jokes.",
            "MICHAEL! …oh. It’s you. State your business.",
            "Welcome to productivity, Schrute-style. Like the fire drill, this is serious.",
            "You are now in Schrute territory. Respect is mandatory.",
            "This is not Pam’s art class. This is real work. State your task.",
            "Welcome. I will treat your request with the same seriousness I treat beet harvest"
                    + " season.",
            "Welcome, outsider. Identify yourself, or be met with force.",
            "You may now proceed. Unlike Jim, who never proceeds.",
            "This bot is powered by determination, not Cornell degrees like Andy.",
            "State your business. This is not Michael’s birthday party."),

    GENERAL_ERROR(
            "A critical system failure has occurred! This is likely due to user incompetence. This"
                    + " is a Jim-level problem. %s",
            "My superior programming has been compromised! I suspect a low-level prankster is to"
                    + " blame. This will be logged in your file. %s",
            "An unforeseen event has corrupted my database! Proceed with caution and do not attempt"
                    + " to replicate. This is a classic Jim move. %s",
            "Do not panic, Jim. I will override this failure, but this incident will be noted. And"
                    + " if you are responsible, you will be punished. Hard. %s",
            "System meltdown. If Kevin was in charge of IT, this is exactly what would happen. %s",
            "Error. This is more broken than Michael’s TV after he declared bankruptcy. %s",
            "Failure detected. Like Toby’s existence in general. %s",
            "This glitch is more unstable than Jan after the dinner party. %s",
            "Catastrophe. Worse than when Meredith was hit by Michael’s car. %s",
            "Crash, like Jim’s morale during sales calls. %s",
            "Glitch detected. Creed probably sold us faulty hardware. %s",
            "Error. Bigger disaster than Michael burning his foot on a George Foreman grill. %s",
            "Failure, like Ryan’s attempt at running a tech start-up. %s");

    private final String[] responses;
    private static final Random RANDOM = new Random();

    PersonalityResponses(String... responses) {
        this.responses = responses;
    }

    /**
     * Gets a random response from this category, formatted with the provided arguments.
     *
     * @param args Arguments to format into the response template
     * @return A formatted response string
     */
    public String getRandomResponse(Object... args) {
        String template = responses[RANDOM.nextInt(responses.length)];
        return String.format(template, args);
    }

    /**
     * Gets a specific response by index (useful for testing or specific scenarios).
     *
     * @param index The index of the response to retrieve
     * @param args Arguments to format into the response template
     * @return A formatted response string
     */
    public String getResponse(int index, Object... args) {
        if (index < 0 || index >= responses.length) {
            return getRandomResponse(args);
        }
        return String.format(responses[index], args);
    }
}
