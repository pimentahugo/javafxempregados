# Projeto Cadastro e Consulta de Empregados com JavaFX, Persistência XML e JSON, Persistência SGBD Postrgres

<h3>Descrição do Projeto</h3>
Este projeto é uma continuação do projeto <a href="https://github.com/pimentahugo/guiswigncadastroempregados">GUI Swign - Cadastro de empregados</a>, agora migrando a interface gráfica para JavaFX e adicionando funcionalidades de persistência em arquivos XML, JSON e em um Sistema de Gerenciamento de Banco de Dados (SGBD) PostgreSQL. A aplicação permite o cadastro, consulta e remoção de empregados, proporcionando uma experiência mais moderna e eficiente.

<h3>Escopo do projeto</h3>

<h3>Funcionalidades</h3>
1. JavaFX Interface:<br />
- Interface gráfica JavaFX substituindo a GUI Swing.<br />
- Duas partes distintas para manipulação de dados: Persistência em arquivos XML e JSON, e Persistência em SGBD PostgreSQL.<br /><br />
2. Persistência em Arquivos XML:<br />
- "Escrita": Permite ao usuário cadastrar empregados e salvar os dados em um arquivo XML, escolhendo o local e o nome do arquivo.<br />
- "Leitura": Permite ao usuário carregar dados de um arquivo XML para a aplicação, substituindo o ArrayList de empregados em memória RAM.<br /><br />
3. Persistência em Arquivos JSON:<br />
- Funcionalidade semelhante à persistência em XML, mas utilizando o formato JSON.<br /><br />
4. Persistência em SGBD PostgreSQL:<br />
- Integração com um banco de dados PostgreSQL para persistência dos dados dos empregados.<br />
- Cadastramento e remoção de empregados refletem diretamente no banco de dados.<br />

<h3>Estrutura do Projeto</h3>
O projeto está organizado em pacotes para melhor separação e documentação. Os pacotes incluem:
