# Confinance

###  Sobre Nós: 💼
O Grupo five Tech composto por:
 Bruna Camilly, João Victtor Silva, Lucas Fernando, Selton Alves e Thamyres Cordeiro.
 Teve a iniciativa de fazer uma aplicação com a principal ideia de ajudar o usuário em suas questões financeiras, onde o usuário terá em sua visiblidade suas dispensas e receitas do mês em questão.

### Conceito Da Aplicação: 📃
    
O aplicativo tem como objetivo ajudar o usuário a gerenciar suas finanças pessoais de maneira eficiente. Com o uso do app, o usuário pode inserir informações sobre seus gastos mensais, como alimentação, educação e outras categorias, além de registrar suas receitas, seja por meio de investimentos ou salário. O layout do aplicativo é simples, mas foi projetado com foco na experiência do usuário e na facilidade de uso no dia a dia.

Além de permitir que o usuário insira informações sobre seus gastos e receitas, o aplicativo também realiza cálculos matemáticos para fornecer ao usuário uma visão clara de sua situação financeira no final do mês. Com base nos dados inseridos, o aplicativo realiza operações matemáticas para calcular o total de gastos do usuário em diferentes categorias, como alimentação, educação, transporte, entre outras. Além disso, o aplicativo também realiza cálculos para determinar o total de receitas do usuário em um determinado período.

Ao combinar essas informações, o aplicativo é capaz de fornecer uma visão completa de quanto o usuário ganhou e gastou durante o mês, bem como seu saldo final. Isso permite que o usuário veja claramente onde seu dinheiro está sendo gasto e faça ajustes em seus hábitos de consumo, se necessário, para atingir seus objetivos financeiros. Com esses recursos, o aplicativo ajuda o usuário a ter mais controle sobre suas finanças e tomar decisões mais informadas sobre como gerenciar seu dinheiro de forma mais eficiente.

### Tecnologias Utilizadas: 🛠
   - MySQL 
   - Spring Boot
   - Kotlin

### Formato de Desenvolvimento: 🧑‍💻
A aplicação utilizará o MySQL como SGBD para armazenarinformações do usuário, como nome, e-mail, senha, entre outras.O Aplicativo será desenvolvido com Kotlin para Android, sendo responsável pela criação dos layouts e pela lógica de negócios usando a arquitetura MVVM.

Para a comunicação com o banco de dados, a aplicação utilizará o Spring Boot, que permitirá a criação de uma API RESTful. Serão utilizadas as dependências Spring Web, Spring Data JPA, MySQL Driver e Validation. O Spring Boot seguirá a arquitetura MVC.

Este formato de desenvolvimento permitirá uma estrutura modular da aplicação, possibilitando a fácil manutenção do código e a escalabilidade da aplicação no futuro. O uso do Spring Boot como framework de desenvolvimento de API RESTful permitirá uma comunicação eficiente e segura com o banco de dados MySQL, garantindo a segurança dos dados do usuário. A utilização do Kotlin como linguagem de programação permitirá um desenvolvimento mais ágil e eficiente da aplicação para a plataforma Android. A arquitetura MVVM permitirá uma separação clara entre a camada de apresentação e a lógica de negócios, facilitando a manutenção e o teste da aplicação.

MVVM (Model-View-ViewModel) e MVC (Model-View-Controller) são duas arquiteturas de software usadas para separar a lógica de apresentação da lógica de negócios em uma aplicação.

A arquitetura MVVM é composta por três camadas: a camada do modelo, a camada da visão e a camada do ViewModel. A camada do modelo representa os dados e a lógica de negócios da aplicação. A camada da visão é responsável pela interface do usuário e pela apresentação dos dados. A camada do ViewModel atua como um intermediário entre a camada da visão e a camada do modelo, permitindo que a visão seja atualizada automaticamente quando os dados do modelo são alterados.

Já a arquitetura MVC é composta pelas camadas do modelo, da visão e do controlador. A camada do modelo representa os dados e a lógica de negócios da aplicação. A camada da visão é responsável pela interface do usuário e pela apresentação dos dados. A camada do controlador atua como um intermediário entre a camada da visão e a camada do modelo, respondendo às ações do usuário na visão e atualizando o modelo de acordo.

### Diagrama UML: 📊

