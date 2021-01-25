# Transações-api

Uma API em Java e Spring Framework que recebe transações e devolve estatísticas.



## Como a API deve funcionar?

A API deve criar transações e deletar todas as transações. Além disso, deve calcular estatísticas sobre as transações criadas. A API terá os seguintes endpoints:

`POST /transacoes`: cria uma transação. 

**Body:**

```
{
  "dataHora": "2021-01-23T20:32:01.262Z",
  "valor":"6"
}
```

**Onde:**

`valor`: valor da transação;<br/><br/>
`dataHora`: data e hora da transação no formato ISO 8601 YYYY-MM-DDThh:mm:ss.sssZ no timezone local.


E deve retornar com body vazio com um dos códigos a seguir:

* `201 Created`: A transação foi aceita (ou seja, foi validada, está válida e foi registrada).
* `422 Unprocessable Entity`: A transação não foi aceita por qualquer motivo (1 ou mais dos critérios de aceite não foram atendidos - por exemplo: uma transação com valor menor que 0).
* `400 Bad Request`: A API não compreendeu a requisição do cliente (por exemplo: um JSON inválido).

<br/><br/>

`DELETE /transacoes`: Remove todas as transações.

Deve aceitar uma requisição com body vazio o seguinte código:

* `200 OK`: Todas as informações foram apagadas com sucesso

<br/><br/>
`GET /estatistica`: Retorna estatísticas das transações que aconteceram nos últimos 60 segundos (1 minuto).
 As estatísticas que devem ser calculadas são:

```
{   
   "count": 10,
   "sum": 1234.56,  
   "avg": 123.456,
   "max": 12.34,
   "min": 123,56
}
```
<br/>
Onde:<br/><br/>

`count`: Quantidade de transações nos últimos 60 segundos.<br/>
`sum`: Soma total do valor transacionado nos últimos 60 segundos.<br/>
`avg`: Média do valor transacionado nos últimos 60 segundos.<br/>
`min`: Menor valor transacionado nos últimos 60 segundos.<br/>
`max`: Maior valor transacionado nos últimos 60 segundos.<br/>




### Documentação da api


Por padrão, a documentação da API está disponível no endereço [http://localhost:8080/swagger-ui.html#/](http://localhost:8080/swagger-ui.html#/)
