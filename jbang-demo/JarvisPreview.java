///usr/bin/env jbang "$0" "$@" ; exit $? 
//JAVA 21+
//PREVIEW
// 07-add-jarvis-deps
//DEPS dev.langchain4j:langchain4j:1.0.0-beta1 dev.langchain4j:langchain4j-mistral-ai:1.0.0-beta1 ch.qos.logback:logback-classic:1.5.6
// 08-add-external-resources
//FILES resources/logback.xml

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.mistralai.MistralAiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.TokenStream;

// 09-ai-services-mode
interface Assistant {
        @SystemMessage("Tu es JARVIS, un assistant virtuel expert dans le développement Java.")
        TokenStream chat(String message);
}

void main() {
    // 10-mistral-model
    MistralAiStreamingChatModel streamingChatModel = MistralAiStreamingChatModel.builder()
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
            .streamingChatLanguageModel(streamingChatModel)
            .chatMemory(chatMemory)
            .build();

    // 13-prompt
    System.out.println(
            "💬: Bonjour JARVIS. Explique en quelques lignes ce qu'est JBang à des développeuses et développeurs Java. Merci.\n");
    TokenStream tokenStream = assistant
            .chat("Bonjour JARVIS. Explique en quelques lignes ce qu'est JBang à des développeuses et développeurs Java. Merci.");
    System.out.println("🤖: ");
    tokenStream
            .onPartialResponse(System.out::print)
            .onError(Throwable::printStackTrace).start();
}