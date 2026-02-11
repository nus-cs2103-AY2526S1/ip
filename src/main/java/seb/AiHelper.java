package seb;

import java.util.List;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;

class AiHelper {
    private final ChatModel model;
    
    public AiHelper() {
        this.model = OpenAiChatModel.builder()
                .apiKey(System.getenv("LLM_API_KEY"))
                .baseUrl("https://api.groq.com/openai/v1")
                .modelName("llama-3.3-70b-versatile") // or another Groq model
                .build();
    }
    
    public ChatResponse getAiResponse(String systemPrompt, String userPrompt) {
        ChatRequest req = ChatRequest.builder()
                .messages(List.of(
                        SystemMessage.from(systemPrompt),
                        UserMessage.from(userPrompt)
                ))
                .build();
        
        ChatResponse res = model.chat(req);
        return res;
    }
    
    public static void main(String[] args) {
        AiHelper agent = new AiHelper();
        String reply = agent.getAiResponse("You are a helpful assistant for a CLI app", "How do I add a task with a deadline?").toString();
        System.out.println(reply);
    }
}

