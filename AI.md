# AI and its role in my IP
To pretend as if AI did not play a role in this project would be massively
disingenuous. I will say that almost all of the code you see in this project
is handwritten. I never requested AI to generate code for me, in fact I usually
don't even mention what language I am using, so when the AI gives me code it is
usually in Python. But There are some key areas in which AI definitely helped me
that I want to mention here.

## Documentation
As mentioned in my README.md file, I was very new to documentation at the start of
the semester. I did not understand how to write good documentation. As such, I
consulted generative AI tools for help. I would write my own documntation for my
code, then copy paste the entire file into DeepSeek or Chatgpt to get its thoughts
and opinions. Most of this happened around the time of the first increment that 
required JavaDocs.

Overtime I learned from the AI on how to write documentation myself. It got to the
point that I'm proud to say I did not rely on AI for most of the documentation you 
see today. However, the inspiration from AI definitely seeps through and stil
persists in my work. Particularly, files created from a while back feature
documentation that is heavily inspired by comments from the AI. Most of these have
been labelled with something along the lines of:

`This documentation was produced under the guidance of AI`

within the class level JavaDocs. But just for transparency, I will list some of the
files here which I think Ai played a major role in aiding me with writing documentation.

- Chatbot
- Task
- TaskList
- Month
- CommandType
- Storage
- ApplicationContext

Now I have mentioned that for MOST of the code in the IP is handwritten. However, there
some pieces of Code that I got help from the AI with.

## JavaFX & FXML
I could not wrap my head around FXML. It just seemed overwhelming to me because there
were so many things to consider. I ended up using the JavaFX tutorial as a base for the
GUI version of this program, which I have already mentioned in the Acknowledgements of
the README.md. However, there was another source, which is generative AI. Now I tried
best to limit my use of gen AI, in fact it was SceneBUilder that lead to a lot of the
main structure of the code you see in the FXML files. However, there were certain details
that I did not understand and had to consult the AI for. I think one of the most significant
uses of AI was figuiring out how to make the Profile Pictures circular. Other points
would be things like making it so that the DialogueBoxes accomodate for the ScollBar 
of the ScrollPane. Basically, here are the files where I had help from AI to figure
out how to work with Javafc and FXML:

- DialogueBox (circular profile picture)
- DialogueBox (Conditional loading of profile picture, I attempted but quite couldn't 
figure it out)
- MainWindow (accommodating for ScrollPane)

Apart from that I am proud to say that the rest of the code is not only hand written,
but without referencing anyone elses code. However, there were some ideas that came
about from dicussions with AI that I think are worth mentioning.

## Command Parsers and Factories
1. CommandTypes

One of the most pressing issues I immediately noticed was that adding all the commands
in 1 class seemed like poor coding practise. This is something I discussed with the AI, 
I talked about how I could use and enum instead, and the AI brought in the idea of using
java Varargs to allow for aliases. I liked this idea, so I implemented it. I soon realise
that this was not a very good use of varargs, and that's why in the final code I used Sets
instead of varargs

2. Command Parsers and Factories

I had a lot of discussion with the AI on how to potentially allow forsome sort of Dialogue
Tree to be implemented. The AI was not helpful, but it did bring up the idea of CommandFactories.
I later realised that this was a pretty common strategy for coding, so I implemented that in
my code. I liked this idea so much that I reused this idea for the Time Parsers, and also
eventually to create a separate Parser class and Factory class. These ideas were inspired by
my discussion with AI, so I thought it was worth while mentioning. I will say though, I
personally came up with the idea of the Command Pipeline, the AI could never figure out the
solution to the problem I was proposing, but one day this idea clicked for me, and I'm really
proud of that. I do wonder if this is a common idea that other people came up with too? But the
point is, that part was not AI, just the concept of using Factories and Parsers


## With that...
I think that's it for the role AI played in this IP. I really tried my best to limit the use
of Generative AI as much as possible, as a result I do think the codebase looks a bit janky
and awkward. I do look forward to seeing feedback on it. All in all, I think that I managed 
to benefit from the use of AI within project, more so as a idea board, where I would bounce
ideas off of the AI to see if it thinks it makes sense. And by doing so, I personally believe
that I learned a lot. But this concludes the AI.md. Sorry for the giant wall of text.
