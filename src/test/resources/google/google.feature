
Feature: Busqueda en Google

	@BusquedaInput
  Scenario Outline: Buscar Usuario
    Given Se carga la URL de mi aplicacion
    When Escribo "<Campo>" en la barra de búsqueda
    And Verifico que el campo no está deshabilitado
    Then El modelo de Angular debe reflejar el valor "<Campo>"
    And Se muestra la lista de opciones
    When Selecciono la opción "<Campo>" de la lista
    Then El input de búsqueda debe estar deshabilitado

    Examples: 
      | Campo  |
      | Rodrigo |
   
