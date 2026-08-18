# ☕ Java Backend Learning

Repositório dedicado aos meus estudos e projetos práticos em **Java**, **Maven** e **Programação Orientada a Objetos**, com foco no desenvolvimento Backend.

Aqui estão projetos acadêmicos e pessoais desenvolvidos durante minha jornada de aprendizado em Java.

---

## 📂 Projetos

### 💼 MatchVagas

Sistema de console desenvolvido em Java para **gestão de candidatos e vagas**, com um algoritmo de compatibilidade que calcula a afinidade entre um candidato e uma vaga.

O projeto foi desenvolvido com foco na aplicação prática de **Programação Orientada a Objetos, Generics, Records, Streams, Lambdas e organização em camadas**.

#### Funcionalidades

**👔 Perfil Empregador**

* Cadastrar vagas
* Editar vagas
* Excluir vagas
* Listar vagas cadastradas
* Buscar candidatos compatíveis
* Gerar ranking de candidatos por afinidade

**👤 Perfil Candidato**

* Cadastrar candidato
* Editar candidato
* Excluir candidato
* Listar candidatos cadastrados
* Buscar vagas compatíveis

#### 🤝 Algoritmo de Match

Cada combinação entre candidato e vaga recebe uma pontuação de **0 a 6**, de acordo com os critérios de compatibilidade:

| Critério                                      | Pontos |
| --------------------------------------------- | -----: |
| Área de atuação compatível                    |     +3 |
| Expectativa salarial dentro do limite da vaga |     +2 |
| Modalidade de trabalho compatível             |     +1 |

A pontuação final é classificada da seguinte forma:

| Pontuação | Classificação |
| --------: | ------------- |
|         6 | Perfeito      |
|         5 | Ótimo         |
|         4 | Bom           |
|       < 4 | Ruim          |

Candidatos classificados como **Ruim** não são incluídos no ranking de recomendações.

#### 🏗️ Arquitetura

O projeto é organizado em camadas para separar responsabilidades:

```text
src/
└── main/
    └── java/
        ├── model/
        │   ├── Candidato
        │   ├── Vaga
        │   └── ResultadoMatch
        │
        ├── repository/
        │   ├── GenericDAO<T>
        │   ├── RepositoryCandidato
        │   └── RepositoryVagas
        │
        ├── service/
        │   └── MatchService
        │
        ├── console/
        │   ├── CandidatoConsole
        │   └── VagasConsole
        │
        └── Main
```

O `GenericDAO<T>` centraliza as operações de CRUD, permitindo reutilizar a mesma estrutura para diferentes entidades e reduzindo a duplicação de código entre os repositórios.

#### 🛠️ Tecnologias e conceitos utilizados

* Java
* Maven
* Programação Orientada a Objetos
* Generics
* Records
* Streams
* Lambdas
* Arquitetura em camadas
* CRUD
* Git/GitHub

#### 🚀 Possíveis melhorias

* Persistência dos dados em arquivos
* Integração com banco de dados
* Validação das entradas do usuário
* Tratamento de exceções
* Testes unitários para o `MatchService`
* Interface gráfica ou aplicação web

---

### 📦 meu-primeiro-maven

Meu primeiro projeto utilizando **Maven**, desenvolvido para aprender:

* Estrutura de projetos Maven
* Gerenciamento de dependências
* `pom.xml`
* Compilação e execução de aplicações Java

---

### 🛒 sistema-estoque

Sistema simples de gerenciamento de estoque desenvolvido em Java.

**Conceitos praticados:**

* Programação Orientada a Objetos
* Classes e objetos
* Encapsulamento
* Organização em camadas
* Lógica de negócio

---

### 🎓 unb_poo_maven

Projeto acadêmico desenvolvido na disciplina de **Programação Orientada a Objetos da Universidade de Brasília (UnB)** utilizando Maven.

---

## ☕ Tecnologias

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge\&logo=openjdk\&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge\&logo=apachemaven\&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge\&logo=git\&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge\&logo=github\&logoColor=white)

---

## 🚀 Como executar

Clone o repositório:

```bash
git clone https://github.com/warlleymedeiros/java-backend-learning.git
```

Entre no diretório:

```bash
cd java-backend-learning
```

Depois, entre no projeto desejado e execute utilizando o Maven.

---

## 📚 Objetivo

Este repositório acompanha minha evolução no desenvolvimento Backend com Java, reunindo exercícios, projetos acadêmicos e projetos pessoais desenvolvidos durante meus estudos.

O objetivo é aplicar os conceitos estudados na prática e evoluir gradualmente para tecnologias e arquiteturas utilizadas no mercado.
