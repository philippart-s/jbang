///usr/bin/env jbang "$0" "$@" ; exit $? 
// 07-add-jarvis-deps

// 08-add-external-resources

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Jarvis {

    private static final Logger _LOG = LoggerFactory.getLogger(Jarvis.class);

        // 09-ai-services-mode
        interface Assistant {
                @SystemMessage("You are JARVIS, a virtual assistant expert in Java development.")
                TokenStream chat(String message);
        }
        
        public static void main(String[] args) {
                // 10-mistral-model
          StreamingChatModel streamingChatModel = OpenAiStreamingChatModel.builder()
                                .apiKey(System.getenv("OVH_AI_ENDPOINTS_ACCESS_TOKEN"))
                                .modelName(System.getenv("OVH_AI_ENDPOINTS_MODEL_NAME"))
                                .baseUrl(
                                        System.getenv("OVH_AI_ENDPOINTS_MODEL_URL"))
                                .maxTokens(512)
                  .build();
                
                // 11-add-memory
                ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
                
                // 12-create-assistant
                Assistant assistant = AiServices.builder(Assistant.class)
                                .streamingChatModel(streamingChatModel)
                                .chatMemory(chatMemory)
                .build();
                
                // 13-prompt
                _LOG.info("💬: Hello JARVIS. Explain in a few lines what JBang is to Java developers. Thank you.\n");
                TokenStream tokenStream = assistant
                                .chat("Hello JARVIS. Explain in a few lines what JBang is to Java developers. Thank you.");
                CompletableFuture<ChatResponse> futureChatResponse = new CompletableFuture<>();
                _LOG.info("🤖: ");
                tokenStream
                        .onCompleteResponse((ChatResponse response) -> futureChatResponse.complete(response))
                        .onPartialResponse(_LOG::info)
                        .onError(Throwable::printStackTrace).start();
                futureChatResponse.join();
                
        }
}