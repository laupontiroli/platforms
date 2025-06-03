# Entrega Individual



    2025.1


## Grupo

1. Laura Pontiroli Machado
2. Julia Almeida Silva
- Grupo:
    - Laura Pontiroli Machado
    - Julia Almeida Silva




## Entregas

- [x] Roteiro 1 - FAST API
- [x] Roteiro 2 - PRODUCT
- [x] Roteiro 3 - ORDER
- [x] Roteiro 4 - JENKINS
- [ ] Roteiro 5 - MINIKUBE
- [ ] Roteiro 6 - BOTTLENECKS

## Diagramas

Diagrama da arquitetura final



``` mermaid
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
        product([Product Service]):::trusted
        order([Order Service]):::trusted
        exchange([Exchange Service]):::danger
        db[(Database)]:::database
    end

    %% FLOWS (All labelled as CRUD for simplicity)
    internet-->|REQUEST|gateway
    gateway-->|CRUD|auth
    gateway-->|CRUD|account
    gateway-->|CRUD|product
    gateway-->|CRUD|order
    gateway-->|CONSULT|exchange

    auth-->|CRUD|account
    account-->|CRUD|db
    product-->|CRUD|db
    order-->|CRUD|db
    order-->|CRUD|product

    exchange-.->|CONSULT|api3rd

    %% LINK STYLE FOR DANGER ZONE
    class exchange,api3rd danger

    %% AGGREGATE FLOW to DB
    account-->|CRUD|db
```



## Códigos

link dos repositórios: 

- [account](https://github.com/laupontiroli/account)
- [account-service](https://github.com/laupontiroli/account-service)
- [product](https://github.com/laupontiroli/product)
- [product-service](https://github.com/laupontiroli/product-service)
- [order](https://github.com/laupontiroli/order)
- [order-service](https://github.com/laupontiroli/order-service)
- [auth](https://github.com/laupontiroli/auth)
- [auth-service](https://github.com/laupontiroli/auth-service)
- [gateway-service](https://github.com/laupontiroli/gateway-service)
- [exchange-service](https://github.com/laupontiroli/exchange-service)



## Exemplo de vídeo

Video do postman da solução rodando localmente

<iframe width="100%" height="470" src="https://www.youtube.com/embed/3574AYQml8w" allowfullscreen></iframe>


## Referências

[Material for MkDocs](https://squidfunk.github.io/mkdocs-material/reference/){:target='_blank'}