![UML](https://github.com/FiveTech22/back-end-frameworks/blob/main/UML.png)


### Protótipos da aplicação 📱

#### Telas de Login:
#### 1.1 tela de abertura / 1.2 tela de criar conta  

![1.1 tela de abertura](https://github.com/FiveTech22/back-end-frameworks/blob/main/1.1%20Tela%20de%20Abertura.jpg) ![1.2 tela de criar conta](https://github.com/FiveTech22/back-end-frameworks/blob/main/1.2%20tela%20criar%20conta.jpg)

#### 1.3 tela de login / 1.4 tela de recuperação de senha
![1.3 tela de login](https://github.com/FiveTech22/back-end-frameworks/blob/main/1.3%20tela%20de%20login.jpg) ![1.4 tela de recuperação de senha](https://github.com/FiveTech22/back-end-frameworks/blob/main/1.4%20tela%20recupera%C3%A7%C3%A3o%20de%20senha.jpg) 

#### 1.5 tela código de recuperação / 1.6 tela nova senha
![1.5 tela de código de recuperação](https://github.com/FiveTech22/back-end-frameworks/blob/main/1.5%20Tela%20codigo%20de%20recupera%C3%A7%C3%A3o.jpg) ![1.6 tela nova senha](https://github.com/FiveTech22/back-end-frameworks/blob/main/1.6%20Tela%20nova%20senha.jpg)

### Telas Iniciais

#### 1.1 Tela Inicial / Exemplo - tela inicial
![tela inicial](https://github.com/FiveTech22/back-end-frameworks/blob/main/tela%20Inicial.jpg) ![Exemplo - tela inicial](https://github.com/FiveTech22/back-end-frameworks/blob/main/Exemplo%20-Tela%20Inicial.jpg)

#### 1.2 Tela de menu / 1.3 Tela do usuário
![Tela de menu](https://github.com/FiveTech22/back-end-frameworks/blob/main/tela%20de%20menu.jpg) ![tela do usuário](https://github.com/FiveTech22/back-end-frameworks/blob/main/tela%20do%20usuario.jpg)

#### 1.4 Tela sobre nós / 1.5 Tela de termo de uso
![Tela sobre nós](https://github.com/FiveTech22/back-end-frameworks/blob/main/Tela%20Sobre%20N%C3%B3s.jpg) ![tela de termo de uso](https://github.com/FiveTech22/back-end-frameworks/blob/main/Tela%20Termos%20de%20Uso.jpg)

### Definição das Funcionalidades ✏️

#### Tela de Login:

Campo de E-mail ou Usuário: oferecer um campo onde o usuário pode inserir seu endereço de e-mail para fazer login.

Campo de Senha: permitir que o usuário insira sua senha para acessar a conta.

Botão de Entrar: permitir que o usuário envie suas informações de login para acessar a conta.

Recuperação de Senha: incluir um botão (“recuperar senha”) para que o usuário seja direcionado para a tela de recuperação de senha, caso a tenha esquecido. Se for o caso de esquecimento da senha, será enviado um código para o e-mail inserido e a partir daí permitir que o usuário crie uma nova senha segura e confirmá-la para concluir a recuperação de senha.

Criar Conta: O botão (“Criar uma conta”) serve para que novos usuários possam criar uma conta no aplicativo.

#### Tela de Inicial:


Visão geral: Exibe o saldo total da conta, assim também o total geral do mês de receitas e despesas criadas. Como também os registros criados nas categorias de despesas e receitas. 

Perfil do usuário: Permitir que o usuário visualize e edite suas informações pessoais, como nome e e-mail. Permitir que o usuário altere sua senha para aumentar a segurança da conta. Permitir que o usuário encerre sua conta no aplicativo, se desejar.

Menu: oferecer um menu de navegação para que o usuário possa acessar as diferentes seções do aplicativo como a parte do perfil do usuário, atalho para voltar a tela inicial, atalhos de planejamento dos objetos, receitas e despesas, como também atalhos para visualizar informações sobre o aplicativo e termos de uso.

####  Tela de Objetivo: 


Criação objetivo: o usuário pode criar objetivos financeiros, como por exemplo, juntar dinheiro para comprar um carro, fazer uma viagem ou pagar uma dívida. É possível definir o nome, valor do objetivo, valor já poupado e o prazo para alcançá-lo.

Descrição do objetivo: Mostra o ícone e o nome do objetivo criado, como também o quanto tem poupado e o valor do objetivo para ser alcançado podendo fazer ajustes em seu planejamento financeiro conforme necessário ou exclui-lo.

####  Tela de Receitas: 


Cadastro de receitas: o usuário pode cadastrar o valor da sua receita, a descrição e a data de recebimento. E também permite categorizar as receitas por tipo, o que ajuda a ter uma visão geral do fluxo de dinheiro.

Categorias: O usuário pode visualizar suas receitas criadas por categoria, podendo editar e excluir.


####  Tela de Despesas: 

Cadastro de despesas: o usuário pode cadastrar o valor da sua despesa, a descrição e a data da despesa gastas. E também permite categorizar as despesas por tipo, o que ajuda a ter uma visão geral do fluxo de dinheiro.

Categorias: O usuário pode visualizar suas despesas criadas por categoria, podendo editar e excluir.
