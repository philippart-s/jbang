///usr/bin/env jbang "$0" "$@" ; exit $? 
// 11-add-jarvis-deps

// 12-add-external-resources

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.mistralai.MistralAiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.TokenStream;

public class Jarvis {

        private static final Logger _LOG = LoggerFactory.getLogger(Jarvis.class);

        // 02-ai-services-mode
        interface Assistant {
                @SystemMessage("Tu es JARVIS, un assistant virtuel expert dans le développement Java.")
                TokenStream chat(String message);
        }

        public static void main(String[] args) {
                // 03-mistral-model
                MistralAiStreamingChatModel streamingChatModel = MistralAiStreamingChatModel.builder()
                                .apiKey(System.getenv("OVH_AI_ENDPOINTS_ACCESS_TOKEN"))
                                .modelName(System.getenv("OVH_AI_ENDPOINTS_MODEL_NAME"))
                                .baseUrl(
                                        System.getenv("OVH_AI_ENDPOINTS_MODEL_URL"))
                                .maxTokens(512)
                                .build();

                // 04-add-memory
                ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);

                // 05-create-assistant
                Assistant assistant = AiServices.builder(Assistant.class)
                                .streamingChatLanguageModel(streamingChatModel)
                                .chatMemory(chatMemory)
                                .build();

                // 06-prompt
                _LOG.info("💬: Bonjour JARVIS. Explique en quelques lignes ce qu'est JBang à des développeuses et développeurs Java. Merci.\n");
                TokenStream tokenStream = assistant
                                .chat("Bonjour JARVIS. Explique en quelques lignes ce qu'est JBang à des développeuses et développeurs Java. Merci.");
                _LOG.info("🤖: ");
                tokenStream
                                .onPartialResponse(_LOG::info)
                                .onError(Throwable::printStackTrace).start();
        }
}