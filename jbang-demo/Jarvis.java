/// usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS dev.langchain4j:langchain4j:1.10.0
//DEPS dev.langchain4j:langchain4j-open-ai:1.10.0
//DEPS ch.qos.logback:logback-classic:1.5.6
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

public class Jarvis {

  private static final Logger _LOG = LoggerFactory.getLogger(Jarvis.class);

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

    _LOG.info("💬: Bonjour JARVIS. Explique en quelques lignes ce qu'est JBang à des développeuses et développeurs Java. Merci.\n");
    TokenStream tokenStream = assistant
        .chat("Bonjour JARVIS. Explique en quelques lignes ce qu'est JBang à des développeuses et développeurs Java. Merci.");
    CompletableFuture<ChatResponse> futureChatResponse = new CompletableFuture<>();
    _LOG.info("🤖: ");
    tokenStream
        .onCompleteResponse(futureChatResponse::complete)
        .onPartialResponse(_LOG::info)
        .onError(Throwable::printStackTrace)
        .start();
    futureChatResponse.join();
  }
}