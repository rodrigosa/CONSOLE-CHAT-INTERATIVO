# Configuração das variáveis de ambiente

Este projeto não armazena chaves de API no código-fonte nem em
`src/main/resources/application.properties`. A aplicação lê as credenciais
diretamente das variáveis de ambiente do sistema operacional.

## Variáveis necessárias

| Variável | Uso |
| --- | --- |
| `GROQ_API_KEY` | Chave da API da Groq |

## Configuração no Windows

Abra o PowerShell e execute os comandos abaixo, substituindo os valores pelas
suas chaves:

```powershell
[Environment]::SetEnvironmentVariable(
    "GROQ_API_KEY",
    "sua-chave-da-groq",
    "User"
)
```

O escopo `"User"` configura as variáveis apenas para o seu usuário do Windows.
Depois de executar os comandos, feche e reabra o IntelliJ IDEA ou o terminal
para que o novo ambiente seja carregado.

Também é possível configurar as variáveis no IntelliJ IDEA em:

**Run > Edit Configurations > Environment variables**

Adicione `GROQ_API_KEY` com o respectivo valor.

## Verificação sem exibir as chaves

Use os comandos abaixo para confirmar se as variáveis foram configuradas.
Eles exibem apenas `True` ou `False`, não os valores das credenciais:

```powershell
[Environment]::GetEnvironmentVariable("GROQ_API_KEY", "User") -ne $null
```

## Segurança

- Nunca substitua as chaves reais neste arquivo de documentação.
- Não faça commit de arquivos com credenciais.
- O arquivo `application.properties` está ignorado pelo Git para evitar que
  credenciais locais sejam adicionadas acidentalmente.
- As chaves que já foram expostas devem ser revogadas e recriadas nos
  respectivos provedores.
- Em CI/CD, use o gerenciador de secrets da plataforma, como GitHub Actions
  Secrets, em vez de colocar chaves diretamente no pipeline.
