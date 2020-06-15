# Voting service API

## Rodando
Utilizei MySQL, então é preciso ter um banco MySQL com o schema **voting** e configurar as propriedades do projeto (profile de dev cria automaticamente o schema)

Executar no terminal
```bash
./mvnw spring-boot:run
```

#### Interpretação do problema
Interpretei como necessário apenas a criação de tópicos da assembleia;
onde seria criado um tópico (com ou sem o tempo final da sessão de votação)
Podendo ser iniciado a sessão de votação do tópico com o tempo final da sessão (ou 1 min sem passar o tempo)

Os votos recebem apenas o voto como corpo da requisição, a informação do usuário esta sendo passada no header;
numa versão *real* poderia ser utilizado JWT.

A contabilização pode ser obtido após o término da seseão de votação

#### Informações sobre o desenvolvimento
Separei as camadas do sistema basicamente em: Controller, Service e Repository; com algumas partes auxiliares;
como a Client, que seria a interação com outros serviços (poderia ser utilizado FeignClient num ambiente real)

Para a contabilização do resultado criei manualmente a pesquisa, retornando apenas as informações pertinentes.

Para facilitar o desenvolvimento, criei um arquivo com dados que são carregados ao iniciar o serviço (apenas no profile de dev)

#### Tarefas bônus
1 -
Utilizei para uma "integração externa" validando o usuário, uma chamada de um serviço chamado [JsonPlaceholder](https://jsonplaceholder.typicode.com/). 

4 - 
Para um versionamento, uma opção simples seria utilizar a URL, como utilizei nesse serviço.
