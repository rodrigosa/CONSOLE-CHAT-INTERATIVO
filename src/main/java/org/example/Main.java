package org.example;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String keyGroqOllama3 = requiredEnvironmentVariable("GROQ_API_KEY");

        // Configurando para usar o Llama via Groq Cloud
        ChatModel llamaModel = OpenAiChatModel.builder()
                .baseUrl("https://api.groq.com/openai/v1") // Redireciona para o servidor da Groq
                .apiKey(keyGroqOllama3)             // Crie gratuitamente no console da Groq
                .modelName("openai/gpt-oss-20b")// Nome do modelo Llama escolhido
                .build();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("🦙 Olá! Em que posso ajudar?. Para sair, digite Q e pressione Enter.");

            while (true) {
                System.out.print("> ");

                if (!scanner.hasNextLine()) {
                    break;
                }

                String prompt = scanner.nextLine().trim();

                if (prompt.equalsIgnoreCase("Q")) {
                    System.out.println("Fim!");
                    break;
                }

                if (prompt.isEmpty()) {
                    continue;
                }

                String resposta = llamaModel.chat(prompt);
                System.out.println("🦙 Llama (via Groq): " + resposta);
            }
        }
    }

    private static String requiredEnvironmentVariable(String name) {
        String value = System.getenv(name);

        if (value == null || value.isBlank()) {
            throw new IllegalStateException(
                    "A variável de ambiente " + name + " não foi configurada."
            );
        }

        return value;
    }
}