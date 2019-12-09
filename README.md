# xy-inc

A XY Inc. é uma empresa especializada na produção de excelentes receptores GPS (Global Positioning System). A diretoria está empenhada em lançar um dispositivo inovador que
promete auxiliar pessoas na localização de ponto de interesse (POIs), e precisa muito de sua ajuda.

Você foi contratado para desenvolver a plataforma que fornecerá toda a inteligência ao dispositivo. Esta plataforma deve ser baseada em serviços, de forma a flexibilizar a integração, sendo
estes:

Serviço para cadastrar pontos de interesse com 3 atributos: Nome do POI, Coordenada X (inteiro não negativo) Coordenada Y (inteiro não negativo). Os POIs devem ser armazenados em uma base de dados.
Serviço para listar todos os POIs cadastrados.
Serviço para listar POIs por proximidade. Este serviço receberá uma coordenada X e uma coordenada Y, especificando um ponto de referência, em como uma distância máxima (dmax) em metros. O serviço deverá retornar todos os POIs da base de dados que estejam a uma distância
menor ou igual a d-max a partir do ponto de referência.

## Tecnologias:

* Java 8
* Spring Boot
* Maven
* Lombok
* Junit
* Swagger

## Documentação:
https://xy-zup.herokuapp.com/swagger-ui.html#/

## Execução:
Deverá ser configurado o properties para que a aplicação rode local

Deverá ser criado um arquivo com o nome 'Procfile' na raiz do projeto com o que segue:
```
web: java -jar target/java-getting-started-1.0.jar
```
Deverá ser executado o seguinte comando na raiz do projeto:
```
mvn clean install
```

## Endpoints:

![Image description](https://i.ibb.co/HBKgwCn/2019-12-09-00h01-28.png)
