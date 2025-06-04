## Objetivo

O objetivo para esse roteiro era desenvolver o microsserviço EXCHANGE. Nele o usuário poderia pegar o valor de sell e de buy da conversão de uma moeda para outra.

## Montagem do Roteiro


### Tarefa 1

Escolher api pra conexão.

A primeira coisa a se fazer foi escolher a api para fazer a conversão e ver a documentação da api para descobrir se ela enviava os valores necessários (sell e buy)

A Api escolhida foi a [AwesomeAPI](https://docs.awesomeapi.com.br/api-de-moedas).

### Tarefa 2

Fazer a conexão com a api e a rota do exchange em FASTAPI 

=== "Main.py do Exchange"

    ``` { .py .copy .select linenums='1' title="main.py" }
    --8<-- "https://raw.githubusercontent.com/laupontiroli/exchange-service/main/app/main.py"
    ```


### Tarefa 3

Fazer o dockerfile para encaixar o programa dentro do serviço desse jeito:

```mermaid
flowchart TD
    %% STYLES
    classDef external fill:#e3f2fd,stroke:#2196f3,color:#333;
    classDef trusted fill:#e8eaf6,stroke:#3f51b5,color:#333;
    classDef database fill:#fffde7,stroke:#fbc02d,stroke-width:2px;
    classDef danger fill:#ffebee,stroke:#f44336,color:#000;

    %% EXTERNAL LAYER
    internet([fa:fa-globe Internet]):::external
    api3rd([fa:fa-plug 3rd-party API]):::external

    %% TRUSTED LAYER
    subgraph "Trusted Layer"
        direction TB
        gateway([API Gateway]):::trusted
        auth([Authentication]):::trusted
        account([Account Service]):::trusted
        exchange([Exchange Service]):::danger
        db[(Database)]:::database
    end

    %% FLOWS (All labelled as CRUD for simplicity)
    internet-->|REQUEST|gateway
    gateway-->|CRUD|auth
    gateway-->|CRUD|account
    gateway-.->|CONSULT|exchange
    account --> db

    auth-->|CRUD|account

    exchange-.->|CONSULT|api3rd

    %% LINK STYLE FOR DANGER ZONE
    class exchange,api3rd danger

```

=== "Dockerfile do Exchange"

    ``` {  .copy .select linenums='1' title="Dockerfile" }
    --8<-- "https://raw.githubusercontent.com/laupontiroli/exchange-service/main/Dockerfile"
    ```


Também foi adicionado no compose.yaml geral esse serviço 

=== "Compose.yaml"

    ``` {  .yaml .copy .select linenums='1' title="Compose.yaml" }
    --8<-- "https://raw.githubusercontent.com/laupontiroli/platforms/main/apis/compose.yaml"
    ```


## Discussões

Esse roteiro foi particulamente fácil. O mais dificil foi entender como montar o dockerfile e adicionar ao compose geral.

## Conclusão

Com essa parte do roteiro nos aprendemos a adicionar microsserviços e também que é possível a integração entre serviços de diferentes linguagens.