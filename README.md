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

#### 1.1 Tela de abertura: 
Ao entrar no aplicativo, o usuário poderá criar uma conta ou fazer login por meio do botão "Criar conta", que o direciona para a tela de criação de conta . Da mesma forma, se clicar no botão "Fazer login", será direcionado para a tela de login.


#### 1.2 Tela de criação de conta:
  Caso o usuário clique no botão "Criar conta" presente na (tela de abertura), será direcionado para esta tela, na qual deverá informar seu nome, e-mail e senha. Se algum dos campos estiver em branco, aparecerá a mensagem "Preencha o campo". Se o usuário tentar prosseguir sem preencher todos os campos, não conseguirá criar uma conta. Se o usuário já tiver uma conta, poderá clicar no botão "Já tenho uma conta" e será direcionado para a (tela de login). Para voltar à (tela de abertura), basta clicar na seta localizada na parte superior esquerda.


#### 1.3 tela de login / 1.4 tela de recuperação de senha
![1.3 tela de login](https://github.com/FiveTech22/back-end-frameworks/blob/main/1.3%20tela%20de%20login.jpg) ![1.4 tela de recuperação de senha](https://github.com/FiveTech22/back-end-frameworks/blob/main/1.4%20tela%20recupera%C3%A7%C3%A3o%20de%20senha.jpg) 

#### 1.3 Tela de login: 
 Nesta tela, o usuário deve informar o e-mail e a senha que foi criada na (tela de criação de conta). Se o usuário digitar um e-mail diferente do que foi registrado, aparecerá a mensagem "E-mail incorreto". Da mesma forma, se digitar uma senha diferente, aparecerá a mensagem "Senha incorreta". Caso o usuário tenha esquecido a senha, poderá clicar no botão "Recuperar senha" e será direcionado para a (tela de recuperação de senha). Se quiser criar uma nova conta, poderá voltar para a (tela de criação de conta) clicando no botão "Criar conta". Se o usuário preencher o e-mail e a senha correta, poderá clicar no botão "Entrar", o que o carregará à (tela inicial). Para voltar à (tela de abertura), basta clicar na seta localizada na parte superior esquerda.

#### 1.4 Tela de recuperação de senha: 
 Ao entrar nesta tela, o usuário deverá informar o e-mail cadastrado no aplicativo. Em seguida, ao clicar no botão "Enviar", será direcionado para a (tela código de recuperação) para recuperação de senha. Se o usuário digitar um e-mail diferente do que foi registrado, aparecerá a mensagem "E-mail incorreto". Caso o usuário queira desistir de recuperar a senha, poderá clicar no botão "Criar uma conta" para ser direcionado à (tela de criação de conta) ou no botão "Login" para voltar à (tela de login). 


#### 1.5 tela código de recuperação / 1.6 tela nova senha
![1.5 tela de código de recuperação](https://github.com/FiveTech22/back-end-frameworks/blob/main/1.5%20Tela%20codigo%20de%20recupera%C3%A7%C3%A3o.jpg) ![1.6 tela nova senha](https://github.com/FiveTech22/back-end-frameworks/blob/main/1.6%20Tela%20nova%20senha.jpg)

#### 1.5 Tela de código de recuperação: 
 Nesta tela, aparecerá uma mensagem informando que um e-mail foi enviado ao usuário. Ele deve digitar os quatro números do código que foram enviados por e-mail, colocando-os nos quadrados que estão na tela. Se o usuário digitar algum número incorreto, aparecerá a mensagem "Código incorreto". Ele só poderá aceitar ao colocar o código correto. Ao clicar no botão "confirmar", o usuário será direcionado para a (tela de nova senha). Se o usuário clicar no botão "enviar e-mail novamente" antes do tempo determinado, aparecerá a mensagem "Aguarde até que o tempo termine". Ao clicar na seta na parte superior esquerda, ele voltará para a (tela de recuperação de senha).
 
 #### 1.6 Tela de nova senha: 
 Nesta tela, o usuário deve digitar uma nova senha e repeti-la novamente. Ao clicar no botão "Confirmar", ele será direcionado para a (tela de login). Ao clicar na seta na parte superior esquerda, ele voltará para a (tela de código de recuperação).


### Telas Iniciais

#### 1.1 Tela Inicial / Exemplo - tela inicial
![tela inicial](https://github.com/FiveTech22/back-end-frameworks/blob/main/tela%20Inicial.jpg) ![Exemplo - tela inicial](https://github.com/FiveTech22/back-end-frameworks/blob/main/Exemplo%20-Tela%20Inicial.jpg)

#### 1.1 Tela inicial:
Nesta tela, o usuário poderá visualizar um botão com três traços um abaixo do outro, como no exemplo ao lado “ ≡ “, que representará a (tela do menu). Ao clicar, será direcionado para a mesma. Assim como o mês que terá duas setas, uma para a esquerda “←” e a outra para a direita “→”, com o intuito de, quando o usuário clicar na seta esquerda, voltar para o mês anterior e, quando clicar na seta direita, ir para o mês seguinte. Também haverá a possibilidade de visualizar o saldo da conta, o total das receitas e despesas e suas categorias. A parte de categoria de receitas e despesas só terá funcionalidade após adicionar as respectivas receitas e despesas. Se o usuário não criar as categorias, elas ficarão vazias com a mensagem: “Nenhum registro encontrado”. Após adicionadas, aparecerão o ícone, o nome e o valor descrito na (tela de receita) ou (tela de despesa). Ao clicar no ícone da categoria, será direcionado para a respectiva categoria. O usuário não poderá editar o saldo de sua conta, pois ele será atualizado automaticamente ao adicionar receitas e despesas. Ao clicar no nome “receitas”, será direcionado para a (tela de receita), assim como ao clicar no nome “despesa”, será direcionado para a (tela de despesas).

#### 1.2 Tela de menu / 1.3 Tela do usuário
![Tela de menu](https://github.com/FiveTech22/back-end-frameworks/blob/main/tela%20de%20menu.jpg) ![tela do usuário](https://github.com/FiveTech22/back-end-frameworks/blob/main/tela%20do%20usuario.jpg)

####  1.2 Tela de menu: 
  Nesta tela, ao clicar no ícone do perfil redondo na parte superior da tela, o usuário será direcionado para a tela do usuário. Ao clicar em 'Início', será direcionado para a (tela inicial). Da mesma forma, ao clicar em 'Objetivos', será direcionado para a (tela de objetivos), em 'Receitas' para a (tela de receitas), em 'Despesas' para a (tela de despesas), em 'Sobre Nós' para a (tela Sobre Nós) e em 'Termos de Uso' para a (tela de termos de uso).

### 1.3 Tela do usuário: 
 Nesta tela, o usuário poderá visualizar seu nome e e-mail cadastrados na (tela de criar conta), e também poderá editá-los. Além disso, poderá clicar no botão 'Sair' para ser direcionado à (tela de abertura). Nesta tela, o usuário poderá alterar sua senha, digitando sua senha atual, a nova senha e confirmando-a. Ao clicar no botão 'Alterar Senha', se a senha atual estiver incorreta, será exibida uma mensagem de erro: 'Senha incorreta'. Caso contrário, uma mensagem de sucesso será exibida: 'Senha alterada com sucesso'. Para voltar à (tela de menu), basta clicar na seta na parte superior esquerda da tela.

#### 1.4 Tela sobre nós / 1.5 Tela de termo de uso
![Tela sobre nós](https://github.com/FiveTech22/back-end-frameworks/blob/main/Tela%20Sobre%20N%C3%B3s.jpg) ![tela de termo de uso](https://github.com/FiveTech22/back-end-frameworks/blob/main/Tela%20Termos%20de%20Uso.jpg)

### 1.4 Tela 'Sobre Nós': 
 Nesta tela, apresentaremos um pouco sobre o propósito de nosso aplicativo. Para retornar à (tela de menu), basta clicar na seta localizada na parte superior esquerda.

#### 1.5 Tela de Termo de Uso:
  Nesta tela, apresentaremos o termo de uso do nosso aplicativo. Para retornar à (tela de menu), basta clicar na seta localizada na parte superior esquerda.

### Telas de Objetivos:


#### 1.1 Tela de objetivo / Exemplo - Tela de Objetivo
 ![1.1 Tela de  objetivo ](https://github.com/FiveTech22/back-end-frameworks/blob/main/1.1%20Tela%20do%20Objetivo.jpg)  ![Exemplo - Tela de Objetivo](https://github.com/FiveTech22/back-end-frameworks/blob/main/1.2%20Exemplo%20-%20tela%20de%20Objetivo.jpg)

#### 1.2 Tela de criação de objetivo /  #### 1.3 Tela de edição de objetivo

![1.2 Tela de criação de objetivo](https://github.com/FiveTech22/back-end-frameworks/blob/main/1.3%20Tela%20da%20Cria%C3%A7%C3%A3o%20do%20Objetivo.jpg) ![1.3 Tela de edição de objetivo](https://github.com/FiveTech22/back-end-frameworks/blob/main/1.4%20Exemplo%20-%20Tela%20da%20edi%C3%A7%C3%A3o%20do%20Objetivo.jpg)

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
