# AI Usage Documentation

## Overview

This document records my use of AI assistance during the development of the Lyra
task management application. Throughout the Individual Project (iP), I leveraged
AI tools to enhance productivity and code quality while maintaining a strong
understanding of the underlying implementation.

## AI Tools Used

**Primary Tool:** Claude (Anthropic's AI Assistant) via Cursor IDE

- **Version:** Claude 4 Sonnet
- **Integration:** Direct integration within Cursor IDE for seamless code
  assistance

## Development Approach

Rather than fully relying on AI to write code, I adopted an **AI-assisted coding
approach** where:

1. **I designed the overall architecture** and made key design decisions
2. **AI helped implement** boilerplate code, repetitive patterns, and complex
   logic
3. **I reviewed and refined** all AI-generated code to ensure quality and
   understanding
4. **I wrote tests manually** to validate functionality and deepen my
   understanding

## Incremental Development with AI Assistance

### Level 1-3: Basic Task Management (Weeks 1-2)

- **Manual Work:** Designed the basic Task, Todo, Deadline, and Event class
  hierarchy
- **AI Assistance:** Generated boilerplate constructors, getters, and toString
  methods
- **Learning:** Understanding OOP inheritance and polymorphism patterns
- **Time Saved:** ~30% on repetitive code, allowing more focus on design
  decisions

### Level 4-6: Command Pattern Implementation (Week 3)

- **Manual Work:** Designed the Command pattern architecture and interfaces
- **AI Assistance:** Generated individual command classes (AddTodoCommand,
  MarkCommand, etc.)
- **Observation:** AI excelled at following established patterns once I provided
  examples
- **Time Saved:** ~40% on command implementations, more time for testing edge
  cases

### Level 7-8: Error Handling and Storage (Week 4)

- **Manual Work:** Designed exception hierarchy and storage file format
- **AI Assistance:** Implemented file I/O operations and error handling logic
- **Challenge:** Had to guide AI on specific error message formats and file
  parsing logic
- **Time Saved:** ~25% - Complex logic required more manual intervention

### Level 9-10: Advanced Features (Week 5-6)

- **Features Implemented:** Find, Sort, Help commands with comprehensive
  functionality
- **AI Assistance:** Generated search algorithms and sorting comparators
- **Manual Work:** Designed user experience and command interfaces
- **Learning:** Advanced Java features like Comparators and Stream API
- **Time Saved:** ~35% on algorithm implementation

### A-Level Features: GUI Implementation (Week 7-8)

- **Manual Work:** Designed overall GUI architecture and user experience
- **AI Assistance:** Generated JavaFX layouts, CSS styling, and event handling
- **Major Challenge:** Cross-platform JavaFX distribution - AI helped solve
  complex Gradle configuration
- **Innovation:** Modern UI design with custom DialogBox styling and responsive
  layout
- **Time Saved:** ~50% on GUI implementation - AI particularly strong with UI
  code

### Testing and Quality Assurance (Throughout)

- **Manual Work:** Wrote all JUnit test cases to ensure understanding
- **AI Assistance:** Generated test data and edge case scenarios
- **Coverage:** Achieved comprehensive test coverage for Task, TaskList, and
  utility classes
- **Time Saved:** ~20% on test setup, but manual verification was crucial

## Key Observations

### What AI Excelled At:

1. **Boilerplate Code Generation:** Constructors, getters, setters, and basic
   methods
2. **Pattern Following:** Once shown an example, AI consistently applied
   patterns
3. **JavaFX/CSS Styling:** Excellent at generating modern, responsive UI code
4. **Complex Build Configuration:** Solved challenging Gradle JavaFX
   distribution issues
5. **Error Message Consistency:** Generated user-friendly error messages
   following established tone

### What Required Manual Intervention:

1. **Architecture Decisions:** Overall design patterns and class relationships
2. **Business Logic:** Task management rules and validation logic
3. **User Experience Design:** Command syntax and interaction flow
4. **Performance Optimization:** Efficient algorithms and data structures
5. **Integration Testing:** Ensuring components work together correctly

### Productivity Impact:

- **Overall Time Saved:** Approximately 35-40% compared to manual coding
- **Quality Improvement:** AI helped catch edge cases and suggest better error
  handling
- **Learning Enhancement:** More time available for understanding design
  patterns and testing
- **Code Consistency:** AI ensured consistent coding style and naming
  conventions

## Challenges and Limitations

1. **Context Understanding:** AI sometimes missed broader application context
   when implementing specific methods
2. **Over-Engineering:** Tendency to generate overly complex solutions for
   simple problems
3. **Testing Gaps:** AI-generated code sometimes missed important edge cases
4. **Documentation:** Had to manually ensure all Javadoc comments were accurate
   and helpful

## Best Practices Developed

1. **Start with Architecture:** Always design the overall structure manually
   before using AI
2. **Provide Clear Context:** Give AI detailed requirements and examples for
   better results
3. **Review Everything:** Never commit AI-generated code without thorough review
4. **Test Manually:** Write your own tests to ensure deep understanding
5. **Iterate and Refine:** Use AI as a starting point, then improve and
   customize

## Conclusion

AI-assisted development significantly enhanced my productivity while allowing me
to focus on higher-level design decisions and learning objectives. The key was
maintaining control over the architecture and ensuring I understood every piece
of code in the final implementation. This approach allowed me to deliver a more
polished application with advanced features like a modern GUI and comprehensive
error handling, while still achieving the core learning goals of the iP.

The experience taught me that AI is most valuable as a sophisticated code
generation tool rather than a replacement for software engineering thinking. The
combination of human design insight and AI implementation assistance created a
powerful development workflow that I plan to continue refining in future
projects.
