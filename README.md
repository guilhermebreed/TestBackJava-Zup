# TestBackJava-Zup

# Configuração

Alterar o properties em 'resources' e em 'test', colocando a senha da sua conexão mysql se necessário.
Criar o database com o seguinte comando 'create DATABASE zuptest' no Mysql com o nome 'zuptest'

Instalar o Maven

Executar o comando 'mvn clean install' dentro do diretório do projeto;

Executar o comando 'mvn spring-boot:run' dentro do diretório do projeto, o mesmo será executado.

Caso considere mais viável, o mesmo pode ser executado dentro de qualquer IDE de sua preferência, no meu caso, utilizei o Eclipse :);

# Funcionalidades/Desafios/URL´s

##Funcionalidade: Integração de gastos por cartão
http://localhost:8081/api/v1/gastos

Considere o Json abaixo para inclusão de gastos
json= {
      "value": 100, "userCode": 1, "date": "2018-05-01T12:00:00"
}

##Funcionalidade: Listagem de gastos*
http://localhost:8081/api/v1/gastos/{id}

##Funcionalidade: Categorização automática de gasto
http://localhost:8081/api/v1/gastos 
json= {
    "code": 1, "value": 40, "userCode": 1, "date": "2018-05-02T22:30:00", "description": "gasto 1", "version":0
}

##Funcionalidade: Filtro de gastos
http://localhost:8081/api/v1/gastos?userCode=1&date=2018-05-02:12:00

##Funcionalidade: Sugestão de categoria
http://localhost:8081/api/v1/categorias/{description}
