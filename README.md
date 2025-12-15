# 🚀 Projeto SOLID Financeiro: Gerenciamento de Usuários

Este projeto demonstra a aplicação dos cinco princípios **SOLID** na arquitetura de um serviço de gerenciamento de usuários utilizando Spring Boot. O objetivo é criar um código que seja **manutenível**, **flexível** e **escalável**.

---

## 🧱 Princípios SOLID Aplicados

### S - Single Responsibility Principle (SRP)

Cada classe no projeto tem uma única razão para mudar, garantindo alta coesão e baixo acoplamento.

* **Orquestração de Fluxo:** O **`UsuarioService`** é o orquestrador principal, focado apenas em coordenar as etapas do registro/CRUD (validação, mapeamento, persistência, ações pós-registro).
* **Responsabilidades Segregadas:** A lógica interna é delegada a componentes especializados:
  * **`UsuarioFactory` (ou Mapper)**: Única responsabilidade de **Conversão** entre DTO/Record e Entidade de Domínio.
  * **`UsuarioValidator`**: Responsabilidade única pela **Validação** das regras de negócio.
  * **`LoggingService`**: Responsabilidade única de **Auditoria/Logging**.

---

### O - Open/Closed Principle (OCP)

O sistema é **aberto para extensão** de novas funcionalidades, mas **fechado para modificação** do código central (`UsuarioService`).

* **Padrão Strategy Pós-Registro:** A interface **`PostRegistrationAction`** permite adicionar novas ações (ex: `EmailUsuarioSender`, `PermissaoUsuario`) simplesmente criando novas classes que a implementam.
* O `UsuarioService` é imune a mudanças quando um novo requisito pós-registro surge, pois ele apenas itera sobre a lista de ações injetadas pelo Spring.

---

### L - Liskov Substitution Principle (LSP)

Garante que os subtipos (implementações) possam ser usados de forma intercambiável pelo código cliente, mantendo o comportamento esperado do supertipo (interface).

* **Persistência Flexível:** O `UsuarioService` depende da interface **`UsuarioRepository`**.
* As implementações concretas (`JpaUsuarioRepository`, `LegadoUsuarioRepository`) são substitutos válidos. A injeção dinâmica via `@Qualifier` (ou `@Profile`) permite trocar a fonte de dados sem modificar o código do serviço de alto nível, desde que ambos os repositórios cumpram o contrato da interface.

---

### I - Interface Segregation Principle (ISP)

Os clientes (ex: Controllers) não são forçados a depender de métodos que não utilizam. A interface "gorda" do CRUD foi segregada em contratos menores e coesos:

* **Interfaces Segregadas:** Foram criadas interfaces específicas, como:
  * `UsuarioQueryService` (apenas `listarUsuarios`)
  * `UsuarioModificationService` (apenas `criarUsuario`, `atualizarUsuario`)
  * `UsuarioAdministrationService` (apenas `removerUsuario`)
* **Injeção Mínima:** O `UsuarioController` injeta apenas as interfaces necessárias (ex: `UsuarioQueryService`), ficando desacoplado e sem acesso aos métodos perigosos (`removerUsuario`).

---

### D - Dependency Inversion Principle (DIP)

Garante que os módulos de alto nível (Serviços) dependam de abstrações, e não de implementações concretas (classes de baixo nível).

* **Injeção por Construtor:** Todas as classes de alto nível (ex: `UsuarioService`, `UsuarioController`) recebem suas dependências (Mappers, Repositórios, Validadores) através do construtor.
* O `UsuarioService` depende de interfaces (`UsuarioRepository`, `PostRegistrationAction`), e o **Spring** (Contêiner IoC) gerencia e fornece as implementações concretas em tempo de execução.