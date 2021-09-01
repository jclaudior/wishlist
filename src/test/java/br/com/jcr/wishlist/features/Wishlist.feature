#language: pt
  Funcionalidade: Lista de desejos
    Cenario: Inserir uma lista de produtos
      Dado Que eu irei enviar um Objeto Wishlist contendo CustomerId e Um lista de produtos com Id e Description para o metodo POST em "/wishlist"
      Entao Eu Recebo status 201

    Cenario: Inserir uma lista de produtos com mais de vinte itens
      Dado Que eu irei enviar um Objeto Wishlist contendo CustomerId e Um lista com mais de vinte produtos com Id e Description para o metodo POST em "/wishlist"
      Entao Eu Recebo a mensagem "Limite máximo de 20 produtos na lista de desejos atingida!"


    Cenario: Inserir um produto na lista de desejos
      Dado Que eu irei enviar um Objeto Product  para o metodo POST em "/wishlist/customerId/8000"
      Entao Eu Recebo status 201

    Cenario: Inserir um produto na lista de desejos com mais de vinte itens
      Dado Que eu irei enviar um Objeto Product  para o metodo POST em "/wishlist/customerId/8000"
      Entao Eu Recebo a mensagem "Limite máximo de 20 produtos na lista de desejos atingida!"

    Cenario: Buscar lista de desejos com todos os produtos
      Dado Fazer a requisição GET em passando CustomerId "/wishlist/customerId/8000"
      Entao Eu Recebo um objeto com uma lista de produtos com tamanho maior que 1

    Cenario: Buscar lista de desejos com um produto
      Dado Fazer a requisição GET em passando CustomerId "/wishlist/customerId/8000/productId/31"
      Entao Eu Recebo um objeto com uma lista de produtos com tamanho igual a 1

    Cenario: Deletar lista de desejos do cliente
      Dado Fazer a requisição delete em passando CustomerId "/wishlist/customerId/8000"
      Entao Entao Eu Recebo status 200
