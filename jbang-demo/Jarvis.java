///usr/bin/env jbang "$0" "$@" ; exit $? 

//DEPS dev.langchain4j:langchain4j:1.0.0-beta1 dev.langchain4j:langchain4j-mistral-ai:1.0.0-beta1 ch.qos.logback:logback-classic:1.5.6

//FILES resources/logback.xml

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

        // java-02-mem-interface
        interface Assistant {
                @SystemMessage("Tu es JARVIS, un assistant virtuel expert dans le développement Java.")
                TokenStream chat(String message);
        }

        public static void main(String[] args) {
                // java-03-mem-model
                MistralAiStreamingChatModel streamingChatModel = MistralAiStreamingChatModel.builder()
                                .apiKey(System.getenv("OVH_AI_ENDPOINTS_ACCESS_TOKEN"))
                                .modelName("Mistral-7B-Instruct-v0.2")
                                .baseUrl(
                                                "https://mistral-7b-instruct-v02.endpoints.kepler.ai.cloud.ovh.net/api/openai_compat/v1")
                                .maxTokens(512)
                                .build();

                // java-04-mem-memory
                ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);

                // java-05-mem-assistant
                Assistant assistant = AiServices.builder(Assistant.class)
                                .streamingChatLanguageModel(streamingChatModel)
                                .chatMemory(chatMemory)
                                .build();

                // java-06-mem-prompt
                _LOG.info("💬: Bonjour JARVIS. Explique en quelques lignes ce qu'est JBang à des développeuses et développeurs Java. Merci.\n");
                TokenStream tokenStream = assistant
                                .chat("Bonjour JARVIS. Explique en quelques lignes ce qu'est JBang à des développeuses et développeurs Java. Merci.");
                _LOG.info("🤖: ");
                tokenStream
                                .onPartialResponse(_LOG::info)
                                .onError(Throwable::printStackTrace).start();
        }
}