///usr/bin/env jbang "$0" "$@" ; exit $? 
// 24-enable-java-preview

//DEPS dev.langchain4j:langchain4j:1.10.0 dev.langchain4j:langchain4j-open-ai:1.10.0 ch.qos.logback:logback-classic:1.5.6
//FILES resources/logback.xml

import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.TokenStream;

import java.util.concurrent.CompletableFuture;

// JEP 445 / 463
public class JarvisPreview {

        private static final Logger _LOG = LoggerFactory.getLogger(JarvisPreview.class);

        interface Assistant {
                @SystemMessage("Tu es JARVIS, un assistant virtuel expert dans le développement Java.")
                TokenStream chat(String message);
        }

        public static void main(String[] args) {
                StreamingChatModel streamingChatModel = OpenAiStreamingChatModel.builder()
                                .apiKey(System.getenv("OVH_AI_ENDPOINTS_ACCESS_TOKEN"))
                                .modelName(System.getenv("OVH_AI_ENDPOINTS_MODEL_NAME"))
                                .baseUrl(
                                        System.getenv("OVH_AI_ENDPOINTS_MODEL_URL"))
                                .maxTokens(512)
                                .build();

                ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);

                Assistant assistant = AiServices.builder(Assistant.class)
                                .streamingChatModel(streamingChatModel)
                                .chatMemory(chatMemory)
                                .build();

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