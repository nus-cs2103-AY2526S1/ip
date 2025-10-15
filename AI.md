# AI Declaration
## Tools Used
The following AI tools were used in Visual Studio Code for code completion, generation and chat messages, starting in Week 6.
1. GitHub Copilot
2. GPT-4.1 Agent Mode

These tools were solely used to assist in writing JUnit tests, Javadoc comments and  the User Guide for the project.

## Interesting Observations
***Generation of JUnit Tests***

The AI tools were able to generate basic JUnit tests for the classes in the project. However, the generated tests required modifications and manual verification to ensure that they were meaningful and correctly tested the intended functionality. The AI needed very specific prompts on what to test. With only general prompts and limited context, it did not produce as useful of tests. Sometimes, the generated tests were buggy, which caused the build to fail. I needed to send reprompts to update the code, and even then, I would still need to manually edit it for it to work.


***Javadoc Comments***

The AI tools were more successful in generating Javadoc comments. They were able to provide accurate documentation for the classes and methods, although some manual editing was still needed to ensure the accuracy of the code, and that it adheres to the coding standards.