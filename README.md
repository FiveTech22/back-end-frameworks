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

![UML](https://github.com/FiveTech22/back-end-frameworks/blob/main/img/UML.png)


### Protótipos da aplicação 📱

### Telas de Login:
#### 1.1 tela de abertura / 1.2 tela de criar conta  

![1.1 tela de abertura](https://github.com/FiveTech22/back-end-frameworks/blob/main/img/1.1%20Tela%20de%20Abertura.jpg) ![1.2 tela de criar conta](https://github.com/FiveTech22/back-end-frameworks/blob/main/img/1.2%20tela%20criar%20conta.jpg)

#### 1.1 Tela de abertura: 
Ao entrar no aplicativo, o usuário poderá criar uma conta ou fazer login por meio do botão "Criar conta", que o direciona para a tela de criação de conta . Da mesma forma, se clicar no botão "Fazer login", será direcionado para a tela de login.


#### 1.2 Tela de criação de conta:
  Caso o usuário clique no botão "Criar conta" presente na (tela de abertura), será direcionado para esta tela, na qual deverá informar seu nome, e-mail e senha. Se algum dos campos estiver em branco, aparecerá a mensagem "Preencha o campo". Se o usuário tentar prosseguir sem preencher todos os campos, não conseguirá criar uma conta. Se o usuário já tiver uma conta, poderá clicar no botão "Já tenho uma conta" e será direcionado para a (tela de login). Para voltar à (tela de abertura), basta clicar na seta localizada na parte superior esquerda.


#### 1.3 tela de login 
![1.3 tela de login](https://github.com/FiveTech22/back-end-frameworks/blob/main/img/tela%20de%20login.jpg)

#### 1.3 Tela de login: 
 Nesta tela, o usuário deve informar o e-mail e a senha que foi criada na (tela de criação de conta). Se o usuário digitar um e-mail diferente do que foi registrado, aparecerá a mensagem "E-mail incorreto". Da mesma forma, se digitar uma senha diferente, aparecerá a mensagem "Senha incorreta". Caso o usuário tenha esquecido a senha, poderá clicar no botão "Recuperar senha" e será direcionado para a (tela de recuperação de senha). Se quiser criar uma nova conta, poderá voltar para a (tela de criação de conta) clicando no botão "Criar conta". Se o usuário preencher o e-mail e a senha correta, poderá clicar no botão "Entrar", o que o carregará à (tela inicial). Para voltar à (tela de abertura), basta clicar na seta localizada na parte superior esquerda.



### Telas Iniciais

#### 1.1 Tela Inicial / Exemplo - tela inicial
![tela inicial](https://github.com/FiveTech22/back-end-frameworks/blob/main/img/tela%20Inicial.jpg) ![Exemplo - tela inicial](https://github.com/FiveTech22/back-end-frameworks/blob/main/img/Exemplo%20-Tela%20Inicial.jpg)

#### 1.1 Tela inicial:
Nesta tela, o usuário poderá visualizar um botão com três traços um abaixo do outro, como no exemplo ao lado “ ≡ “, que representará a (tela do menu). Ao clicar, será direcionado para a mesma. Assim como o mês que terá duas setas, uma para a esquerda “←” e a outra para a direita “→”, com o intuito de, quando o usuário clicar na seta esquerda, voltar para o mês anterior e, quando clicar na seta direita, ir para o mês seguinte. Também haverá a possibilidade de visualizar o saldo da conta, o total das receitas e despesas e suas categorias. A parte de categoria de receitas e despesas só terá funcionalidade após adicionar as respectivas receitas e despesas. Se o usuário não criar as categorias, elas ficarão vazias com a mensagem: “Nenhum registro encontrado”. Após adicionadas, aparecerão o ícone, o nome e o valor descrito na (tela de receita) ou (tela de despesa). Ao clicar no ícone da categoria, será direcionado para a respectiva categoria. O usuário não poderá editar o saldo de sua conta, pois ele será atualizado automaticamente ao adicionar receitas e despesas. Ao clicar no nome “receitas”, será direcionado para a (tela de receita), assim como ao clicar no nome “despesa”, será direcionado para a (tela de despesas).

#### 1.2 Tela de menu / 1.3 Tela do usuário
![Tela de menu](https://github.com/FiveTech22/back-end-frameworks/blob/main/img/tela%20de%20menu.jpg) ![tela do usuário](https://github.com/FiveTech22/back-end-frameworks/blob/main/img/tela%20do%20usuario.jpg)

####  1.2 Tela de menu: 
  Nesta tela, ao clicar no ícone do perfil redondo na parte superior da tela, o usuário será direcionado para a tela do usuário. Ao clicar em 'Início', será direcionado para a (tela inicial). Da mesma forma, ao clicar em 'Objetivos', será direcionado para a (tela de objetivos), em 'Receitas' para a (tela de receitas), em 'Despesas' para a (tela de despesas), em 'Sobre Nós' para a (tela Sobre Nós) e em 'Termos de Uso' para a (tela de termos de uso).

#### 1.3 Tela do usuário: 
 Nesta tela, o usuário poderá visualizar seu nome e e-mail cadastrados na (tela de criar conta), e também poderá editá-los. Além disso, poderá clicar no botão 'Sair' para ser direcionado à (tela de abertura). Nesta tela, o usuário poderá alterar sua senha, digitando sua senha atual, a nova senha e confirmando-a. Ao clicar no botão 'Alterar Senha', se a senha atual estiver incorreta, será exibida uma mensagem de erro: 'Senha incorreta'. Caso contrário, uma mensagem de sucesso será exibida: 'Senha alterada com sucesso'. Para voltar à (tela de menu), basta clicar na seta na parte superior esquerda da tela.

#### 1.4 Tela sobre nós / 1.5 Tela de termo de uso
![Tela sobre nós](https://github.com/FiveTech22/back-end-frameworks/blob/main/img/Tela%20Sobre%20N%C3%B3s.jpg) ![tela de termo de uso](https://github.com/FiveTech22/back-end-frameworks/blob/main/img/Tela%20Termos%20de%20Uso.jpg)

#### 1.4 Tela 'Sobre Nós': 
 Nesta tela, apresentaremos um pouco sobre o propósito de nosso aplicativo. Para retornar à (tela de menu), basta clicar na seta localizada na parte superior esquerda.

#### 1.5 Tela de Termo de Uso:
  Nesta tela, apresentaremos o termo de uso do nosso aplicativo. Para retornar à (tela de menu), basta clicar na seta localizada na parte superior esquerda.

### Telas de Objetivos:


#### 1.1 Tela de objetivo / Exemplo - Tela de Objetivo
 ![1.1 Tela de  objetivo ](https://github.com/FiveTech22/back-end-frameworks/blob/main/img/1.1%20Tela%20do%20Objetivo.jpg)  ![Exemplo - Tela de Objetivo](https://github.com/FiveTech22/back-end-frameworks/blob/main/img/1.2%20Exemplo%20-%20tela%20de%20Objetivo.jpg)
 
#### 1.1 Tela de objetivo:
Nesta tela, o usuário pode clicar no botão "+" que o direciona para a (tela de criação do objetivo). Assim que o usuário criar seu objetivo, poderá visualizá-lo, podendo também editá-lo clicando no ícone de lápis que ficará ao lado da descrição do objetivo, indo então para (Tela editar objetivo). O usuário também poderá clicar no ícone de lixeira que ficará acima do valor do objetivo para excluí-lo da tela. Ao clicar na seta na parte superior esquerda, o usuário irá voltar para (tela de menu).

#### 1.2 Tela de criação de objetivo /  1.3 Tela de edição de objetivo

![1.2 Tela de criação de objetivo](https://github.com/FiveTech22/back-end-frameworks/blob/main/img/1.3%20Tela%20da%20Cria%C3%A7%C3%A3o%20do%20Objetivo.jpg) ![1.3 Tela de edição de objetivo](https://github.com/FiveTech22/back-end-frameworks/blob/main/img/Exemplo%20-%20Tela%20da%20edi%C3%A7%C3%A3o%20do%20Objetivo.jpg)

#### 1.2 Tela de criação de objetivo:
Nesta tela, o usuário pode definir uma meta que deseja alcançar. Ele pode inserir o nome do objetivo, o valor a ser alcançado, o valor já poupado e, se desejar, uma data para alcançá-lo. Além disso, ele pode escolher um ícone para representar o objetivo. Ao clicar no botão "Criar", o usuário será direcionado para a (tela do objetivo), se desejar cancelar a criação do objetivo, ele pode clicar no "X" na parte superior esquerda da tela, o que o levará de volta à (tela do objetivo) 

#### 1.3 Tela de edição de objetivo:
 Nesta tela, o usuário poderá editar o que foi criado na (tela de criação de objetivo) , alterando o nome do objetivo, o valor do objetivo, o valor já poupado, assim como a data desejada para alcançá-lo, além dos ícones. Depois de alterado, o usuário poderá clicar no botão "salvar" na parte inferior da tela para salvar suas alterações. No entanto, se desejar cancelar a edição, poderá apertar o botão no formato de um "x" no canto superior esquerdo da tela para cancelar as alterações.

### Telas de receita: 

#### 1.1 Tela de receita / 1.2 Tela de edição de receita

![1.1 Tela de receita](https://github.com/FiveTech22/back-end-frameworks/blob/main/img/1.1%20tela%20receita.jpg) ![1.2 Tela de edição de receita](https://github.com/FiveTech22/back-end-frameworks/blob/main/img/1.2%20Tela%20editar%20receita.jpg)
#### 1.1 Tela de receita: 
 Nesta tela, o usuário poderá inserir o valor da receita, a descrição do que se trata essa receita, a data em que foi recebida e, por fim, selecionar uma das quatro categorias: salário, investimento, serviços ou outros. Essa funcionalidade permite ao usuário identificar em quais áreas as receitas estão dando mais retorno. Ao clicar no botão 'Criar', a receita criada irá aparecer na (tela inicial) na categoria de receitas. Já ao clicar no 'X', localizado na parte superior esquerda da tela, o usuário poderá fechar a tela de criação de receitas e voltar para a (tela inicial) .

#### 1.2 Tela de edição de receita:
Nesta tela, o usuário poderá editar o que foi criado na (tela de receita), alterando o valor da receita, bem como a descrição, data e categoria da receita. Depois de alterar, o usuário poderá clicar no botão "salvar" na parte inferior da tela para salvar suas modificações. Se desejar cancelar a alteração, basta clicar no botão em forma de "x" no canto superior esquerdo da tela para cancelar as alterações.

### Telas de despesas: 

#### 1.1 Tela de despesas / 1.2 Tela Editar Despesas

![1.1 Tela de despesas](https://github.com/FiveTech22/back-end-frameworks/blob/main/img/1.1%20Tela%20Despesa.jpg) ![1.2 Tela Editar Despesas](https://github.com/FiveTech22/back-end-frameworks/blob/main/img/1.2%20Tela%20%20Editar%20Despesa.jpg)

#### 1.1 Tela de despesas:
 Nesta tela, o usuário poderá inserir o valor da despesa, a descrição do que é essa despesa, a data em que a despesa foi feita e, por fim, as categorias que serão separadas em cinco ícones: alimentação, casa, saúde, educação e outros. Com essa funcionalidade, o usuário poderá saber onde suas despesas estão sendo gastas. Ao clicar no botão "criar", a despesa criada aparecerá na  (tela inicial)  na categoria de despesas. Ao clicar no botão em forma de  "X" na parte superior esquerda da tela, o usuário fechará a criação da despesa e voltará para a (tela inicial).

#### 1.2 Tela Editar Despesas:
Nesta tela, o usuário poderá editar o que foi criado na (Tela Despesa), alterando o valor da despesa, bem como a descrição, data e categoria da despesa. Depois de alterado, o usuário poderá clicar no botão "Salvar" na parte inferior da tela para salvar suas modificações. Caso queira cancelar a alteração, poderá clicar no botão em formato de "x" no canto superior esquerdo da tela, assim, a alteração será cancelada.

### Telas de categorias de receitas

#### 1.1 Exemplo - Tela Salário / 1.2 Exemplo - Tela Investimento: 

![1.1 Exemplo - Tela Salário](https://github.com/FiveTech22/back-end-frameworks/blob/main/img/1.1%20Exemplo%20-%20tela%20sal%C3%A1rio.jpg) ![1.2 Exemplo - Tela Investimento](https://github.com/FiveTech22/back-end-frameworks/blob/main/img/1.2%20Exemplo%20-%20tela%20Investimento.jpg)

#### 1.1 Exemplo - Tela Salário:
Assim que o usuário criar sua receita com o ícone salário, ele poderá visualizar as receitas criadas com o mesmo ícone, podendo ver a data do recebimento, a descrição e também podendo editar clicando no lápis ao lado do nome da descrição, indo para a (tela editar receita). Depois de editado, terá o botão "salvar" na parte inferior da tela, que, ao ser clicado, salvará automaticamente as informações editadas. O usuário também poderá clicar no ícone da lixeira que ficará acima do valor da receita e esta será excluída automaticamente da tela da categoria. Ao clicar na seta na parte superior esquerda, o usuário voltará para a (tela inicial).

#### 1.2 Exemplo - Tela Investimento: 
 Assim que o usuário criar sua receita com ícone de investimento, ele poderá visualizar seus investimentos criados com o mesmo ícone, podendo visualizar a data do recebimento, descrição e também podendo editar clicando no lápis que ficará ao lado do nome da descrição, indo então para a (tela editar receita). Depois de editado, terá o botão para 'salvar' na parte inferior da tela, que ao clicar, salvará automaticamente as informações editadas. E podendo clicar no ícone da lixeira que ficava acima do valor da receita que será excluído automaticamente da tela de sua categoria. Ao clicar na seta na parte superior esquerda, irá voltar para a (tela inicial)



#### 1.3 Exemplo - Tela Serviço / 1.4 Exemplo - Tela outros 

![1.3 Exemplo - Tela Serviço](https://github.com/FiveTech22/back-end-frameworks/blob/main/img/1.3%20Exemplo%20-%20tela%20servi%C3%A7os.jpg) ![1.4 Exemplo - Tela outros](https://github.com/FiveTech22/back-end-frameworks/blob/main/img/1.4%20Exemplo%20-%20tela%20outros%201.jpg)

#### 1.3 Exemplo - Tela Serviço: 
 Assim que usuário criar sua receita  com ícone serviço ele poderá visualizar suas receitas criadas com o mesmo ícone, podendo visualizar a data do recebimento, descrição e também podendo editar,  clicando no lápis que ficará ao lado no nome da descrição, indo então para (tela editar receita), depois de editado terá o botão para “salvar” na parte inferior da tela, que ao clicar salvará automático as informações editadas. e podendo clicar no ícone da  lixeira que ficava acima do valor da receita que será excluído automaticamente da tela de sua categoria. Ao clicar na seta na parte superior esquerda irá voltar para (tela de inicial ).


#### 1.4 Exemplo - Tela outros: 
 Assim que usuário criar sua receita com ícone outros ele poderá visualizar suas receitas criadas com o mesmo ícone, podendo visualizar a data do recebimento, descrição e também podendo editar  clicando no lápis que ficará ao lado no nome da descrição, indo então para (tela editar receita), depois de editado terá o botão para “salvar” na parte inferior da tela, que ao clicar salvará automático as informações editadas. e podendo clicar no ícone da  lixeira que ficava acima do valor da receita que será excluído automaticamente da tela de sua categoria. Ao clicar na seta na parte superior esquerda irá voltar para (tela de inicial ).

### Telas de categorias de despesas 

#### 1.1 Exemplo - Tela Alimentação / 1.2 Exemplo - Tela Casa 

![Exemplo - Tela Alimentação](https://github.com/FiveTech22/back-end-frameworks/blob/main/img/1.1%20Exemplo%20-%20tela%20Alimenta%C3%A7%C3%A3o.jpg) ![Exemplo - Tela Casa](https://github.com/FiveTech22/back-end-frameworks/blob/main/img/1.2%20Exemplo%20-%20tela%20casa.jpg)

#### 1.1 Exemplo - Tela Alimentação:
 Assim que o usuário criar sua despesa com o ícone Alimentação, poderá visualizar suas despesas criadas com o mesmo ícone, podendo ver a data do gasto, descrição e também poderá editá-las clicando no ícone de lápis que ficará ao lado do nome da descrição, indo então para a tela de editar despesa. Depois de editada, haverá o botão "Salvar" na parte inferior da tela, que ao ser clicado salvará automaticamente as informações editadas. Além disso, poderá clicar no ícone da lixeira que fica acima do valor da despesa para excluí-la automaticamente da tela de sua categoria. Ao clicar na seta na parte superior esquerda, irá voltar para a (tela inicial).

#### 1.2 Exemplo - Tela Casa: 
 Assim que usuário criar sua despesa com ícone Casa ele poderá visualizar suas despesas criadas com o mesmo ícone, podendo visualizar a data do gasto, descrição e também podendo editar  clicando no lápis que ficará ao lado no nome da descrição, indo então para (tela de editar despesa), depois de editado terá o botão para “salvar” na parte inferior da tela, que ao clicar salvará automático as informações editadas. e podendo clicar no ícone da  lixeira que ficava acima do valor da despesa que será excluído automaticamente da tela de sua categoria. Ao clicar na seta na parte superior esquerda irá voltar para (tela de inicial ).

#### 1.3 Exemplo - Tela Saúde 1.4 Exemplo - Tela Educação

![1.3 Exemplo - Tela Saúde](https://github.com/FiveTech22/back-end-frameworks/blob/main/img/1.3%20Exemplo%20-%20tela%20sa%C3%BAde.jpg) ![1.4 Exemplo - Tela Educação](https://github.com/FiveTech22/back-end-frameworks/blob/main/img/1.4%20Exemplo%20-%20tela%20educa%C3%A7%C3%A3o.jpg)

#### 1.3 Exemplo - Tela Saúde
 Assim que usuário criar sua despesa com ícone saúde ele poderá visualizar suas despesas criadas com o mesmo ícone, podendo visualizar a data do gasto, descrição e também podendo editar  clicando no lápis que ficará ao lado no nome da descrição, indo então para (tela de editar despesa), depois de editado terá o botão para “salvar” na parte inferior da tela, que ao clicar salvará automático as informações editadas. e podendo clicar no ícone da  lixeira que ficava acima do valor da despesa que será excluído automaticamente da tela de sua categoria. Ao clicar na seta na parte superior esquerda irá voltar para (tela de inicial ).

#### 1.4 Exemplo - Tela Educação: 
 Assim que usuário criar sua despesa com ícone educação ele poderá visualizar suas despesas criadas com o mesmo ícone, podendo visualizar a data do gasto, descrição e também podendo editar  clicando no lápis que ficará ao lado no nome da descrição, indo então para (tela de editar despesa), depois de editado terá o botão para “salvar” na parte inferior da tela, que ao clicar salvará automático as informações editadas. e podendo clicar no ícone da  lixeira que ficava acima do valor da despesa que será excluído 


#### 1.5 Exemplo - Tela Outros

![1.5 Exemplo - Tela Outros](https://github.com/FiveTech22/back-end-frameworks/blob/main/img/1.5%20Exemplo%20-%20tela%20outros.jpg)

#### 1.5 Exemplo - Tela Outros: 
 Assim que usuário criar sua despesa com ícone outros ele poderá visualizar suas despesas criadas com o mesmo ícone, podendo visualizar a data do gasto, descrição e também podendo editar  clicando no lápis que ficará ao lado no nome da descrição, indo então para (tela de editar despesa), depois de editado terá o botão para “salvar” na parte inferior da tela, que ao clicar salvará automático as informações editadas. e podendo clicar no ícone da  lixeira que ficava acima do valor da despesa que será excluído automaticamente da tela de sua categoria. Ao clicar na seta na parte superior esquerda irá voltar para (tela de inicial ).



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

### Contrato com  o JSON

Delete/User: Esse endpoint é responsável por deletar um usuário na aplicação. Receberá como paramêtro Id do tipo long para idntificar o usuário que será removido.

Resposta: COD 200.

{
  "Info": "Deletado com sucesso."
}

Resposta: COD 404.

{
  "Info": "Usuário não encontrado."
}

Login/User: Esse endpoint é responsável por fazer o login do usuário na aplicação. Receberá como paramêtro E-mail e Senha.

Resposta: COD 200.

{
  "Id":1,
  "name":"Amanda chaves",
  "Email": "Amanda@Gmail.com",
  "Password": "745614!",
  "Salary": 879514
}

Resposta: COD 404.

{
  "info": "Usuário não encontrado"
}

Create/User: Esse endpoint é responsável por fazer o cadastro do usuário na aplicação. Receberá como paramêtro Nome, Id, Salário, E-mail, Senha.

Resposta: COD 201.

{
  "Id":1,
  "name":"João Alves",
  "Email": "Joao@Gmail.com",
  "Password": "17894@",
  "Salary": 147451
}

Resposta: COD 403.

{
  "info": "Email já está em uso"
}

Resposta: COD 404.

{
  "info": "Erro No Email ou Senha do Usuário."
}

UPDATE/USER: Esse endpoint é responsável por fazer a atualização dos usuários na aplicação. Receberá como paramêtro Id.

Resposta: COD 200.

{
    "Info": "Atualização com sucesso."
}

Resposta: COD 404.

{
    "Info": "Usuário não encontrado."
}

Create/Category: Esse endpoint é responsável por criar uma categoria de despesas do usuário na aplicação. Receberá como paramêtro Id do usuário e da categoria.

Resposta: COD 201.

{ "category_id": 12, "user_id": 4, "type_moviment": "despesas", "value": 200, "date": "2024-04-23T18:25:43.511Z" }

Resposta: COD 404.

{ "Informações": "Parâmetros passados errados." }

Delete/Category: Esse endpoint é responsáel por deletar algo que está atrelado a um tipo de categoria. Receberá como paramêtro Id da categoria.

Resposta: COD 200. { "Info": "Categoria deletada com sucesso." }

Resposta: COD 404.

{ "Info": "Categoria não encontrada." }

Update/Category: Esse endpoint é responsáel por atualizar uma categoria. Receberá como paramêtro Id da categoria.

Resposta: COD 200.

{ "Info": "Atualizaçãoão realizada com sucesso." }

Resposta: COD 404.

{ "Info": "ERRO ao atualizar , por favor verifique os parâmetros." }

