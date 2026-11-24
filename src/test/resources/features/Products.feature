@ProductsAll
Feature: APIS de Products

  Como usuario de la tienda
  Quiero consultar y crear productos
  Para poder verificar los detalles de los registros

  @CP01_Products @Products
  Scenario Outline: Obtener un producto por id
    Given el actor establece el endpoint de productos
    When el actor consulta el producto con id "<id>"
    Then el codigo de respuesta debe ser 200
    And el campo "id" de la respuesta debe ser "<id>"

    Examples:
      | id |
      | 1  |

  @CP02_Products @Products
  Scenario Outline: Validar los datos de un producto
    Given el actor establece el endpoint de productos
    When el actor consulta el producto con id "<id>"
    Then el codigo de respuesta debe ser 200
    And el campo "title" de la respuesta no debe estar vacio
    And el campo "category" de la respuesta no debe estar vacio

    Examples:
      | id | title        | category        |
      | 1 | "Essence Mascara Lash Princess" | "beauty"  |

  @CP03_Products @Products
  Scenario Outline: Crear un producto exitosamente
    Given el actor establece el endpoint de productos
    When el actor crea un producto con el "<title>" "<price>" "<category>"
    Then el codigo de respuesta debe ser 201
    And el campo "title" de la respuesta debe ser "<title>"

    Examples:
      | title        | price | category |
      | Laptop Mario | 999   | laptops  |

  @CP04_Products_Negativo @Products
  Scenario Outline: Obtener un producto que no existe
    Given el actor establece el endpoint de productos
    When el actor consulta el producto con id "<id>"
    Then el codigo de respuesta debe ser 404
    And el campo "message" de la respuesta debe contener "<mensaje>"

    Examples:
      | id    | mensaje   |
      | 99999 | not found |
      | 88888 | not found |
