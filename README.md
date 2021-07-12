# mult-e  

EndPoint DTO: http://host/Pessoa/findDTO - Método GET
Parâmetro enviado: NENHUM
Colunas escolhidas para retornar na classe DTO: Nome e DataNascimento

EndPoint Find: http://host/Pessoa/find - Método GET
Parâmetros:
  - Todos os registros: JSON vazio {}
  - Filtrar por nome: {"nome":"Patricia"}
  - Filtrar por data de nascimento: {"dataNascimento":"2000-02-02"}
  
EndPoint Delete: http://host/Pessoa/delete/1 - Método DELETE
  Parâmetro JSON enviado: NENHUM - {}
  
EndPoint Update: http://host/Pessoa/update/2 - Método PUT
  Parâmetro: {"nome" : "TESTE3", "dataNascimento" : "2021-12-01", "estadoCivil" : "2"}
  
EndPoint inserir: http://host/Pessoa/save - Método POST
  Parâmetro: {"nome" : "TESTE6", "dataNascimento" : "2021-12-01", "estadoCivil" : "5"}
  
  
Estado civil:

1 - Solteiro (a)
2 - Casado (a)
3 - União Estável
4 - Divorciado (a)
5 - Viúvo (a)
