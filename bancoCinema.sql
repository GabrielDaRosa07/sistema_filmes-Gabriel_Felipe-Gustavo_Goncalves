-- Arquivo SQL para o trabalho final de BAN -- Gabriel Felipe / Gustavo Gonçalves

DROP TABLE IF EXISTS Elenco, Possui, Avaliacao, Usuario, Filme, Genero, Pessoa;

CREATE TABLE Usuario(
    IDUser INT PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    Email VARCHAR(100) NOT NULL UNIQUE,
    Senha VARCHAR(50) NOT NULL,
    Credencial BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE Filme(
    IDFilme INT PRIMARY KEY,
    Titulo VARCHAR(100) NOT NULL,
    Ano INT,
    Duracao INT,
    Sinopse TEXT,
    Poster VARCHAR(255)
);

CREATE TABLE Genero(
    IDGenero INT PRIMARY KEY,
    Nome VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Pessoa(
    IDPessoa INT PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    Papel VARCHAR(100) NOT NULL
);

CREATE TABLE Avaliacao(
    IDUser INT,
    IDFilme INT,
    Critica TEXT,
    Nota NUMERIC(2, 1),
    Data DATE,
    PRIMARY KEY (IDUser, IDFilme),
    FOREIGN KEY (IDUser) REFERENCES Usuario(IDUser),
    FOREIGN KEY (IDFilme) REFERENCES Filme(IDFilme)
);

CREATE TABLE Lista (
    IDLista INT PRIMARY KEY,
    IDUser INT NOT NULL,
    Nome VARCHAR(100) NOT NULL,
    Privada BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (IDUser) REFERENCES Usuario(IDUser) ON DELETE CASCADE 
);

CREATE TABLE ListaDeFilme (
    IDLista INT,
    IDFilme INT,
    Posicao INT,
    PRIMARY KEY (IDLista, IDFilme),
    FOREIGN KEY (IDLista) REFERENCES Lista(IDLista) ON DELETE CASCADE,
    FOREIGN KEY (IDFilme) REFERENCES Filme(IDFilme)
);

CREATE TABLE Possui(
    IDFilme INT,
    IDGenero INT,
    PRIMARY KEY (IDFilme, IDGenero),
    FOREIGN KEY (IDFilme) REFERENCES Filme(IDFilme),
    FOREIGN KEY (IDGenero) REFERENCES Genero(IDGenero)
);

CREATE TABLE Elenco (
    IDPessoa INT,
    IDFilme INT,
    PRIMARY KEY (IDPessoa, IDFilme),
    FOREIGN KEY (IDPessoa) REFERENCES Pessoa(IDPessoa),
    FOREIGN KEY (IDFilme) REFERENCES Filme(IDFilme)
);

-- Dados exemplos de teste --

INSERT INTO Usuario (IDUser, Nome, Email, Senha, Credencial) VALUES
(1, 'Ana Maria', 'ana.maria@gmail.com', 's123', FALSE),
(2, 'Gabriel Jones', 'gabriel.jones@email.com', 's456', FALSE),
(3, 'Nido Joelson', 'nido.critico@gmail.com', 's789', TRUE);

INSERT INTO Genero (IDGenero, Nome) VALUES
(10, 'Ficção Científica'),
(20, 'Drama'),
(30, 'Ação');

INSERT INTO Pessoa (IDPessoa, Nome, Papel) VALUES
(101, 'Keanu Reeves', 'Ator'),
(102, 'Lana Wachowski', 'Diretor'),
(103, 'David Corenswet', 'Ator'),
(104, 'Tarantino', 'Diretor/Ator'),
(106, 'James Gunn', 'Diretor');

INSERT INTO Filme (IDFilme, Titulo, Ano, Duracao, Sinopse) VALUES
(1001, 'Matrix', 1999, 136, 'Um hacker chamado Neo escolhe descobrir a verdade sobre sua realidade.'),
(1002, 'Superman', 2025, 121, 'Movido pela fé na humanidade e pela esperança em um mundo melhor, Superman embarca em uma jornada diante de uma sociedade que questiona seus ideais de justiça e verdade, Superman enfrentará desafios épicos que testarão sua missão e o verdadeiro significado de ser um herói.');

INSERT INTO Possui (IDFilme, IDGenero) VALUES
(1001, 10),
(1001, 30),
(1002, 30);

INSERT INTO Elenco (IDPessoa, IDFilme) VALUES
(101, 1001),
(103, 1002);

INSERT INTO Avaliacao (IDUser, IDFilme, Critica, Nota, Data) VALUES
(1, 1001, 'Filme revolucionário, mudou o cinema!', 5.0, '2025-06-26'),
(3, 1001, 'Uma obra-prima dos filmes de super heroi, embora o estilo pode ser muito lúdico para alguns.', 4.5, '2025-06-20');

INSERT INTO Lista (IDLista, IDUser, Nome, Privada) VALUES
(501, 1, 'Meus Favoritos de Ficção Científica', FALSE),
(502, 2, 'Filmes para ver no fim de semana', TRUE);

INSERT INTO ListaDeFilme (IDLista, IDFilme, Posicao) VALUES
(501, 1001, 1);
